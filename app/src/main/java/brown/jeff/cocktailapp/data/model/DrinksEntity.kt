package brown.jeff.cocktailapp.data.model

import com.google.gson.annotations.SerializedName


data class DrinksEntity(
    @SerializedName("drinks")
    val drinks: List<DrinkEntity>
)