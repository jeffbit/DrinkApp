package brown.jeff.cocktailapp.model

import com.google.gson.annotations.SerializedName


data class DrinksEntity(
    @SerializedName("drinks")
    val drinks: List<DrinkEntity>
)