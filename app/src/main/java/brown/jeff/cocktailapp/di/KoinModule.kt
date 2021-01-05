package brown.jeff.cocktailapp.di

import android.app.Application
import androidx.room.Room
import brown.jeff.cocktailapp.data.api.DrinkApi
import brown.jeff.cocktailapp.data.api.NetworkConnection
import brown.jeff.cocktailapp.data.api.RetrofitClient
import brown.jeff.cocktailapp.data.repository.DrinkRepositoryImpl
import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.repository.FavoriteDrinkRepositoryImpl
import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.dao.DrinkDao
import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.DrinkDatabase
import brown.jeff.cocktailapp.features.drinklist.ui.adapter.DrinkAdapter
import brown.jeff.cocktailapp.features.drinkdetail.viewmodel.DrinkClickedViewModel
import brown.jeff.cocktailapp.features.favoritedrink.viewmodel.FavoriteDrinksViewModel
import brown.jeff.cocktailapp.features.drinklist.viewmodel.PopularDrinksViewModel
import brown.jeff.cocktailapp.features.randomdrink.viewmodel.RandomDrinkViewModel
import brown.jeff.cocktailapp.features.searchdrink.viewmodel.SearchRecentDrinksViewModel
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