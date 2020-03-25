package brown.jeff.cocktailapp.ui.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import brown.jeff.cocktailapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchRecentDrinksFragment : Fragment() {

    private val searchRecentDrinksViewModel: SearchRecentDrinksViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
