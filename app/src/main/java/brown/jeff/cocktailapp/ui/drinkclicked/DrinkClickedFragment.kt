package brown.jeff.cocktailapp.ui.drinkclicked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.ui.adapter.ViewPageAdapter
import brown.jeff.cocktailapp.util.loadImage
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.drink_clicked_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrinkClickedFragment : Fragment() {


    private val drinkClickedViewModel: DrinkClickedViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drink_clicked_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPageAdapter(view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retrieveSafeArgs()
        loadDrinkIntoView()
        changeFavoriteButtonIcon()

    }


    //observes drink from viewmodel
    private fun loadDrinkIntoView() {
        drinkClickedViewModel.drink.observe(viewLifecycleOwner, Observer {
            loadImage(drinkimage_imageview, it)
            drinkname_textview.text = it.drink
        })
    }


    //retrieves data from previous fragment and sets viewmodel data
    private fun retrieveSafeArgs() {
        val safeArgs: DrinkClickedFragmentArgs by navArgs()
        val drinkPassed = safeArgs.passDrink
        drinkClickedViewModel.setDrinkValue(drinkPassed)

    }

    private fun initViewPageAdapter(view: View) {
        val tabLayout: TabLayout = view.findViewById(R.id.viewpager_tablayout)
        val viewPager: ViewPager = view.findViewById(R.id.viewPager)
        val viewPageAdapter = ViewPageAdapter(
            childFragmentManager
        )
        viewPageAdapter.addFragment(
            DrinkIngredientsFragment(
                drinkClickedViewModel
            ), "Ingredients")
        viewPageAdapter.addFragment(
            DrinkInstructionsFragment(
                drinkClickedViewModel
            ), "Instructions")
        viewPager.adapter = viewPageAdapter


        tabLayout.setupWithViewPager(viewPager)

    }

    private fun changeFavoriteButtonIcon() {
        var flag = true

        floatingActionButton.setOnClickListener {
            if (flag) {
                floatingActionButton.setImageDrawable(context?.let {
                    it
                    ContextCompat.getDrawable(
                        it, R.drawable.ic_favorite_border_black_24dp
                    )
                })
            } else {
                floatingActionButton.setImageDrawable(context?.let {
                    ContextCompat.getDrawable(
                        it, R.drawable.ic_favorite_black_24dp
                    )
                })
            }
        }
    }


}
