package com.travela.propertylisting.domain

import com.travela.propertylisting.core.base.Error
import com.travela.propertylisting.core.base.Results
import com.travela.propertylisting.core.base.Success
import com.travela.propertylisting.core.base.UseCase
import com.travela.propertylisting.datamodel.models.Property
import com.travela.propertylisting.datamodel.repository.PropertyRepository
import com.travela.propertylisting.ui.screen.ParamGetPropertyList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchPropertyList @Inject constructor(
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
