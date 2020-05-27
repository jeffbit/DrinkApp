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
import brown.jeff.cocktailapp.util.Constants
import brown.jeff.cocktailapp.util.changeRecyclerViewLayout
import brown.jeff.cocktailapp.util.hideKeyboard
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRecentDrinksFragment : Fragment() {

    private val searchRecentDrinksViewModel: SearchRecentDrinksViewModel by viewModel()
    private lateinit var drinkAdapter: DrinkAdapter
    private lateinit var searchView: SearchView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchDrinks(view)
        hideKeyboard()
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.searchfragment_menu, menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.searchsettings_menuitem -> {
                Toast.makeText(context, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val toolbar = view?.findViewById<Toolbar>(R.id.popular_toolbar)
        activity?.setActionBar(toolbar)
        drinkAdapter = DrinkAdapter(emptyList()) { drink: Drink ->
            handleScreenChange(drink)
        }
        recyclerview_search.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = drinkAdapter
            activity?.let { changeRecyclerViewLayout(context, it, this) }

        }
        if (savedInstanceState != null) {
            val searchString = savedInstanceState.getString(Constants.SEARCH_STRING)
        }


        searchRecentDrinksViewModel.getRecentDrinks()
        searchRecentDrinksViewModel.popularDrinks.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                drinkAdapter.updateDrinkList(it)
            }
        })
        searchRecentDrinksViewModel.displayError.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                errrortextview_search.text = it
                errrortextview_search.visibility = View.VISIBLE
                recyclerview_search.visibility = View.GONE
            } else {
                errrortextview_search.visibility = View.GONE
                recyclerview_search.visibility = View.VISIBLE

            }

        })


    }


    //initiates searchview
    private fun searchDrinks(view: View) {
        searchView = view.findViewById(R.id.searchview_search)
        searchView.setOnClickListener {
            searchView.onActionViewExpanded()
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchRecentDrinksViewModel.searchDrinkByName(it)
                    hideKeyboard()
                }
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
