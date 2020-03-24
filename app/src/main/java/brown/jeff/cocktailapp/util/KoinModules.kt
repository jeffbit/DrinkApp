package brown.jeff.cocktailapp.util

import brown.jeff.cocktailapp.model.DrinkRepository
import brown.jeff.cocktailapp.network.NetworkConnection
import brown.jeff.cocktailapp.network.RetrofitClient
import brown.jeff.cocktailapp.ui.favorite.FavoriteDrinksViewModel
import brown.jeff.cocktailapp.ui.popular.PopularDrinksViewModel
import brown.jeff.cocktailapp.ui.recent.SearchRecentDrinksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    single { DrinkRepository(RetrofitClient.cocktailApi, get(), get()) }
    single { NetworkConnection(get()) }
    viewModel { FavoriteDrinksViewModel() }
    viewModel { PopularDrinksViewModel() }
    viewModel { SearchRecentDrinksViewModel() }
}