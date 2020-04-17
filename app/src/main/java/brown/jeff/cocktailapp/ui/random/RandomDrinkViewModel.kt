package brown.jeff.cocktailapp.ui.random

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.network.Result
import brown.jeff.cocktailapp.repositories.DrinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class RandomDrinkViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {

    private val _drink = MutableLiveData<Drink>()
    val drink: LiveData<Drink>
        get() = _drink

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    fun getRandomDrink() {
        _loading.value = true
        viewModelScope.launch {
                when (val result = drinkRepository.getRandomDrink()) {
                    is Result.Success -> {
                        _loading.postValue(false)
                        _drink.postValue(result.data)
                        Timber.i("Success")
                    }
                    is Result.Failure -> {
                        _loading.postValue(false)
                        Timber.e(result.errors.toString())
                        _errorMessage.value = result.errors.toString()
                    }



            }
        }
    }

}
