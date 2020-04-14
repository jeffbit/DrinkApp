package brown.jeff.cocktailapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import brown.jeff.cocktailapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())


        navView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_popular,
                R.id.navigation_recent,
                R.id.navigation_favorites,
                R.id.navigation_random
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        //hides bottom navigation view for DrinkClickedFragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.drinkClickedFragment) {
                navView.visibility = View.GONE
            } else {
                navView.visibility = View.VISIBLE
            }
        }

//        navView.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.navigation_popular -> {
//                    loadFragment(PopularDrinksFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.navigation_recent -> {
//                    loadFragment(SearchRecentDrinksFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.navigation_favorites -> {
//                    loadFragment(FavoriteDrinksFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.navigation_random -> {
//                    loadFragment(RandomDrinkFragment())
//                    return@setOnNavigationItemSelectedListener true
//                }
//            }
//            false
//        }
//
//
//    }
//
//    private fun loadFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager.beginTransaction()
//        fragmentManager.replace(R.id.nav_host_fragment, fragment)
//        fragmentManager.addToBackStack(null)
//        fragmentManager.commit()
//
    }


}
