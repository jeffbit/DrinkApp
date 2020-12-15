package brown.jeff.cocktailapp.di

import android.app.Application
import androidx.room.Room
import brown.jeff.cocktailapp.network.DrinkApi
import brown.jeff.cocktailapp.network.NetworkConnection
import brown.jeff.cocktailapp.network.RetrofitClient
import brown.jeff.cocktailapp.repositories.DrinkRepositoryImpl
import brown.jeff.cocktailapp.repositories.FavoriteDrinkRepositoryImpl
import brown.jeff.cocktailapp.room.DrinkDao
import brown.jeff.cocktailapp.room.DrinkDatabase
import brown.jeff.cocktailapp.ui.adapter.DrinkAdapter
import brown.jeff.cocktailapp.ui.drinkclicked.DrinkClickedViewModel
import brown.jeff.cocktailapp.ui.favorite.FavoriteDrinksViewModel
import brown.jeff.cocktailapp.ui.popular.PopularDrinksViewModel
import brown.jeff.cocktailapp.ui.random.RandomDrinkViewModel
import brown.jeff.cocktailapp.ui.search.SearchRecentDrinksViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module(override = true) {

    fun provideRetrofit(): DrinkApi {
        return RetrofitClient.cocktailApi
    }
    single { provideRetrofit() }

    factory { NetworkConnection(context = get()) }
    factory { DrinkAdapter(drinks = get(), clickListener = get()) }


}
val drinkRepositoryModule = module {
    single {
        DrinkRepositoryImpl(
            drinkApi = get(),
            networkConnection = get()
        )
    }

}

val favoriteDrinkRepositoryModule = module {
    single {
        FavoriteDrinkRepositoryImpl(drinkDao = get())
    }
}

val databaseModule = module {


    fun provideDatabase(application: Application): DrinkDatabase {
        return Room.databaseBuilder(application, DrinkDatabase::class.java, "drink.database")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(database: DrinkDatabase): DrinkDao {
        return database.drinkDao()
    }

    single { provideDatabase(application = androidApplication()) }
    single { provideDao(database = get()) }

}
val viewModelModule = module {
    viewModel {
        FavoriteDrinksViewModel(
            favoriteDrinkRepositoryImpl = get()
        )
    }
    viewModel {
        RandomDrinkViewModel(
            drinkRepositoryImpl = get(),
            favoriteDrinkRepositoryImpl = get()
        )
    }
    viewModel {
        PopularDrinksViewModel(
            drinkRepositoryImpl = get()
        )
    }
    viewModel {
        SearchRecentDrinksViewModel(
            drinkRepositoryImpl = get()
        )
    }
    viewModel {
        DrinkClickedViewModel(
            drinkRepositoryImpl = get(),
            favoriteDrinkRepositoryImpl = get()
        )
    }


}