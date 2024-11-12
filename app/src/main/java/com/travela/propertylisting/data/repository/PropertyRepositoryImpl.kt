package com.travela.propertylisting.data.repository

import android.util.Log
import com.travela.propertylisting.data.network.TravelaApiService
import com.travela.propertylisting.datamodel.exceptions.ServerException
import com.travela.propertylisting.datamodel.ext.convertNetworkSpecificException
import com.travela.propertylisting.datamodel.models.Property
import com.travela.propertylisting.datamodel.repository.PropertyRepository
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(private val travelaApiService: TravelaApiService) :
    PropertyRepository {

    override suspend fun getPropertyList(
        radius: Double,
        lat: Double,
        long: Double
    ): List<Property>? {
        return try {
            val result = travelaApiService.getPropertyList(radius, lat, long)
            if (result.success) {
                result.data
            } else {
                throw ServerException(result.message ?: "Server error occurred")
            }
        } catch (e: Exception) {
            Log.e("PropertyRepositoryImpl", "getPropertyList: ", e)
            throw e.convertNetworkSpecificException()
        }
    }
}