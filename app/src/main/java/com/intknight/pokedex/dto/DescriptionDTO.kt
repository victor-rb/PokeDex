package com.intknight.pokedex.dto

import com.google.gson.annotations.SerializedName

class DescriptionDTO {

    @SerializedName("flavor_text_entries") var entries : ArrayList<Entries> = ArrayList() // Entries Block Call
    @SerializedName("shape")               var shape   : Shape?             = null        // Shape   Block Call

}


//<Entries Block>**********************************************************************
class Entries{                                                                       //
                                                                                     //
    @SerializedName("flavor_text") var entry   : String    = "DEFAULT_VALUE"  //
    @SerializedName("language")    var language : Language? = null             //
                                                                                     //
}                                                                                    //
                                                                                     //
class Language{                                                                      //
                                                                                     //
    @SerializedName("name") var name : String = "DEFAULT_VALUE"                //
                                                                                     //
}                                                                                    //
//</Entries Block>*********************************************************************

//<Shape Block>************************************************************************
class Shape{                                                                         //
                                                                                     //
    @SerializedName("name") var name : String = "DEFAULT_VALUE"                //
                                                                                     //
}                                                                                    //
//</Shape Block>***********************************************************************