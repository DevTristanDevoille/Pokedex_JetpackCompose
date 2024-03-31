package fr.tristandevoille.pokedexcompose.commons.extensions

import androidx.navigation.NavBackStackEntry
import fr.tristandevoille.pokedexcompose.R
import fr.tristandevoille.pokedexcompose.commons.helpers.ResourcesHelper
import org.koin.java.KoinJavaComponent.get

private val resourcesHelper : ResourcesHelper = get(ResourcesHelper::class.java)

fun NavBackStackEntry.getTitle() : String {
    return when(this.destination.route){
        "pokemons" -> resourcesHelper.getString(R.string.pokemons)
        else -> ""
    }
}

fun NavBackStackEntry.getSubtitle() : String {
    return when(this.destination.route){
        "characters/{id}?name={name}" -> {
            val characterName = this.arguments?.getString("name") ?: ""
            characterName
        }
        else -> ""
    }
}