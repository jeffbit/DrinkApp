package brown.jeff.cocktailapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_popular.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteDrinksFragment : Fragment() {


    private val favoriteDrinksViewModel: FavoriteDrinksViewModel by viewModel()
    private lateinit var favoriteDrinkAdapter: DrinkAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteDrinkAdapter = DrinkAdapter(
            emptyList()
        ) { drink: Drink -> handleScreenChange(drink) }

        recyclerview_popular.apply {
            adapter = favoriteDrinkAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)

        }
        favoriteDrinksViewModel.getDrinksFromLocalStorage()
        //todo: Fragment will not inflate with empty list, figure out how to and display error message if nothing has been added to favorites.
        favoriteDrinksViewModel.favoriteDrinks.observe(viewLifecycleOwner, Observer {
            favoriteDrinkAdapter.updateDrinkList(it)
            errormessage_textview.visibility = View.GONE
        })

        favoriteDrinksViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            errormessage_textview.visibility = View.VISIBLE
            errormessage_textview.text = it
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun handleScreenChange(drink: Drink) {
        val action =
            FavoriteDrinksFragmentDirections.actionNavigationFavoritesToDrinkClickedFragment(drink)
        findNavController().navigate(action)

    }
}
