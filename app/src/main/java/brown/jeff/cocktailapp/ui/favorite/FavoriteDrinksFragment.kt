package brown.jeff.cocktailapp.ui.favorite

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
import brown.jeff.cocktailapp.util.Constants
import brown.jeff.cocktailapp.util.changeRecyclerViewLayout
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteDrinksFragment : Fragment() {


    private val favoriteDrinksViewModel: FavoriteDrinksViewModel by viewModel()
    private lateinit var favoriteDrinkAdapter: DrinkAdapter
    private var isAlertDisplayed: Boolean = false


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
                changeToSettingScreen()
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
        favoriteDrinksViewModel.getFavoriteDrinks()
        favoriteDrinkAdapter = DrinkAdapter(
            emptyList()
        ) { id: String -> handleScreenChange(id) }

        recyclerview_favorites.apply {
            adapter = favoriteDrinkAdapter
            setHasFixedSize(true)
            activity?.let { changeRecyclerViewLayout(context, it, this) }

        }

        if (savedInstanceState != null) {
            val isDisplayed = savedInstanceState.getBoolean(Constants.IS_ALERT_KEY)
            if (isDisplayed) {
                deleteAllDrinks()
            }
        }

        favoriteDrinkAdapter.drinkSelectedListener = object : DrinkAdapter.DrinkSelectedListener {
            override fun onDrinkSelected(drink: Drink, imageView: ImageView) {
                val extras = FragmentNavigatorExtras(imageView to drink.drinkImg)
            }
        }



        favoriteDrinksViewModel.favoriteDrinks.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                errormessage_tv_favorites.visibility = View.VISIBLE
            } else {
                errormessage_tv_favorites.visibility = View.GONE
            }
            favoriteDrinkAdapter.updateDrinkList(it)
        })

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(Constants.IS_ALERT_KEY, isAlertDisplayed)
    }


    private fun handleScreenChange(id: String) {
        val action =
            FavoriteDrinksFragmentDirections.favoritesToDrinkclicked(
                id
            )
        findNavController().navigate(action)

    }


    private fun deleteAllDrinks() {
        showAlertDialogFavorites()
    }


    //show alert for favorites fragment
    private fun showAlertDialogFavorites() {
        isAlertDisplayed = true
        val builder = AlertDialog.Builder(requireActivity(), R.style.AlertDialog)
        builder.setMessage(getString(R.string.delete_drinks_confirmation))
            ?.setPositiveButton(
                getString(R.string.confirm)
            ) { dialog, _ ->
                favoriteDrinksViewModel.deleteAllDrinks()
                isAlertDisplayed = false
                dialog.dismiss()
            }
            ?.setNegativeButton(
                getString(R.string.cancel)
            ) { dialog, _ ->
                isAlertDisplayed = false
                dialog.cancel()
            }

        val alert = builder.create()
        alert.setTitle(getString(R.string.delete_drinks))
        alert.show()


    }

    private fun changeToSettingScreen() {
        val action = FavoriteDrinksFragmentDirections.favoritesToSettings()
        findNavController().navigate(action)
    }


}
