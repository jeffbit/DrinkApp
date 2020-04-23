package brown.jeff.cocktailapp.ui.drinkclicked

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.util.loadImage
import brown.jeff.cocktailapp.util.showSnackBar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.drink_clicked_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrinkClickedFragment : Fragment(R.layout.drink_clicked_fragment) {


    private val drinkClickedViewModel: DrinkClickedViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setHasOptionsMenu(true)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.settings_menu, menu)
        //todo: Figure out how to change favorite icon when clicked.
        // Currently working with selector when pressed but will not stay when released.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        var toolbar = view?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.drink_toolbar)
//         (activity as AppCompatActivity).setSupportActionBar(toolbar)

        return super.onCreateView(inflater, container, savedInstanceState)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retrieveSafeArgs()
        loadDrinkIntoView()
        favoriteFloatingActionButton(view!!, retrieveDrink())

    }


    //observes drink from viewmodel
    private fun loadDrinkIntoView() {
        drinkClickedViewModel.drink.observe(viewLifecycleOwner, Observer {
            loadImage(drink_imageview, it)
            drinkname_textview.text = it.drink
            setIngredients(it.strMeasure1, it.strIngredient1, ingredient1_tv, view_divider1)
            setIngredients(it.strMeasure2, it.strIngredient2, ingredient2_tv, view_divider2)
            setIngredients(it.strMeasure3, it.strIngredient3, ingredient3_tv, view_divider3)
            setIngredients(it.strMeasure4, it.strIngredient4, ingredient4_tv, view_divider4)
            setIngredients(it.strMeasure5, it.strIngredient5, ingredient5_tv, view_divider5)
            setIngredients(it.strMeasure6, it.strIngredient6, ingredient6_tv, view_divider6)
            setIngredients(it.strMeasure7, it.strIngredient7, ingredient7_tv, view_divider7)
            setIngredients(it.strMeasure8, it.strIngredient8, ingredient8_tv, view_divider8)
            setIngredients(it.strMeasure9, it.strIngredient9, ingredient9_tv, view_divider9)
            setIngredients(it.strMeasure10, it.strIngredient10, ingredient10_tv, view_divider10)
            setIngredients(it.strMeasure11, it.strIngredient11, ingredient11_tv, view_divider11)
            setIngredients(it.strMeasure12, it.strIngredient12, ingredient12_tv, view_divider12)
            setIngredients(it.strMeasure13, it.strIngredient13, ingredient13_tv, view_divider13)
            setIngredients(it.strMeasure14, it.strIngredient14, ingredient14_tv, view_divider14)
            setIngredients(it.strMeasure15, it.strIngredient15, ingredient15_tv, view_divider15)
            instructions_tv.text = it.strInstructions
        })
    }


    private fun favoriteFloatingActionButton(view: View, drink: Drink) {
        favoritedrink_fab.setOnClickListener {
            addTofavoriteSnackBar(view, drink)
        }

    }

    //retrieves data from previous fragment and sets viewmodel data
    private fun retrieveSafeArgs() {
        val safeArgs: DrinkClickedFragmentArgs by navArgs()
        val drinkPassed = safeArgs.passDrink
        drinkClickedViewModel.setDrinkValue(drinkPassed)

    }

    private fun retrieveDrink(): Drink {
        val safeArgs: DrinkClickedFragmentArgs by navArgs()
        return safeArgs.passDrink

    }

    private fun addTofavoriteSnackBar(view: View, drink: Drink) {
        drinkClickedViewModel.addDrinkToFavorites(drink)
        showSnackBar(
            view,
            "Drink favorited ",
            Snackbar.LENGTH_LONG,
            "Undo",
            { drinkClickedViewModel.removeDrinkFromFavorites(drink.idDrink) }
        )

    }


    private fun setIngredients(
        measure: String?,
        ingredientName: String?,
        textView: TextView,
        view: View
    ) {
        if (ingredientName != null && measure != null) {
            textView.visibility = View.VISIBLE
            view.visibility = View.VISIBLE
            textView.text =
                getString(R.string.set_ingredients_and_measurements, measure, ingredientName)
        } else {
            textView.visibility = View.GONE
            view.visibility = View.GONE
        }
    }


    private fun appbarFadeIn(){
        var appBarExpanded = false
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if(Math.abs(verticalOffset) > 200){
                appBarExpanded = false
                invalidateOptionsMenu()
            }else{
                appBarExpanded = true
                invalidateOptionsMenu()
            }
        })
        }
    }

private fun invalidateOptionsMenu(){

}

}
