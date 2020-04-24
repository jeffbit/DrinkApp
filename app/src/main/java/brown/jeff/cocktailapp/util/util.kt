package brown.jeff.cocktailapp.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
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
    snackBar.setAction(action, View.OnClickListener {
        actionFunction()
    })
    snackBar.show()

}

fun showAlertDialog(context: Context, message: String, action: () -> Unit) {
    val builder = AlertDialog.Builder(context)
    builder.setMessage(message)
        .setPositiveButton("Confirm", DialogInterface.OnClickListener { dialog, _ ->
            action
            dialog.dismiss()
        })
        .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
            dialog.cancel()
        })

    builder.create()


}

fun backPressedToolbar(toolbar: androidx.appcompat.widget.Toolbar?, activity: Activity?) {
    toolbar?.setNavigationOnClickListener {
        activity?.onBackPressed()
    }
}


