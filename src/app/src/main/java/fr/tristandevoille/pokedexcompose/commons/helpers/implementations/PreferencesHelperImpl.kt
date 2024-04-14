package fr.tristandevoille.pokedexcompose.commons.helpers.implementations

import android.content.Context
import android.content.SharedPreferences
import fr.tristandevoille.pokedexcompose.commons.helpers.PreferencesHelper
import org.koin.java.KoinJavaComponent.get

class PreferencesHelperImpl(context : Context = get(Context::class.java)) : BasePreferencesHelper(),
    PreferencesHelper {

    override val sharedPreferences: SharedPreferences
            = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    companion object {
        private const val PREFERENCES = "preferences"
        private const val POKEMONS_SYNCHRONIZATION_SYNC_DONE = "pokemons_synchronization_sync_done"
    }

    override var pokemonsSynchronized: Boolean
        get() = getValue(POKEMONS_SYNCHRONIZATION_SYNC_DONE,false)
        set(value) = setValue(POKEMONS_SYNCHRONIZATION_SYNC_DONE, value)
}