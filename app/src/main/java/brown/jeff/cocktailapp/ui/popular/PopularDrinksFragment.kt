package brown.jeff.cocktailapp.ui.popular

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
import brown.jeff.cocktailapp.util.changeRecyclerViewLayout
import kotlinx.android.synthetic.main.fragment_popular.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularDrinksFragment : Fragment() {

    private val popularDrinksViewModel: PopularDrinksViewModel by viewModel()
    private lateinit var popularDrinkAdapter: DrinkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting_menuitem -> {
//todo: allow user to disable notifications, delete all favorites,
// view info about api, access original api website, change from light mode to dark mode
                changeToSettingsScreen()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_popular, container, false)
        swipeToRefresh(view)
        //sets the toolbar in the fragment
        val toolbar = view?.findViewById<Toolbar>(R.id.popular_toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        popularDrinkAdapter = DrinkAdapter(
            emptyList()
        ) { drink: Drink -> handleScreenChange(drink) }

        recyclerview_popular.apply {
            adapter = popularDrinkAdapter
            setHasFixedSize(true)
            activity?.let { changeRecyclerViewLayout(context, it, this) }

        }

        popularDrinksViewModel.getAllPopularDrinks()
        popularDrinksViewModel.popularDrinks.observe(viewLifecycleOwner, Observer {
            popularDrinkAdapter.updateDrinkList(it)

        })


    }

    //swipe to refresh pulls new data

    private fun swipeToRefresh(view: View) {
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh_popular)
        swipeRefreshLayout.setOnRefreshListener {
            popularDrinksViewModel.getAllPopularDrinks()
            swipeRefreshLayout.isRefreshing = false
        }
    }




    private fun handleScreenChange(drink: Drink) {
        val action =
            PopularDrinksFragmentDirections.actionNavigationPopularToDrinkClickedFragment(drink)
        findNavController().navigate(action)

    }

    private fun changeToSettingsScreen() {
        val action = PopularDrinksFragmentDirections.actionNavigationPopularToSettingsFragment()
        findNavController().navigate(action)
    }


}
