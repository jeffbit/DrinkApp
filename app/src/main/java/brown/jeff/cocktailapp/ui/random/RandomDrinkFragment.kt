package brown.jeff.cocktailapp.ui.random

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.util.backPressedToolbar
import brown.jeff.cocktailapp.util.loadImage
import brown.jeff.cocktailapp.util.showSnackBar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.drink_clicked_fragment.*
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient10_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient11_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient12_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient13_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient14_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient15_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient1_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient2_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient3_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient4_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient5_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient6_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient7_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient8_tv
import kotlinx.android.synthetic.main.drink_random_fragment.ingredient9_tv
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider1
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider10
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider11
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider12
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider13
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider14
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider15
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider2
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider3
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider4
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider5
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider6
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider7
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider8
import kotlinx.android.synthetic.main.drink_random_fragment.view_divider9
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomDrinkFragment : Fragment() {


    private val randomDrinkViewModel: RandomDrinkViewModel by viewModel()
    private lateinit var toolbar: Toolbar
    private var drink: Drink? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.drink_clicked_fragment, container, false)
        if (drink == null) {
            randomDrinkViewModel.getRandomDrink()
        }
        toolbar = view?.findViewById(R.id.drink_toolbar)!!
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        backPressedToolbar(toolbar, activity)
        return view
    }

    override fun onResume() {
        super.onResume()
        randomDrinkViewModel.drink.observe(viewLifecycleOwner, Observer {
            toolbar.title = it.drink
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.drink_clicked_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.drinkclicked_favorite_menuitem -> {
                drink?.let { addDrinkToFavorites(this.requireView(), it) }
                true
            }
            R.id.drinkclicked_settings_menuitem -> {
                changeToSettingsScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appbarFadeIn()
        relateddrinkstitle_tv.visibility = View.GONE
        coordinatorLayout.visibility = View.GONE
        observeRandomDrink()
        randomDrinkViewModel.drink.observe(viewLifecycleOwner, Observer {
            favoriteFloatingActionButton(requireView(), it)
        })


    }


    private fun observeRandomDrink() {
        randomDrinkViewModel.drink.observe(viewLifecycleOwner, Observer {
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
            loadImage(drink_imageview, it)
            instructions_tv.text = it.strInstructions
            coordinatorLayout.visibility = View.VISIBLE
            drink = it

        })
    }


    private fun favoriteFloatingActionButton(view: View, drink: Drink) {
        favoritedrink_fab.setOnClickListener {
            addDrinkToFavorites(view, drink)
        }

    }

    private fun addDrinkToFavorites(view: View, drink: Drink) {
        randomDrinkViewModel.addDrinkToFavorites(drink)
        showSnackBar(
            view,
            "Drink favorited ",
            Snackbar.LENGTH_LONG,
            "Undo",
            { randomDrinkViewModel.removeDrinkFromFavorites(drink.idDrink) })
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

    //fades imageview,title,and floating actionbar into toolbar
    private fun appbarFadeIn() {
        var scrollRage = -1
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (scrollRage == -1) {
                scrollRage = appBarLayout?.totalScrollRange!!
            }
            drink_toolbar.menu.findItem(R.id.drinkclicked_favorite_menuitem).isVisible =
                scrollRage + verticalOffset == 0
        })
    }

    private fun changeToSettingsScreen() {
        val action = RandomDrinkFragmentDirections.actionNavigationRandomToSettingsFragment()
        findNavController().navigate(action)
    }


}
