package brown.jeff.cocktailapp.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.util.emailFeedback
import brown.jeff.cocktailapp.util.openToWebsite

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)


    }


    //future updates for settings page. Add alert dialog so user can rate and review app. Add option to switch to dark,light, or battery saver mode
    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        return when (preference?.key) {
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

}



