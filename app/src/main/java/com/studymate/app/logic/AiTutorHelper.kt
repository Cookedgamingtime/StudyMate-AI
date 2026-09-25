package com.studymate.app.logic

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

object AiTutorHelper {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val gson = Gson()

    suspend fun ask(
        userMessage: String,
        language: String = "hi"
    ): String = withContext(Dispatchers.IO) {

        val langInstruction = if (language == "hi") {
            "Respond in Hindi (Devanagari). Use simple words. Mix English terms naturally when needed."
        } else {
            "Respond in simple English."
        }

        val fullPrompt = AiConfig.SYSTEM_PROMPT + "\n\n" + langInstruction + "\n\nStudent: " + userMessage

        val bodyJson = JsonObject().apply {
            val contents = JsonArray()
            val contentObj = JsonObject().apply {
                val parts = JsonArray()
                val partObj = JsonObject().apply {
                    addProperty("text", fullPrompt)
                }
                parts.add(partObj)
                add("parts", parts)
            }
            contents.add(contentObj)
            add("contents", contents)
        }

        val url = AiConfig.BASE_URL + "/models/" + AiConfig.MODEL + ":generateContent?key=" + AiConfig.API_KEY

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", "application/json")
            .post(bodyJson.toString().toRequestBody("application/json".toMediaType()))
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val body = response.body?.string() ?: ""
                if (!response.isSuccessful) {
                    DebugHelper.error("Gemini API error: " + response.code)
                    return@withContext "Beta, kuch problem aa gayi. Try again."
                }

                val json = gson.fromJson(body, JsonObject::class.java)
                val candidates = json.getAsJsonArray("candidates")
                if (candidates != null && candidates.size() > 0) {
                    val candidate = candidates[0].asJsonObject
                    val content = candidate.getAsJsonObject("content")
                    val parts = content.getAsJsonArray("parts")
                    if (parts != null && parts.size() > 0) {
                        val text = parts[0].asJsonObject.get("text").asString
                        return@withContext text
                    }
                }
                return@withContext "Empty response from AI."
            }
        } catch (e: Exception) {
            DebugHelper.error("AiTutorHelper error: " + e.message)
            return@withContext "Beta, connection issue. Thoda baad try karein."
        }
    }
}
