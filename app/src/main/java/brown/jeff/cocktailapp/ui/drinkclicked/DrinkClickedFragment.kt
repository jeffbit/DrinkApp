package brown.jeff.cocktailapp.ui.drinkclicked

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.ui.adapter.INGREDIENTS_PAGE_INDEX
import brown.jeff.cocktailapp.ui.adapter.INSTRUCTIONS_PAGE_INDEX
import brown.jeff.cocktailapp.ui.adapter.ViewPageAdapter
import brown.jeff.cocktailapp.util.loadImage
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.drink_clicked_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrinkClickedFragment : Fragment(R.layout.drink_clicked_fragment) {


    private val drinkClickedViewModel: DrinkClickedViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.drink_clicked_menu, menu)
        //todo: Figure out how to change favorite icon when clicked.Currently working with selector when pressed but will not stay when released.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPageAdapter(view)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retrieveSafeArgs()
        loadDrinkIntoView()

    }


    //observes drink from viewmodel
    private fun loadDrinkIntoView() {
        drinkClickedViewModel.drink.observe(viewLifecycleOwner, Observer {
            loadImage(drinkimage_imageview, it)
            //drinkname_textview.text = it.drink
        })
    }


    //retrieves data from previous fragment and sets viewmodel data
    private fun retrieveSafeArgs() {
        val safeArgs: DrinkClickedFragmentArgs by navArgs()
        val drinkPassed = safeArgs.passDrink
        drinkClickedViewModel.setDrinkValue(drinkPassed)

    }

    //setups viewpager2 with adapter
    private fun initViewPageAdapter(view: View) {
        val tabLayout: TabLayout = view.findViewById(R.id.viewpager_tablayout)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        viewPager.adapter = ViewPageAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabText(position)
        }.attach()
    }

    private fun getTabText(position: Int): String? {
        return when (position) {
            INGREDIENTS_PAGE_INDEX -> getString(R.string.ingredients)
            INSTRUCTIONS_PAGE_INDEX -> getString(R.string.instructions)
            else -> null
        }
    }


}
