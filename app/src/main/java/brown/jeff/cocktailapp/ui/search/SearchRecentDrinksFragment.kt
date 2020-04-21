package brown.jeff.cocktailapp.ui.search

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
import brown.jeff.cocktailapp.util.hideKeyboard
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRecentDrinksFragment : Fragment() {

    private val searchRecentDrinksViewModel: SearchRecentDrinksViewModel by viewModel()
    private lateinit var drinkAdapter: DrinkAdapter
    private lateinit var searchView: SearchView
    private var savedQuery: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchDrinks(view)

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.searchfragment_menu, menu)

    }

    //   add filter to searchview. This will change search to search ingredients, will return list of drinks with the query ingredients,
//            return search of no alcoholic drinks or alcoholic drinks
//        this will change the search view fun depending on what item is checked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.searchsettings_menuitem -> {
                Toast.makeText(context, "Settings Clicked", Toast.LENGTH_SHORT).show();
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val toolbar = view?.findViewById<Toolbar>(R.id.popular_toolbar)
        activity?.setActionBar(toolbar)
        drinkAdapter = DrinkAdapter(emptyList(), { drink: Drink ->
            handleScreenChange(drink)
        })
        recyclerview_search.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = drinkAdapter

        }


//        // set up so that if query was searched before fragment was destroyed it populates with that data
//        savedQuery = savedInstanceState?.getString("QUERY")
//        Toast.makeText(context, savedQuery, Toast.LENGTH_SHORT).show();
//        if (savedQuery != null) {
//            searchRecentDrinksViewModel.searchDrinkByName(savedQuery!!)
//        } else {
        searchRecentDrinksViewModel.getRecentDrinks()
//        }
        searchRecentDrinksViewModel.popularDrinks.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                drinkAdapter.updateDrinkList(it)
            }
        })
        searchRecentDrinksViewModel.displayError.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                errrortextview_search.text = it
                recyclerview_search.visibility = View.GONE
            }

        })


    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("QUERY", savedQuery)
//    }


    //initiates searchview
    private fun searchDrinks(view: View) {
        searchView = view.findViewById(R.id.searchview_search)
//        searchView.isIconified = false
//        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchRecentDrinksViewModel.searchDrinkByName(it) }
                hideKeyboard()
                // savedQuery = query
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    //moves drink data to new fragment
    private fun handleScreenChange(drink: Drink) {
        val action =
            SearchRecentDrinksFragmentDirections.actionNavigationRecentToDrinkClickedFragment(drink)
        findNavController().navigate(action)

    }
}
