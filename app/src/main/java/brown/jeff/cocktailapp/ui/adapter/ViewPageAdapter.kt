package brown.jeff.cocktailapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import brown.jeff.cocktailapp.ui.drinkclicked.DrinkIngredientsFragment
import brown.jeff.cocktailapp.ui.drinkclicked.DrinkInstructionsFragment

const val INGREDIENTS_PAGE_INDEX = 0
const val INSTRUCTIONS_PAGE_INDEX = 1

class ViewPageAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val fragmentList: Map<Int, () -> Fragment> = mapOf(
        INGREDIENTS_PAGE_INDEX to { DrinkIngredientsFragment() },
        INSTRUCTIONS_PAGE_INDEX to { DrinkInstructionsFragment() }
    )

    override fun getItemCount(): Int {
        return fragmentList.size

    }

    override fun createFragment(position: Int): Fragment {
          return fragmentList[position]?.invoke() ?: throw IllegalArgumentException()

    }


}