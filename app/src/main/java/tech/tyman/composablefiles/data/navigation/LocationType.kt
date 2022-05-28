package tech.tyman.composablefiles.data.navigation

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

class LocationType : NavType<Location>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Location? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Location {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: Location) {
        bundle.putParcelable(key, value)
    }
}
