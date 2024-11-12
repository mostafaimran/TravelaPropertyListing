package com.travela.propertylisting.datamodel.models

import com.google.gson.annotations.SerializedName

data class Property(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("place_type") var placeType: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("max_guest") var maxGuest: Int? = null,
    @SerializedName("max_child") var maxChild: Int? = null,
    @SerializedName("max_infant") var maxInfant: Int? = null,
    @SerializedName("min_nights") var minNights: Int? = null,
    @SerializedName("free_guest") var freeGuest: Int? = null,
    @SerializedName("bedroom") var bedroom: Int? = null,
    @SerializedName("beds") var beds: Int? = null,
    @SerializedName("bathroom") var bathroom: Double? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("weekend_price") var weekendPrice: Int? = null,
    @SerializedName("per_guest_amount") var perGuestAmount: Int? = null,
    @SerializedName("check_in") var checkIn: String? = null,
    @SerializedName("check_out") var checkOut: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("average_rating") var averageRating: Double? = null,
    @SerializedName("average_response") var averageResponse: Double? = null,
    @SerializedName("total_average") var totalAverage: Int? = null,
    @SerializedName("commission_rate") var commissionRate: Double? = null,
    @SerializedName("commission_expired_date") var commissionExpiredDate: String? = null,
    @SerializedName("custom_min_commission") var customMinCommission: String? = null,
    @SerializedName("custom_commission") var customCommission: Double? = null,
    @SerializedName("instant_booking_type") var instantBookingType: String? = null,
    @SerializedName("instant_booking_from") var instantBookingFrom: String? = null,
    @SerializedName("instant_booking_to") var instantBookingTo: String? = null,
    @SerializedName("instant_booking_message") var instantBookingMessage: String? = null,
    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("showable_price") var showablePrice: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("advance") var advance: String? = null,
    @SerializedName("commission") var commission: Int? = null,
    @SerializedName("before_discount") var beforeDiscount: String? = null,
    @SerializedName("average_price") var averagePrice: String? = null,
    @SerializedName("location") var location: Location = Location(),
    @SerializedName("property_type") var propertyType: PropertyType? = PropertyType(),
    @SerializedName("rank") var rank: Rank? = Rank(),
    @SerializedName("status") var status: String? = null,
    @SerializedName("images") var images: ArrayList<Images> = arrayListOf(),
    @SerializedName("host") var host: Host? = Host(),
    @SerializedName("reviews_count") var reviewsCount: Int? = null,
    @SerializedName("reviews_avg") var reviewsAvg: Double? = null,
    @SerializedName("cancellation") var cancellation: Cancellation? = Cancellation(),
    @SerializedName("hotel") var hotel: String? = null
)

data class Location(
    @SerializedName("lat") var lat: Double = 0.0,
    @SerializedName("lng") var lng: Double = 0.0
)

data class PropertyType(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null
)

data class Rank(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("point") var point: Int? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)

data class Images(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("priority") var priority: Int? = null
)

data class Image(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("priority") var priority: String? = null
)

data class Host(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("last_name") var lastName: String? = null,
    @SerializedName("refer_code") var referCode: String? = null,
    @SerializedName("total_booking") var totalBooking: Int? = null,
    @SerializedName("average_response") var averageResponse: Double? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("image") var image: Image? = Image(),
    @SerializedName("host_status") var hostStatus: String? = null
)

data class Cancellation(
    @SerializedName("body") var body: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("for_host") var forHost: String? = null,
    @SerializedName("day") var day: Int? = null
)