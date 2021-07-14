package com.mdgd.j_commons.core.repo.network

import com.mdgd.j_commons.core.repo.network.schemas.QuakesSchema

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Max
 * on 30-Apr-17.
 */

interface IQuakesAPI {

    // https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-03-01&endtime=2018-03-02
    @GET("query?format=geojson")
    fun getQuakes(@Query("starttime") start: String, @Query("endtime") end: String): Call<QuakesSchema>
}
