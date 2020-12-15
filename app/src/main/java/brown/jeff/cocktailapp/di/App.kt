package brown.jeff.cocktailapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    viewModelModule,
                    drinkRepositoryModule,
                    favoriteDrinkRepositoryModule
                )
            )
        }

    }
}