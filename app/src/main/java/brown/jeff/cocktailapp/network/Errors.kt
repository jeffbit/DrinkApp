package brown.jeff.cocktailapp.network


//used with Result class to catch specific failures

sealed class Errors {

    data class NetworkError(val error: String = "No Network Connection") : Errors()
    data class ExceptionError(val exception: Throwable) : Errors()
    data class ResponseError(val error: String) : Errors()


}
