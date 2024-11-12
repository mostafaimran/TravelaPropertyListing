package com.travela.propertylisting.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.maps.android.compose.CameraPositionState
import com.travela.propertylisting.core.base.Error
import com.travela.propertylisting.core.base.Results
import com.travela.propertylisting.core.base.Success
import com.travela.propertylisting.coreandroid.BaseViewModel
import com.travela.propertylisting.coreandroid.util.ControlledRunner
import com.travela.propertylisting.coreandroid.util.SingleEvent
import com.travela.propertylisting.datamodel.models.Location
import com.travela.propertylisting.datamodel.models.Property
import com.travela.propertylisting.domain.GetPropertyList
import com.travela.propertylisting.domain.ParamGetPropertyList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val getPropertyList: GetPropertyList
) : BaseViewModel() {

    var uiState by mutableStateOf(PropertyListScreenState())
        private set

    val lvApiIssue = MutableLiveData<SingleEvent<Exception>>()

    val logDataList = ArrayList<LogData>()

    private var controlledRunnerFetchRecords = ControlledRunner<Results<List<Property>>>()

    fun requestLocation() {
        uiState = uiState.copy(locationRequired = true)
    }

    fun updateLocation(location: Location) {
        uiState = uiState.copy(
            currentLocation = LatLng(location.lat, location.lng),
            locationRequired = false
        )
    }

    fun getVisibleRadius(cameraPositionState: CameraPositionState): Double? {
        cameraPositionState.projection?.visibleRegion?.let { visibleRegion ->
            val distanceWidth = FloatArray(1)
            val distanceHeight = FloatArray(1)
            val farRight = visibleRegion.farRight
            val farLeft = visibleRegion.farLeft
            val nearRight = visibleRegion.nearRight
            val nearLeft = visibleRegion.nearLeft

            android.location.Location.distanceBetween(
                (farLeft.latitude + nearLeft.latitude) / 2,
                farLeft.longitude,
                (farRight.latitude + nearRight.latitude) / 2,
                farRight.longitude,
                distanceWidth
            )
            android.location.Location.distanceBetween(
                farRight.latitude,
                (farRight.longitude + farLeft.longitude) / 2,
                nearRight.latitude,
                (nearRight.longitude + nearLeft.longitude) / 2,
                distanceHeight
            )
            return sqrt(
                distanceWidth[0].toDouble().pow(2.0) + distanceHeight[0].toDouble().pow(2.0)
            ) / 2.0
        }
        return null
    }

    private fun addLog(log: String) {
        logDataList.add(LogData(log = log))
    }

    fun getPropertyList(radius: Double, latitude: Double, longitude: Double) {
        uiScope.launch {
            uiState = uiState.copy(isLoading = true)

            addLog("requesting property list - radius: $radius, lat: $latitude, lng: $longitude")

            when (val results = controlledRunnerFetchRecords.cancelPreviousThenRun {
                getPropertyList(
                    ParamGetPropertyList(radius, latitude, longitude)
                )
            }) {
                is Success -> {
                    addLog("property list success response: ${Gson().toJson(results.data)}")

                    uiState = uiState.copy(
                        propertyList = results.data,
                        isLoading = false
                    )
                }

                is Error -> {
                    addLog("property list error response: ${results.exception.message ?: ""}")

                    uiState = uiState.copy(
                        isLoading = false,
                        exception = results.exception
                    )
                }
            }
        }
    }

    fun exceptionHandled() {
        uiState = uiState.copy(exception = null)
    }
}

data class PropertyListScreenState(
    val propertyList: List<Property> = emptyList(),
    val isLoading: Boolean = false,
    val exception: Exception? = null,
    val currentLocation: LatLng? = null,
    val locationRequired: Boolean = false
)

data class LogData(val date: Date = Date(), val log: String)