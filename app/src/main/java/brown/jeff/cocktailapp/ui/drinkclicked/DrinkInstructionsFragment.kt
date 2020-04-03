package brown.jeff.cocktailapp.ui.drinkclicked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.ui.drinkclicked.DrinkClickedViewModel
import kotlinx.android.synthetic.main.drink_instructions_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrinkInstructionsFragment(private val drinkClickedViewModel: DrinkClickedViewModel) : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.drink_instructions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        drinkClickedViewModel.drink.observe(viewLifecycleOwner, Observer {
            instructions_textview.text = it.strInstructions
        })
    }


}
