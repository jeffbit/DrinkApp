package brown.jeff.cocktailapp.repositories


import brown.jeff.cocktailapp.data.api.DrinkApi
import brown.jeff.cocktailapp.data.api.NetworkConnection
import brown.jeff.cocktailapp.data.db.dao.DrinkDao
import brown.jeff.cocktailapp.data.entities.DrinkEntity
import brown.jeff.cocktailapp.data.entities.DrinksEntity
import brown.jeff.cocktailapp.data.repository.DrinkRepositoryImpl
import brown.jeff.cocktailapp.domain.usecase.Errors
import brown.jeff.cocktailapp.domain.usecase.Result
import brown.jeff.cocktailapp.util.DRINK_ENTITY
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class DrinkEntityRepositoryImplTest {

    //Network Calls
    private lateinit var mockDrinkDao: DrinkDao
    private lateinit var mockDrinkApi: DrinkApi
    private lateinit var mockNetworkConnection: NetworkConnection
    private lateinit var mockDrinkEntityCall: Call<DrinkEntity>
    private lateinit var mockDrinkEntityResponse: Response<DrinkEntity>
    private lateinit var mockDrinksEntityCall: Call<DrinksEntity>
    private lateinit var mockDrinksResponse: Response<DrinkEntity>
    private lateinit var drinkRepositoryImpl: DrinkRepositoryImpl


    private val drinkID = DRINK_ENTITY.idDrink


    @Before
    fun setup() {
        mockDrinkApi = mock()
        mockDrinkDao = mock()
        mockNetworkConnection = mock()
        //for single Drink
        mockDrinkEntityCall = mock()
        mockDrinkEntityResponse = mock()
        //for call to Drinks
        mockDrinksEntityCall = mock()
        mockDrinksResponse = mock()
        drinkRepositoryImpl = DrinkRepositoryImpl(
            drinkApi = mockDrinkApi,
            networkConnection = mockNetworkConnection
        )


    }


    @Test
    fun `should return drink when drink id is given`() = runBlocking {
        //given
        val drink = DRINK_ENTITY
        given { mockNetworkConnection.isInternetAvailable() }.willReturn(true)
        given { mockDrinkEntityResponse.isSuccessful }.willReturn(true)
        given { mockDrinkEntityResponse.body() }.willReturn(drink)
        given { mockDrinkEntityCall.execute() }.willReturn(mockDrinkEntityResponse)
        given { mockDrinkApi.getDrinkById(drinkID) }.willReturn(mockDrinksEntityCall)
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
        given { mockDrinkEntityResponse.isSuccessful }.willReturn(false)
        given { mockDrinkEntityCall.execute() }.willReturn(mockDrinkEntityResponse)
        given { mockDrinkApi.getDrinkById(drinkID) }.willReturn(mockDrinksEntityCall)
        //then
        val response = drinkRepositoryImpl.getDrinkById(drinkID)
        //when
        val result = Result.Failure(Errors.ResponseError("null"))
        Assert.assertEquals(response, result)
        println(response)
        println(result)


    }

}