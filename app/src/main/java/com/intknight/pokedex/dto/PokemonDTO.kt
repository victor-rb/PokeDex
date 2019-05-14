package com.intknight.pokedex.dto

import com.google.gson.annotations.SerializedName

class PokemonDTO {

    @SerializedName("name")    var name    : String            = "DEFAULT_VALUE" // Name String
    @SerializedName("sprites") var sprites : Sprite?           = null            //Sprites Object Call
    @SerializedName("stats")   var stats   : ArrayList<Stats>  = ArrayList()     //Stat Block Call
    @SerializedName("types")   var types   : ArrayList<Types>  = ArrayList()     //Types Block Call
    @SerializedName("weight")  var weight  : Int               = 0               //Weight Value

}

//<Sprite Object>**********************************************************************************
class Sprite{                                                                                    //
                                                                                                 //
    @SerializedName("back_default")       var backDefault      : String = "DEFAULT_VALUE"  //
    @SerializedName("back_shiny")         var backShiny        : String = "DEFAULT_VALUE"  //
    @SerializedName("front_default")      var frontDefault     : String = "DEFAULT_VALUE"  //
    @SerializedName("front_shiny")        var frontShiny       : String = "DEFAULT_VALUE"  //
                                                                                                 //
}                                                                                                //
//</Sprite Object>*********************************************************************************


//Stats Block********************************************************************
class Stats{                                                                   //
                                                                               //
    @SerializedName("base_stat") var baseStat : String = "DEFAULT_VALUE" //
    @SerializedName("stat")      var stat     : Stat?  = null            //
                                                                               //
}                                                                              //
                                                                               //
class Stat{                                                                    //
                                                                               //
    @SerializedName("name") var name : String = "DEFAULT_VALUE"          //
                                                                               //
}                                                                              //
//</Stats Block******************************************************************

//<Types Block>******************************************************************
class Types{                                                                   //
                                                                               //
    @SerializedName("type") var type : Type? = null                      //
                                                                               //
}                                                                              //
                                                                               //
class Type{                                                                    //
                                                                               //
    @SerializedName("name") var name : String = "DEFAULT_VALUE"          //
                                                                               //
}                                                                              //
//</Type Block>******************************************************************