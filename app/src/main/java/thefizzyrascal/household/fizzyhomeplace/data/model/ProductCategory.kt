package thefizzyrascal.household.fizzyhomeplace.data.model

import androidx.annotation.StringRes
import thefizzyrascal.household.fizzyhomeplace.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    KITCHEN(R.string.fotqv_category_kitchen),
    TEXTILES(R.string.fotqv_category_textiles),
    DECOR(R.string.fotqv_category_decor),
    STORAGE(R.string.fotqv_category_storage),
    CLEANING(R.string.fotqv_category_cleaning),
}
