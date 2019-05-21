package com.intknight.pokedex

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import com.intknight.pokedex.caller.Request
import com.intknight.pokedex.doa.PokemonModel

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        while (!checkConnection()) {

            checkConnection()
            Log.d("Network |", checkConnection().toString())
        }

        Handler().postDelayed({

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }, 3000)


    }

    private fun checkConnection(): Boolean {
        val connManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}
