package com.intknight.pokedex

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.intknight.pokedex.caller.Request
import com.intknight.pokedex.doa.PokemonModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var pokeImage: ImageView

    lateinit var pokeNameTx: TextView
    lateinit var pokeTypeTx: TextView
    lateinit var pokeWeightTx: TextView
    lateinit var statHpTx: TextView
    lateinit var statAtkTx: TextView
    lateinit var statDefTx: TextView
    lateinit var statSpdTx: TextView
    lateinit var statXatkTx: TextView
    lateinit var statXdefTx: TextView
    lateinit var pokeDeskTx: TextView

    lateinit var imageSeek: SeekBar

    lateinit var shinySwitch: Switch

    lateinit var pokeSearch : EditText

    lateinit var searchButton : Button

    var pokemon = PokemonModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        pokeImage = findViewById(R.id.pokeImage)

        pokeNameTx = findViewById(R.id.pokenameTX)
        pokeTypeTx = findViewById(R.id.pokeTypeTX)
        pokeWeightTx = findViewById(R.id.pokeWeightTX)

        statHpTx = findViewById(R.id.hpTX)
        statAtkTx = findViewById(R.id.atkTX)
        statDefTx = findViewById(R.id.defTX)
        statSpdTx = findViewById(R.id.spdTX)
        statXatkTx = findViewById(R.id.xatkTX)
        statXdefTx = findViewById(R.id.xdefTX)
        pokeDeskTx = findViewById(R.id.descTX)

        imageSeek = findViewById(R.id.imageSeekBar)

        shinySwitch = findViewById(R.id.swShiny)

        pokeSearch = findViewById(R.id.poke_search)

        searchButton = findViewById(R.id.searchBT)
        ////////////////////////////////////////////////////////////////

        val extra = intent.getStringExtra("index")

        if (extra != null)createPage(extra)

        else createPage()
        ////////////////////////////////////////////////////////////////


        imageSeek.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
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
            if (imageSeek.progress < (pokemon.sprite.size / 2)) {
                imageSeek.progress = (pokemon.sprite.size / 2)
            } else {
                imageSeek.progress = 0
            }
        }

        pokeSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (pokeSearch.text.toString().toInt() <= 802) {
                    pokemon.shape = "DEFAULT_MODEL"
                    createPage(pokeSearch.text.toString())
                    pokeSearch.setText("")
                    pokeSearch.clearFocus()

                    val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    input.hideSoftInputFromWindow(v.windowToken, 0)

                } else {
                    Toast.makeText(this, "Insert a Value to Search the Pokemon up to 802", Toast.LENGTH_LONG).show()
                }
                return@setOnEditorActionListener true
            }
            false
        }

        searchButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createPage(index: String = "1") {

        pokemon = Request.getToPage(index)

        while (pokemon.shape == "DEFAULT_MODEL") Log.d("LOG |", "Wait..")


        imageSeek.max = pokemon.sprite.size - 1
        imageSeek.progress = 0

        pokeImage.setImageBitmap(pokemon.sprite[0])

        pokeNameTx.text = pokemon.name.capitalize()
        pokeTypeTx.text = Html.fromHtml(pokemon.type)
        pokeWeightTx.text = "${pokemon.weight} | ${pokemon.shape.capitalize()}"

        statHpTx.text = pokemon.stats[0]
        statAtkTx.text = pokemon.stats[1]
        statDefTx.text = pokemon.stats[2]
        statSpdTx.text = pokemon.stats[3]
        statXatkTx.text = pokemon.stats[4]
        statXdefTx.text = pokemon.stats[5]

        pokeDeskTx.text = pokemon.entry


    }
}
