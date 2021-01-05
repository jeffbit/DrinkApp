package brown.jeff.cocktailapp.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DrinkEntity(
    val idDrink: String,
    @SerializedName("strAlcoholic")
    val alcoholic: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strCreativeCommonsConfirmed")
    val creativeCommonsConfirmed: String,
    @SerializedName("strDrink")
    val drink: String,
    val strDrinkAlternate: String?,
    val strDrinkDE: String?,
    val strDrinkES: String?,
    val strDrinkFR: String?,
    @SerializedName("strDrinkThumb")
    val drinkImg: String,
    @SerializedName("strDrinkZH-HANS")
    val strDrinkZHHANS: String?,
    @SerializedName("strDrinkZH-HANT")
    val strDrinkZHHANT: String?,
    @SerializedName("strGlass")
    val glassType: String?,
    @SerializedName("strIBA")
    val strIBA: String?,
    val strIngredient1: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strInstructions: String,
    val strInstructionsDE: String?,
    val strInstructionsES: String?,
    val strInstructionsFR: String?,
    @SerializedName("strInstructionsZH-HANS")
    val strInstructionsZHHANS: String?,
    @SerializedName("strInstructionsZH-HANT")
    val strInstructionsZHHANT: String?,
    val strMeasure1: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strTags: String?,
    val strVideo: String?,
    val dateModified: String
) : Parcelable


