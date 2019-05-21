package com.intknight.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import com.intknight.pokedex.caller.Request
import com.intknight.pokedex.dto.PokeAdapter
import kotlinx.android.synthetic.main.list_item_layout.*

class ListActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var viewAdapter : PokeAdapter
    lateinit var viewManager : RecyclerView.LayoutManager

    lateinit var search : android.widget.SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val pokeList = Request.getToList()

        while (pokeList.size < 802){
            Log.d("Log pokeList |", "Wait...")
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = PokeAdapter(this, stringArrayToPair(pokeList))

        search = findViewById(R.id.searchView)

        recyclerView = findViewById<RecyclerView>(R.id.reciclerView).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        search.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                viewAdapter.filter.filter(query)
                return false
            }
        })
    }
    fun stringArrayToPair(list : ArrayList<String>) : ArrayList<Pair<String, String>>{
        val pair = ArrayList<Pair<String, String>>()
        for (i in 0 until list.size){
            pair.add(list[i] to (i + 1).toString())
        }
        return pair
    }
}
