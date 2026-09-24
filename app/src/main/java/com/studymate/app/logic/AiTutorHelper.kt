package com.studymate.app.logic

import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

/**
 * AiTutorHelper — DeepSeek API se baat karta hai.
 * Zig/Momo ki awaaz mein response deta hai.
 */
object AiTutorHelper {

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val gson = Gson()

    /**
     * AI se ek response lo.
     * @param userMessage User ka message
     * @param language "hi" ya "en"
     * @return AI ka reply (text)
     */
    suspend fun ask(
        userMessage: String,
        language: String = "hi"
    ): String = withContext(Dispatchers.IO) {

        val langInstruction = if (language == "hi") {
            "Respond in Hindi (Devanagari). Use simple words. Mix English terms naturally when needed."
        } else {
            "Respond in simple English."
        }

        val bodyJson = JsonObject().apply {
            addProperty("model", AiConfig.MODEL)

            val messages = com.google.gson.JsonArray()

            val systemMsg = JsonObject().apply {
                addProperty("role", "system")
                addProperty("content", AiConfig.SYSTEM_PROMPT + "\n" + langInstruction)
            }
            messages.add(systemMsg)

            val userMsg = JsonObject().apply {
                addProperty("role", "user")
                addProperty("content", userMessage)
            }
            messages.add(userMsg)

            add("messages", messages)
            addProperty("temperature", 0.7)
            addProperty("max_tokens", 800)
        }

        val request = Request.Builder()
            .url(AiConfig.BASE_URL + AiConfig.CHAT_ENDPOINT)
            .addHeader("Authorization", "Bearer ${AiConfig.API_KEY}")
            .addHeader("Content-Type", "application/json")
            .post(bodyJson.toString().toRequestBody("application/json".toMediaType()))
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val body = response.body?.string() ?: ""
                if (!response.isSuccessful) {
                    return@withContext "Kuch problem aa gayi. Try again: ${response.code}"
                }

                val json = gson.fromJson(body, JsonObject::class.java)
                val choices = json.getAsJsonArray("choices")
                if (choices != null && choices.size() > 0) {
                    val message = choices[0].asJsonObject.getAsJsonObject("message")
                    return@withContext message.get("content").asString
                }
                return@withContext "Empty response from AI."
            }
        } catch (e: Exception) {
            return@withContext "Beta, connection issue. Thoda baad try karein. (${e.message})"
        }
    }
}
