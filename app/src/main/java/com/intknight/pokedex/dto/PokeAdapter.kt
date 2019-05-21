package com.intknight.pokedex.dto

import android.app.Application
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.intknight.pokedex.ListActivity
import com.intknight.pokedex.MainActivity
import com.intknight.pokedex.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_item_layout.view.*

class PokeAdapter (private var context : Context, private val pokeData : ArrayList<Pair<String, String>>) : RecyclerView.Adapter<PokeAdapter.ViewHolder>(), Filterable {

    var pokeListFilter = pokeData


    class ViewHolder (val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int): ViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false)


        return ViewHolder(layout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.view.poke_name.text = pokeListFilter[position].first.toUpperCase()
        holder.view.poke_number.text = pokeListFilter[position].second

        holder.view.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            val index = holder.view.poke_number.text.toString()
            intent.putExtra("index", index)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = pokeListFilter.size


    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                pokeListFilter = if (charString.isEmpty()){
                    pokeData
                }else{
                    val filteredList = ArrayList<Pair<String, String>>()
                    for (row in pokeData){
                        if (row.first.toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = pokeListFilter
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                pokeListFilter = results?.values as ArrayList<Pair<String, String>>
                notifyDataSetChanged()
            }
        }
    }
}