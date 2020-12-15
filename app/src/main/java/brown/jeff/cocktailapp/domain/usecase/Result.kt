package brown.jeff.cocktailapp.domain.usecase

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val errors: Errors) : Result<Nothing>()

}