package com.intknight.pokedex.doa

import android.graphics.Bitmap

data class PokemonModel (

    var name    : String            = "DEFAULT_MODEL",
    var sprite  : ArrayList<Bitmap> = ArrayList(),
    var stats   : ArrayList<String> = ArrayList(),
    var type    : String            = "DEFAULT_MODEL",
    var weight  : String            = "DEFAULT_MODEL",
    var entry   : String            = "DEFAULT_MODEL",
    var shape   : String            = "DEFAULT_MODEL"

)