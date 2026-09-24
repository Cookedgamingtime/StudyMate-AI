package com.studymate.app

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.studymate.app.ui.DashboardScreen
import com.studymate.app.ui.LanguageScreen
import com.studymate.app.ui.SplashScreen
import com.studymate.app.ui.theme.StudyMateTheme

class MainActivity : ComponentActivity() {

    private val notifPerm = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notifPerm.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        setContent {
            StudyMateTheme {
                val nav = rememberNavController()
                NavHost(
                    navController = nav,
                    startDestination = "splash"
                ) {
                    composable("splash") {
                        SplashScreen(
                            onComplete = { nav.navigate("language") }
                        )
                    }
                    composable("language") {
                        LanguageScreen(
                            onSelected = { nav.navigate("dashboard") }
                        )
                    }
                    composable("dashboard") {
                        DashboardScreen()
                    }
                }
            }
        }
    }
}
