package com.studymate.app.logic

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DebugHelper {

    private const val TAG = "StudyMate"

    private val _logs = MutableStateFlow<List<LogEntry>>(emptyList())
    val logs: StateFlow<List<LogEntry>> = _logs

    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    data class LogEntry(
        val time: String,
        val level: String,
        val message: String
    )

    fun info(message: String) {
        val entry = LogEntry(timeFormat.format(Date()), "INFO", message)
        _logs.value = _logs.value + entry
        Log.i(TAG, message)
    }

    fun warn(message: String) {
        val entry = LogEntry(timeFormat.format(Date()), "WARN", message)
        _logs.value = _logs.value + entry
        Log.w(TAG, message)
    }

    fun error(message: String, throwable: Throwable? = null) {
        val fullMsg = if (throwable != null) "$message | ${throwable.message}" else message
        val entry = LogEntry(timeFormat.format(Date()), "ERROR", fullMsg)
        _logs.value = _logs.value + entry
        Log.e(TAG, message, throwable)
    }

    fun clear() {
        _logs.value = emptyList()
    }
}
