package com.travela.propertylisting.data.network

import com.travela.propertylisting.core.ApiResponse
import com.travela.propertylisting.datamodel.models.Property
import retrofit2.http.GET
import retrofit2.http.Query

interface TravelaApiService {

    @GET(ApiEndPoints.API_GET_PROPERTY_LIST)
    suspend fun getPropertyList(
        @Query("within") radius: Double,
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ): ApiResponse<List<Property>>

}