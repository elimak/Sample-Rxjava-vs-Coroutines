package com.elimak.rxjava_coroutines_flow.db.country

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDaoFlow {
    /*** Get a country by id.
     * @return the country from the table with a specific id.
     */
    @Query("SELECT * FROM country_table WHERE uid = :id")
    fun getCountryById(id: Int): Flow<Country>

    /**
     * Insert a country in the database. If the country already exists, replace it.
     * @param country the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(country: Country): Job

    /**
     * Insert all countries.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Country>): Job

    /**
     * Delete all countries.
     */
    @Query("DELETE FROM country_table")
    fun deleteAllCountries(): Job

    /*** Get all countries
     * @return all the country from the table
     */
    @Query("SELECT * from country_table ORDER BY uid ASC")
    fun getAll(): Flow<List<Country>>
}