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
        private const val LOCATIONS_SYNCHRONIZATION_SYNC_DONE = "locations_synchronization_sync_done"
    }
}