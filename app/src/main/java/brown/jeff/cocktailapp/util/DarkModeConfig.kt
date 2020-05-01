package brown.jeff.cocktailapp.util

import androidx.appcompat.app.AppCompatDelegate

//handles user changing from light mode to dark mode
enum class DarkModeConfig {
    YES,
    NO,
    FOLLOW_SYSTEM
}

fun shouldEnableDarkMode(darkModeConfig: DarkModeConfig) {
    when (darkModeConfig) {
        DarkModeConfig.YES -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        DarkModeConfig.NO -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }
        DarkModeConfig.FOLLOW_SYSTEM -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        }
    }
}