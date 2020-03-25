package brown.jeff.cocktailapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DrinkAdapter(private var drinks: List<Drink>) :
    RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {


    fun updateDrinkList(drinkList: List<Drink>) {
        drinks = drinkList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.drink_adapter_view, parent, false)
        return DrinkViewHolder(view)


    }

    override fun getItemCount(): Int {
        return drinks.size
    }


    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val currentDrink = drinks[position]
        holder.bind(currentDrink)


    }

    class DrinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var drinkImageView: ImageView = view.findViewById(R.id.drink_imageview)
        private var drinkNameTextView: TextView = view.findViewById(R.id.drink_name)

        fun bind(drink: Drink) {
            drinkNameTextView.text = drink.drink

            //Glide loads image into view
            Glide.with(drinkImageView.context)
                .load(drink.drinkImg)
                .error(R.drawable.ic_broken_image_black_24dp)
                .apply(RequestOptions.centerCropTransform())
                .into(drinkImageView)


        }

    }


    //todo: add onclick to allow user to click heart icon in corner of view to save and favorite cocktails

}