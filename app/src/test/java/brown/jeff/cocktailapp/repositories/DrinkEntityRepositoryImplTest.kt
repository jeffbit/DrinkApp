package brown.jeff.cocktailapp.repositories


import brown.jeff.cocktailapp.data.repository.DrinkRepositoryImpl
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.model.Drinks
import brown.jeff.cocktailapp.data.api.DrinkApi
import brown.jeff.cocktailapp.data.api.Errors
import brown.jeff.cocktailapp.data.api.NetworkConnection
import brown.jeff.cocktailapp.data.api.Result
import brown.jeff.cocktailapp.data.db.dao.DrinkDao
import brown.jeff.cocktailapp.util.DRINK
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.*
import retrofit2.Call
import retrofit2.Response

class DrinkRepositoryImplTest {

    //Network Calls
    private lateinit var mockDrinkDao: DrinkDao
    private lateinit var mockDrinkApi: DrinkApi
    private lateinit var mockNetworkConnection: NetworkConnection
    private lateinit var mockDrinkCall: Call<Drink>
    private lateinit var mockDrinkResponse: Response<Drink>
    private lateinit var mockDrinksCall: Call<Drinks>
    private lateinit var mockDrinksResponse: Response<Drink>
    private lateinit var drinkRepositoryImpl: DrinkRepositoryImpl


    private val drinkID = DRINK.idDrink


    @Before
    fun setup() {
        mockDrinkApi = mock()
        mockDrinkDao = mock()
        mockNetworkConnection = mock()
        //for single Drink
        mockDrinkCall = mock()
        mockDrinkResponse = mock()
        //for call to Drinks
        mockDrinksCall = mock()
        mockDrinksResponse = mock()
        drinkRepositoryImpl = DrinkRepositoryImpl(mockDrinkApi, mockDrinkDao, mockNetworkConnection)



    }



    @Test
    fun `should return drink when drink id is given`() = runBlocking {
        //given
        val drink = DRINK
        given { mockNetworkConnection.isInternetAvailable() }.willReturn(true)
        given { mockDrinkResponse.isSuccessful }.willReturn(true)
        given { mockDrinkResponse.body() }.willReturn(drink)
        given { mockDrinkCall.execute() }.willReturn(mockDrinkResponse)
        given { mockDrinkApi.getDrinkById(drinkID) }.willReturn(mockDrinksCall)
        //when
        val response = drinkRepositoryImpl.getDrinkById(drinkID)
        //then
        verify(mockDrinkApi).getDrinkById(drinkID)
        Assert.assertEquals(response, Result.Success(drink))

    }

    @Test
    fun `should return network connection error when internet is not available`() = runBlocking {
        //given
        given { mockNetworkConnection.isInternetAvailable() }.willReturn(false)
        //when
        val response = drinkRepositoryImpl.getDrinkById(drinkID)
        //then
        Assert.assertEquals(response, Result.Failure(Errors.NetworkError()))
        println(response)
        println(Result.Failure(Errors.NetworkError()))
    }

    @Test
    fun `should return exception error when input is incorrect`() = runBlocking {
        //given
        given { mockNetworkConnection.isInternetAvailable() }.willReturn(true)
        //when
        val response = drinkRepositoryImpl.getDrinkById("test")
        //then
        val result = Result.Failure(Errors.ExceptionError(NullPointerException()))
        Assert.assertEquals(response, result)
        println(response)
        println(result)


    }

    @Test
    fun `should return response error with api call is unsuccessful`() = runBlocking {
        //given
        given { mockNetworkConnection.isInternetAvailable() }.willReturn(true)
        given { mockDrinkResponse.isSuccessful }.willReturn(false)
        given { mockDrinkCall.execute() }.willReturn(mockDrinkResponse)
        given { mockDrinkApi.getDrinkById(drinkID) }.willReturn(mockDrinksCall)
        //then
        val response = drinkRepositoryImpl.getDrinkById(drinkID)
        //when
        val result = Result.Failure(Errors.ResponseError("null"))
        Assert.assertEquals(response, result)
        println(response)
        println(result)


    }

}