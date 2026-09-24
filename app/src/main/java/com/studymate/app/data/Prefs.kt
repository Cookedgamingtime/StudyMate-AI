package com.studymate.app.data

import android.content.Context
import androidx.core.content.edit

class Prefs(context: Context) {
    private val sp = context.getSharedPreferences("studymate_prefs", Context.MODE_PRIVATE)

    var language: String
        get() = sp.getString("language", "hi") ?: "hi"
        set(v) = sp.edit { putString("language", v) }

    var userName: String
        get() = sp.getString("user_name", "") ?: ""
        set(v) = sp.edit { putString("user_name", v) }

    var userClass: Int
        get() = sp.getInt("user_class", 8)
        set(v) = sp.edit { putInt("user_class", v) }

    var streak: Int
        get() = sp.getInt("streak", 0)
        set(v) = sp.edit { putInt("streak", v) }

    var lastActiveDate: String
        get() = sp.getString("last_active", "") ?: ""
        set(v) = sp.edit { putString("last_active", v) }

    var totalXp: Int
        get() = sp.getInt("total_xp", 0)
        set(v) = sp.edit { putInt("total_xp", v) }

    var mascotChoice: String
        get() = sp.getString("mascot", "zig") ?: "zig"
        set(v) = sp.edit { putString("mascot", v) }
}
