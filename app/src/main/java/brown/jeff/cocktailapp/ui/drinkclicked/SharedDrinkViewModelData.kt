package brown.jeff.cocktailapp.ui.drinkclicked

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import brown.jeff.cocktailapp.model.Drink

class SharedDrinkViewModelData {


    private val _drink = MutableLiveData<Drink>()
    val drink: LiveData<Drink>
        get() = _drink


    fun setDrink(drink: Drink) {
        _drink.postValue(drink)
    }


}