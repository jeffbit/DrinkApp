package brown.jeff.cocktailapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.ui.popular.PopularDrinksFragmentDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar


//used to load image into imageview
fun loadImage(imageView: ImageView, drink: Drink) {
    Glide.with(imageView.context)
        .load(drink.drinkImg)
        .error(R.drawable.ic_broken_image_black_24dp)
        .apply(RequestOptions.centerCropTransform())
        .into(imageView)
}

//hides keyboard from view
fun Context.hideKeyBoard(view: View) {
    val inputManager =
        getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)

}

fun Fragment.hideKeyboard() {
    view?.let {
        activity?.hideKeyBoard(it)
    }
}


fun showSnackBar(
    view: View,
    displayedMessage: String,
    duration: Int,
    action: String,
    actionFunction: () -> Unit
) {
    val snackBar = Snackbar.make(view, displayedMessage, duration)
    snackBar.setAction(action) {
        actionFunction()
    }
    snackBar.show()

}


//back press
fun backPressedToolbar(toolbar: androidx.appcompat.widget.Toolbar?, activity: Activity?) {
    toolbar?.setNavigationOnClickListener {
        activity?.onBackPressed()
    }
}


//navigate to settings fragment
fun navigateToSettings(view: View) {
    val action =
        PopularDrinksFragmentDirections.actionNavigationPopularToSettingsFragment()
    view.findNavController().navigate(action)
}


//change recyclerview on orientation change
fun changeRecyclerViewLayout(
    context: Context,
    activity: Activity,
    recyclerView: RecyclerView
) {
    if (activity.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
    } else {
        recyclerView.layoutManager = GridLayoutManager(context, 4)


    }
}


//intent to share drink
fun shareDrink(
    activity: Activity,
    drinkName: String
) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, drinkName)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share $drinkName")
    activity.startActivity(shareIntent)
}

//intent to open url
fun openToWebsite(activity: Activity, url: String, title: String) {

    val intent: Intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = Uri.parse(url)
    }
    val shareIntent = Intent.createChooser(intent, title)
    activity.startActivity(shareIntent)
}

//intent to email feedback to dev
fun emailFeedback(activity: Activity) {
    val intent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_EMAIL, "jbicondev@gmail.com")
        putExtra(Intent.EXTRA_SUBJECT, "DrinkApp: Feedback")
    }

    val sendIntent = Intent.createChooser(intent, "Email feedback")
    activity.startActivity(sendIntent)


}


//todo: them changes colors once app is re-opened
//get saved theme
fun getSavedTheme(activity: Activity) {
    val settings: SharedPreferences =
        activity.getSharedPreferences(Constants.SETTINGS_SHARED_PREF, 0)
    when (settings.getInt("mode", 0)) {
        0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

}

fun shouldEnableDarkMode(theme: String) {
    when (theme) {
        Constants.DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Constants.LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}







