package com.travela.propertylisting.datamodel.repository

import com.travela.propertylisting.datamodel.models.Property

interface PropertyRepository {
    suspend fun getPropertyList(radius: Double, lat: Double, long: Double): List<Property>?
}