package brown.jeff.cocktailapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import brown.jeff.cocktailapp.model.Drink

class DrinkAdapter(private var drinks: List<Drink>) :
    RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return drinks.size
    }


    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val currentDrink = drinks[position]


    }

    class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        
        fun bind(){

        }

    }
}