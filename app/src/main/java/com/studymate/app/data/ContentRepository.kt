package com.studymate.app.data

import androidx.compose.ui.graphics.Color

/**
 * ContentRepository — saara content yahan se load hota hai.
 * Filhaal hardcoded. Baad mein Firebase se aayega.
 */
object ContentRepository {

    val subjects: List<Subject> = listOf(
        Subject(
            id = "python",
            name = "Python",
            nameHindi = "पाइथन",
            emoji = "🐍",
            color = Color(0xFF7EB8E8),
            lessons = pythonLessons()
        ),
        Subject("algebra", "Algebra", "बीजगणित", "📐", Color(0xFFFF8A80)),
        Subject("geometry", "Geometry", "ज्यामिति", "🔺", Color(0xFFA8E6CF)),
        Subject("physics", "Physics", "भौतिकी", "⚛️", Color(0xFFFFD93D)),
        Subject("chemistry", "Chemistry", "रसायन", "🧪", Color(0xFFB8A8E8)),
        Subject("biology", "Biology", "जीव विज्ञान", "🧬", Color(0xFFA8E8C8)),
        Subject("english", "English", "अंग्रेज़ी", "📖", Color(0xFFFFA8C8)),
        Subject("hindi", "Hindi", "हिन्दी", "✍️", Color(0xFFFFC8A8)),
        Subject("gk", "GK", "सामान्य ज्ञान", "🌍", Color(0xFFA8D8E8)),
        Subject("coding", "Coding", "कोडिंग", "💻", Color(0xFFE8A8C8))
    )

    private fun pythonLessons(): List<Lesson> = listOf(
        Lesson(
            id = "python_01",
            subjectId = "python",
            title = "What is Python?",
            titleHindi = "पाइथन क्या है?",
            content = "Python is a programming language. It is simple, like English. Just like we say 'Hello', in Python we write print(\"Hello\"). That's it. Let's start.",
            contentHindi = "पाइथन एक programming language है। ये simple है, English की तरह। जैसे हम 'Hello' बोलते हैं, वैसे पाइथन में print(\"Hello\") लिखते हैं। बस। चलो शुरू करें।",
            order = 1
        ),
        Lesson(
            id = "python_02",
            subjectId = "python",
            title = "Variables",
            titleHindi = "वेरिएबल्स",
            content = "A variable is like a box. You put things in it. Like x = 5 means box 'x' has 5 inside. Later you can use it.",
            contentHindi = "वेरिएबल एक डिब्बे की तरह है। उसमें चीज़ें रखते हैं। जैसे x = 5 मतलब डिब्बा 'x' में 5 रखा। बाद में इस्तेमाल कर सकते हैं।",
            order = 2
        ),
        Lesson(
            id = "python_03",
            subjectId = "python",
            title = "Data Types",
            titleHindi = "डेटा टाइप्स",
            content = "Main types: int (numbers like 5), float (decimals like 3.14), string (text like \"hello\"), bool (True/False).",
            contentHindi = "मुख्य types: int (5 जैसे numbers), float (3.14 जैसे decimals), string (\"hello\" जैसा text), bool (True/False)।",
            order = 3
        )
    )
}
