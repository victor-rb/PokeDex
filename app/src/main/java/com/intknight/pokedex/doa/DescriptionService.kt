package com.intknight.pokedex.doa

import com.intknight.pokedex.dto.DescriptionDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DescriptionService {

    @GET("pokemon-species/{index}")
    fun getDescription(@Path("index") index : String) : Call<DescriptionDTO>
}