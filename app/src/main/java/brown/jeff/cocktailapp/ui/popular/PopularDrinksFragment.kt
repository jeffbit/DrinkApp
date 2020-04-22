package brown.jeff.cocktailapp.ui.popular

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
import kotlinx.android.synthetic.main.fragment_popular.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularDrinksFragment : Fragment() {

    private val popularDrinksViewModel: PopularDrinksViewModel by viewModel()
    private lateinit var popularDrinkAdapter: DrinkAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        popularDrinksViewModel.insertTestDrinks()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.setting_menuitem -> {
                Toast.makeText(context, "Settings Clicked", Toast.LENGTH_SHORT).show()
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
        //swipe to refresh pulls new data
        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh_popular)
        swipeRefreshLayout.setOnRefreshListener {
            popularDrinkAdapter.clearDrinks()
            popularDrinksViewModel.getAllPopularDrinks()
            //val snackBar = Snackbar.make(view, "Data updated", 1000)
            swipeRefreshLayout.isRefreshing = false
        }
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
            layoutManager = GridLayoutManager(context, 2)

        }


        popularDrinksViewModel.getAllPopularDrinks()
        popularDrinksViewModel.popularDrinks.observe(viewLifecycleOwner, Observer {
            popularDrinkAdapter.updateDrinkList(it)

        })


    }

    private fun handleScreenChange(drink: Drink) {
        val action =
            PopularDrinksFragmentDirections.actionNavigationPopularToDrinkClickedFragment(drink)
        findNavController().navigate(action)

    }


}
