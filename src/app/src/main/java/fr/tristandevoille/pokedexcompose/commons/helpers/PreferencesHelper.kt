package fr.tristandevoille.pokedexcompose.commons.helpers

interface PreferencesHelper {

    var pokemonsSynchronized : Boolean
    var pokemonTotal : Int
    var pokemonOffset : Int
    val pokemonLimit : Int
    var typesSynchronized : Boolean
}