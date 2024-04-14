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
        private const val POKEMON_TOTAL = "pokemon_total"
        private const val POKEMON_OFFSET = "pokemon_offset"
        private const val POKEMON_LIMIT = "pokemon_limit"
        private const val TYPES_SYNCHRONIZATION_SYNC_DONE = "types_synchronization_sync_done"
    }

    override var pokemonsSynchronized: Boolean
        get() = getValue(POKEMONS_SYNCHRONIZATION_SYNC_DONE,false)
        set(value) = setValue(POKEMONS_SYNCHRONIZATION_SYNC_DONE, value)

    override var pokemonTotal: Int
        get() = getValue(POKEMON_TOTAL,0)
        set(value) = setValue(POKEMON_TOTAL,value)

    override var pokemonOffset: Int
        get() = getValue(POKEMON_OFFSET,0)
        set(value) = setValue(POKEMON_OFFSET,value)

    override val pokemonLimit: Int
        get() = getValue(POKEMON_LIMIT,100)

    override var typesSynchronized: Boolean
        get() = getValue(TYPES_SYNCHRONIZATION_SYNC_DONE,false)
        set(value) = setValue(TYPES_SYNCHRONIZATION_SYNC_DONE,value)
}