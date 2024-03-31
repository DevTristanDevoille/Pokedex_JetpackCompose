package fr.tristandevoille.pokedexcompose.commons.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toPrettyDate() : String {
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH)
    return dateFormatter.format(this)
}