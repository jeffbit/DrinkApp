package brown.jeff.cocktailapp.ui.random

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import brown.jeff.cocktailapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomDrinkFragment : Fragment() {


    private val randomDrinkViewModel: RandomDrinkViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drink_random_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.random_drink_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.random_menuitem -> {
                randomDrinkViewModel.getRandomDrink()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        randomDrinkViewModel.getRandomDrink()


    }


}
