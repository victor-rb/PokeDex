package com.intknight.pokedex

import android.arch.lifecycle.Observer
import android.content.Context
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.AsyncTask.execute
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.intknight.pokedex.caller.Request
import com.intknight.pokedex.doa.DescriptionService
import com.intknight.pokedex.doa.PokemonModel
import com.intknight.pokedex.doa.PokemonService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

import kotlin.Exception

class MainActivity : AppCompatActivity() {

    lateinit var pokeImage    : ImageView

    lateinit var pokeNameTx   : TextView
    lateinit var pokeTypeTx   : TextView
    lateinit var pokeWeightTx : TextView
    lateinit var statHpTx     : TextView
    lateinit var statAtkTx    : TextView
    lateinit var statDefTx    : TextView
    lateinit var statSpdTx    : TextView
    lateinit var statXatkTx   : TextView
    lateinit var statXdefTx   : TextView
    lateinit var pokeDeskTx   : TextView

    lateinit var imageSeek    : SeekBar

    lateinit var shinySwitch  : Switch

    lateinit var pokeSearch   : EditText

    var pokemon = PokemonModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork : NetworkInfo? = connManager.activeNetworkInfo
        val isConnected = activeNetwork?.isConnected == true

        if (!isConnected){
            System.exit(0)
        }

        pokeImage    = findViewById(R.id.pokeImage)

        pokeNameTx   = findViewById(R.id.pokenameTX)
        pokeTypeTx   = findViewById(R.id.pokeTypeTX)
        pokeWeightTx = findViewById(R.id.pokeWeightTX)

        statHpTx     = findViewById(R.id.hpTX)
        statAtkTx    = findViewById(R.id.atkTX)
        statDefTx    = findViewById(R.id.defTX)
        statSpdTx    = findViewById(R.id.spdTX)
        statXatkTx   = findViewById(R.id.xatkTX)
        statXdefTx   = findViewById(R.id.xdefTX)
        pokeDeskTx   = findViewById(R.id.descTX)

        imageSeek          = findViewById(R.id.imageSeekBar)

        shinySwitch  = findViewById(R.id.swShiny)

        pokeSearch = findViewById(R.id.poke_search)
        ////////////////////////////////////////////////////////////////

        createPage()

        imageSeek.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    val index = imageSeek.progress
                    pokeImage.setImageBitmap(pokemon.sprite[index])
                    swShiny.isChecked = index > (pokemon.sprite.size / 2) - 1
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            }
        )

        swShiny.setOnClickListener {
            if (imageSeek.progress < (pokemon.sprite.size / 2)){
                imageSeek.progress = (pokemon.sprite.size / 2)
            }else{
                imageSeek.progress = 0
            }
        }

        pokeSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                if (pokeSearch.text.toString().toInt() <= 802){
                    pokemon.shape = "DEFAULT_MODEL"
                    createPage(pokeSearch.text.toString())
                    pokeSearch.setText("")
                    pokeSearch.clearFocus()

                    val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    input.hideSoftInputFromWindow(v.windowToken, 0)

                }else{
                    Toast.makeText(this, "Insert a Value to Search the Pokemon up to 802", Toast.LENGTH_LONG).show()
                }
                return@setOnEditorActionListener true
            }
            false
        }

    }

    private fun createPage(index : String = "1"){

        pokemon = Request.getToPage(index)

        while (pokemon.shape == "DEFAULT_MODEL") Log.d("LOG |", "Wait.." )


        imageSeek.max      = pokemon.sprite.size - 1
        imageSeek.progress = 0

        pokeImage.setImageBitmap(pokemon.sprite[0])

        pokeNameTx.text   = pokemon.name.capitalize()
        pokeTypeTx.text   = Html.fromHtml(pokemon.type)
        pokeWeightTx.text = pokemon.weight

        statHpTx.text     = pokemon.stats[0]
        statAtkTx.text    = pokemon.stats[1]
        statDefTx.text    = pokemon.stats[2]
        statSpdTx.text    = pokemon.stats[3]
        statXatkTx.text   = pokemon.stats[4]
        statXdefTx.text   = pokemon.stats[5]
        pokeDeskTx.text   = pokemon.entry
    }
}
