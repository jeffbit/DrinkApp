package brown.jeff.cocktailapp.ui.drinkclicked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import brown.jeff.cocktailapp.R
import kotlinx.android.synthetic.main.drink_ingredients_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrinkIngredientsFragment() : Fragment() {

    private val drinkIngredientsViewModel: DrinkIngredientsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drink_ingredients_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        drinkIngredientsViewModel.drink.observe(viewLifecycleOwner, Observer {
            ingredients_textview.text = it.strIngredient1
        })
    }

}
