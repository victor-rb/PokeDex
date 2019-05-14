package com.intknight.pokedex.caller

import android.graphics.BitmapFactory
import android.os.AsyncTask.execute
import android.util.Log
import com.intknight.pokedex.doa.DescriptionService
import com.intknight.pokedex.doa.PokemonModel
import com.intknight.pokedex.doa.PokemonService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

object Request {

    val pokemon = PokemonModel()
    

    private fun requestPokemon(index: String) : PokemonModel{
        
        val baseUrl = "https://pokeapi.co/api/v2/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val pokeService = retrofit.create(PokemonService::class.java)
        val descService = retrofit.create(DescriptionService::class.java)

        val callPoke = pokeService.getPokemonService(index)

        execute {
            val body = callPoke.execute().body() ?: return@execute


            pokemon.name = body.name


            pokemon.sprite.clear()
            pokemon.sprite.add(BitmapFactory.decodeStream(URL(body.sprites?.frontDefault).openStream()))
            if (body.sprites?.backDefault != null)pokemon.sprite.add(BitmapFactory.decodeStream(URL(body.sprites?.backDefault).openStream()))
            pokemon.sprite.add(BitmapFactory.decodeStream(URL(body.sprites?.frontShiny).openStream()))
            if (body.sprites?.backShiny != null)pokemon.sprite.add(BitmapFactory.decodeStream(URL(body.sprites?.backShiny).openStream()))


            pokemon.stats = arrayListOf(
                body.stats[5].baseStat,
                body.stats[4].baseStat,
                body.stats[3].baseStat,
                body.stats[0].baseStat,
                body.stats[2].baseStat,
                body.stats[1].baseStat
            )

            var bufferString = "| "
            for (i in 0 until body.types.size) {
                bufferString += "${setColor(body.types[i].type!!.name)} | "
            }

            pokemon.type = bufferString

            pokemon.weight = body.weight.toString()

            val callDesc = descService.getDescription(index)

            var pokeBody = callDesc.execute().body()!!

            for (i in 0 until pokeBody.entries.size) {
                if (pokeBody.entries[i].language?.name == "en") {
                    pokemon.entry = pokeBody.entries[i].entry
                    break
                }
            }

            pokemon.shape = pokeBody.shape!!.name
        }


        return pokemon
    }


     private fun setColor(type: String): String {
        val color = when (type) {
            "bug" -> "#e1cb45"
            "dark" -> "#452a1f"
            "dragon" -> "#9d6fde"
            "electric" -> "#f18f44"
            "fairy" -> "#ffb4e1"
            "fighting" -> "#682326"
            "fire" -> "#e62626"
            "flying" -> "#5887ff"
            "ghost" -> "#102e4a"
            "grass" -> "#56e937"
            "ground" -> "#e6d469"
            "ice" -> "#47f1ff"
            "normal" -> "#a49482"
            "poison" -> "#380141"
            "psychic" -> "#853a4f"
            "rock" -> "#c48d54"
            "steel" -> "#acacac"
            "water" -> "#2756ff"
            else -> ""
        }
        return "<font color='$color'>${type.capitalize()}</font>"
    }

    fun getToPage(index : String): PokemonModel{
        return requestPokemon(index)
    }
}