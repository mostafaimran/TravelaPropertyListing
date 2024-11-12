package com.travela.propertylisting.core

import com.google.gson.annotations.SerializedName
import com.travela.propertylisting.core.base.BaseApiResponse


open class ApiResponse<T>() : BaseApiResponse() {
    @SerializedName("data")
    var data: T? = null
}