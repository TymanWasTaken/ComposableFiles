package tech.tyman.composablefiles.data.navigation

import android.os.Bundle
import androidx.navigation.NavType

class LocationType : NavType<Location>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Location? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Location {
        return GSON
    }
}