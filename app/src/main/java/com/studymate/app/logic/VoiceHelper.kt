package com.studymate.app.logic

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale

/**
 * VoiceHelper — AI ki awaaz ke liye.
 * Text ko bol ke sunata hai (Hindi + English).
 */
object VoiceHelper {

    private var tts: TextToSpeech? = null
    private var initialized = false
    private var currentLanguage = "hi"

    fun init(context: Context, onReady: () -> Unit = {}) {
        if (initialized) {
            onReady()
            return
        }
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                setLanguage(currentLanguage)
                initialized = true
                tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {}
                    override fun onDone(utteranceId: String?) {}
                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {}
                })
                onReady()
            }
        }
    }

    fun setLanguage(lang: String) {
        currentLanguage = lang
        val locale = when (lang) {
            "hi" -> Locale("hi", "IN")
            else -> Locale.ENGLISH
        }
        tts?.language = locale
        tts?.setSpeechRate(0.95f)
        tts?.setPitch(1.1f)
    }

    /**
     * AI ki awaaz — warm, soft, female tone.
     * Ye AI ke text response ko bol ke sunata hai.
     */
    fun speak(text: String) {
        if (!initialized) return
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "studymate_${System.currentTimeMillis()}")
    }

    /** AI ka reply rok do (agar user skip kar de) */
    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        initialized = false
    }
}
