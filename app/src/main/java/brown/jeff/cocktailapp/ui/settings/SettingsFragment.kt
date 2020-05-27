package brown.jeff.cocktailapp.ui.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.util.Constants
import brown.jeff.cocktailapp.util.DarkModeConfig
import brown.jeff.cocktailapp.util.emailFeedback
import brown.jeff.cocktailapp.util.openToWebsite

class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var darkModeConfig: DarkModeConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        darkModeConfig = DarkModeConfig
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)


    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return when (preference?.key) {
            "ui_mode" -> {
                switchThemes()
                true
            }
            "feedback" -> {
                emailFeedback(requireActivity())
                true

            }
            "website" -> {
                openToWebsite(
                    requireActivity(),
                    getString(R.string.cocktaildb_site),
                    getString(R.string.open_cocktaildb)
                )
                true

            }
            else -> super.onPreferenceTreeClick(preference)
        }
    }


    private fun switchThemes() {
        val settingsPref: SharedPreferences =
            requireActivity().getSharedPreferences(Constants.SETTINGS_SHARED_PREF, 0)
        val editor: SharedPreferences.Editor = settingsPref.edit()
        val listPreference: ListPreference? =
            preferenceManager.findPreference<ListPreference>("ui_mode")
        listPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                when (newValue) {
                    "Light Mode" -> {
                        darkModeConfig.shouldEnableDarkMode("light")
                        requireActivity().recreate()
                        editor.putInt("mode", 0)
                        editor.apply()
                    }
                    "Dark Mode" -> {
                        darkModeConfig.shouldEnableDarkMode("dark")
                        requireActivity().recreate()

                        editor.putInt("mode", 1)
                        editor.apply()
                    }
                }
                true
            }

    }


    private fun openGooglePlay() {
        //todo: open up google play app page to write review or submit issues
    }

}



