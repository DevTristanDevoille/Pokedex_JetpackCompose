package fr.tristandevoille.pokedexcompose.commons.helpers

import androidx.annotation.StringRes

interface ResourcesHelper {
    fun getString(@StringRes stringId: Int, formatArgs: Any? = null) : String
}