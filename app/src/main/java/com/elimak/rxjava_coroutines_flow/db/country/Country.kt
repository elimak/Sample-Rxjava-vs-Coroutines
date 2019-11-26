package com.elimak.rxjava_coroutines_flow.db.country

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "country_table")
class Country constructor(countryName: String, countryCode: String, capital: String, flag: String){

    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(name = "countryName")
    @SerializedName("name")
    var countryName: String? = countryName

    @ColumnInfo(name = "alpha3Code")
    @SerializedName("alpha3Code")
    var countryCode: String? = countryCode

    @ColumnInfo(name = "capital")
    var capital: String? = capital

    // svg,
    @ColumnInfo(name = "flag")
    var flag: String? = flag
}
