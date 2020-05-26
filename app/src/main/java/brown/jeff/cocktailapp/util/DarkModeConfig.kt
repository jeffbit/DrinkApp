package brown.jeff.cocktailapp.util

import androidx.appcompat.app.AppCompatDelegate

//handles user changing from light mode to dark mode
object DarkModeConfig {
    private const val lightmode = "light"
    private const val darkmode = "dark"

    fun shouldEnableDarkMode(theme: String) {
        when (theme) {
            darkmode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            lightmode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}