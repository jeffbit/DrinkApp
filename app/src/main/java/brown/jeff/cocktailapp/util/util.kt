package brown.jeff.cocktailapp.util

import android.widget.ImageView
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


//used to load image from API into imageview
fun loadImageRecyclerView(imageView: ImageView, drink: Drink) {
    Glide.with(imageView.context)
        .load(drink.drinkImg)
        .error(R.drawable.ic_broken_image_black_24dp)
        .apply(RequestOptions.centerCropTransform())
        .into(imageView)
}

fun loadImage(imageView: ImageView, drink: Drink) {
    Glide.with(imageView.context)
        .load(drink.drinkImg)
        .error(R.drawable.ic_broken_image_black_24dp)
        .into(imageView)
}