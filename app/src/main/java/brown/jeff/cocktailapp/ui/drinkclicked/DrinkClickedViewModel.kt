package brown.jeff.cocktailapp.ui.drinkclicked

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import brown.jeff.cocktailapp.model.Drink

class DrinkClickedViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _drink = MutableLiveData<Drink>()
    val drink: LiveData<Drink>
        get() = _drink


    fun setDrinkValue(drink: Drink) {
        _drink.postValue(drink)
    }


}
