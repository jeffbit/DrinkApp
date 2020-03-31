package brown.jeff.cocktailapp.ui.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRecentDrinksFragment : Fragment() {

    private val searchRecentDrinksViewModel: SearchRecentDrinksViewModel by viewModel()
    private lateinit var drinkAdapter: DrinkAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        drinkAdapter = DrinkAdapter(emptyList(), { drink: Drink ->
            handleClicks(drink)
        })
        recyclerview_search.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = drinkAdapter

        }

        searchRecentDrinksViewModel.getRecentDrinks()
        searchRecentDrinksViewModel.popularDrinks.observe(viewLifecycleOwner, Observer {
            drinkAdapter.updateDrinkList(it)
        })


    }

    fun handleClicks(drink: Drink) {
        Toast.makeText(context, "Search Fragment: ${drink.alcoholic}", Toast.LENGTH_SHORT).show();
        //todo: pass data into drinkclicked fragment
    }
}
