package brown.jeff.cocktailapp.ui.favorite

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
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

        if (savedInstanceState != null) {
            val isDisplayed = savedInstanceState.getBoolean("isAlertDisplayed")
            if (isDisplayed) {
                deleteAllDrinks()
            }
        }


        favoriteDrinksViewModel.getDrinks.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNullOrEmpty()) {
                    errormessage_tv_favorites.visibility = View.VISIBLE
                } else {
                    errormessage_tv_favorites.visibility = View.GONE
                }
                favoriteDrinkAdapter.updateDrinkList(it)

            }
        })

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isAlertDisplayed", isAlertDisplayed)
    }


    private fun handleScreenChange(drink: Drink) {
        val action =
            FavoriteDrinksFragmentDirections.actionNavigationFavoritesToDrinkClickedFragment(drink)
        findNavController().navigate(action)

    }

    private fun showErrorMessage(drinkList : List<Drink>){

    }
    private fun deleteAllDrinks() {
        showAlertDialogFavorites()
    }


    //show alert for favorites fragment
    private fun showAlertDialogFavorites() {
        isAlertDisplayed = true
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Delete all Drinks")
            ?.setPositiveButton("Confirm", DialogInterface.OnClickListener { dialog, _ ->
                favoriteDrinksViewModel.deleteAllDrinks()
                isAlertDisplayed = false
                dialog.dismiss()
            })
            ?.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                isAlertDisplayed = false
                dialog.cancel()
            })

        val alert = builder.create()
        alert.setTitle("Are you sure")
        alert.show()


    }


}
