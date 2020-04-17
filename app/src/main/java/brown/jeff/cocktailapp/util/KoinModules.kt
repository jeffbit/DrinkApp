package brown.jeff.cocktailapp.util

import brown.jeff.cocktailapp.network.NetworkConnection
import brown.jeff.cocktailapp.network.RetrofitClient
import brown.jeff.cocktailapp.repositories.DrinkRepository
import brown.jeff.cocktailapp.room.DrinkDatabase
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
import brown.jeff.cocktailapp.ui.drinkclicked.DrinkClickedViewModel
import brown.jeff.cocktailapp.ui.drinkclicked.DrinkIngredientsViewModel
import brown.jeff.cocktailapp.ui.drinkclicked.DrinkInstructionsViewModel
import brown.jeff.cocktailapp.ui.drinkclicked.SharedDrinkViewModelData
import brown.jeff.cocktailapp.ui.favorite.FavoriteDrinksViewModel
import brown.jeff.cocktailapp.ui.popular.PopularDrinksViewModel
import brown.jeff.cocktailapp.ui.random.RandomDrinkViewModel
import brown.jeff.cocktailapp.ui.search.SearchRecentDrinksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module(override = true) {
    single {
        DrinkRepository(
            RetrofitClient.cocktailApi,
            DrinkDatabase.invoke(get()).drinkDao(),
            get()
        )
    }
    single { DrinkDatabase }
    single { SharedDrinkViewModelData() }

    factory { NetworkConnection(get()) }
    factory { DrinkAdapter(get(), get()) }

    viewModel { FavoriteDrinksViewModel(get()) }
    viewModel { RandomDrinkViewModel(get()) }
    viewModel { PopularDrinksViewModel(get()) }
    viewModel { SearchRecentDrinksViewModel(get()) }
    viewModel { DrinkClickedViewModel(get(), get()) }
    viewModel { DrinkInstructionsViewModel(get()) }
    viewModel { DrinkIngredientsViewModel(get()) }

}