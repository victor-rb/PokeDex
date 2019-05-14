package com.intknight.pokedex.doa

import com.intknight.pokedex.dto.PokemonDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{index}")
    fun getPokemonService(@Path("index") index : String) : Call<PokemonDTO>

}