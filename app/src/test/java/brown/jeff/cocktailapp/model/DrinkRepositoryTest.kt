package brown.jeff.cocktailapp.model

import brown.jeff.cocktailapp.network.DrinkApi
import brown.jeff.cocktailapp.network.NetworkConnection
import brown.jeff.cocktailapp.room.DrinkDao
import org.junit.Before
import retrofit2.Call
import retrofit2.Response

class DrinkRepositoryTest {
    private lateinit var mockDrinkApi: DrinkApi
    private lateinit var mockNetworkConnection: NetworkConnection
    private lateinit var mockDrinkDao: DrinkDao
    private lateinit var mockDrinkResponse: Response<Drinks>
    private lateinit var mockDrinkCall: Call<Drinks>
    private lateinit var drinkRepository: DrinkRepository


    @Before
    fun setup() {

    }


}