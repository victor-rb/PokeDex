package com.intknight.pokedex.doa

import retrofit2.Call
import retrofit2.http.GET
import com.intknight.pokedex.dto.PokeListDTO

interface PokeListService {

    @GET("pokemon/?limit=802")
    fun getPokemonItem() : Call<PokeListDTO>
}