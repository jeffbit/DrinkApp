package brown.jeff.cocktailapp.ui.favorite

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
import brown.jeff.cocktailapp.util.changeRecyclerViewLayout
import brown.jeff.cocktailapp.util.showAlertDialog
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteDrinksFragment : Fragment() {


    private val favoriteDrinksViewModel: FavoriteDrinksViewModel by viewModel()
    private lateinit var favoriteDrinkAdapter: DrinkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.favorite_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favoritesettings_menuitem -> {
                Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show();
                true
            }
            R.id.favoriteclear_menuitem -> {
                deleteAllDrinks()
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
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        //sets the toolbar in the fragment
        val toolbar = view?.findViewById<Toolbar>(R.id.favorite_toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteDrinkAdapter = DrinkAdapter(
            emptyList()
        ) { drink: Drink -> handleScreenChange(drink) }

        recyclerview_favorites.apply {
            adapter = favoriteDrinkAdapter
            setHasFixedSize(true)
            activity?.let { changeRecyclerViewLayout(context, it, this) }

        }
        favoriteDrinksViewModel.getDrinks


        //todo: Fragment will not inflate with empty list, figure out how to and display error message if nothing has been added to favorites.
        favoriteDrinksViewModel.getDrinks.observe(viewLifecycleOwner, Observer {
            it?.let {
                favoriteDrinkAdapter.updateDrinkList(it)
            }
            errormessage_tv_favorites.visibility = View.GONE
        })

        favoriteDrinksViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            errormessage_tv_favorites.text = it
            errormessage_tv_favorites.visibility = View.VISIBLE

        })

    }


    private fun handleScreenChange(drink: Drink) {
        val action =
            FavoriteDrinksFragmentDirections.actionNavigationFavoritesToDrinkClickedFragment(drink)
        findNavController().navigate(action)

    }

    private fun deleteAllDrinks() {
        context?.let {
            showAlertDialog(
                it, "Delete all drinks"
            ) { favoriteDrinksViewModel.deleteAllDrinks() }
        }
    }


}
