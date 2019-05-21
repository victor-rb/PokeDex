package com.intknight.pokedex.dto

import com.google.gson.annotations.SerializedName

class PokeListDTO {
    @SerializedName("results") var result = ArrayList<Result>()
}

class Result{
    @SerializedName("name") var name : String = "DEFAULT_MODEL"
}