package com.travela.propertylisting.domain

import com.travela.propertylisting.core.base.Error
import com.travela.propertylisting.core.base.Results
import com.travela.propertylisting.core.base.Success
import com.travela.propertylisting.core.base.UseCase
import com.travela.propertylisting.datamodel.models.Property
import com.travela.propertylisting.datamodel.repository.PropertyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPropertyList @Inject constructor(
    private val propertyRepository: PropertyRepository
) : UseCase<ParamGetPropertyList, Results<List<Property>>>() {

    override suspend fun execute(parameters: ParamGetPropertyList): Results<List<Property>> =
        withContext(Dispatchers.Default) {
            try {
                val response = propertyRepository.getPropertyList(
                    parameters.radius,
                    parameters.latitude,
                    parameters.longitude
                )
                Success(response ?: ArrayList())
            } catch (e: Exception) {
                Error(e)
            }
        }
}

data class ParamGetPropertyList(
    val radius: Double,
    val latitude: Double,
    val longitude: Double
)