package com.faranjit.feedbacklist.ui.home.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Bulent Turkmen on 16.03.2021.
 */
@Serializable
data class FeedbackResponse(
    @SerialName("items")
    val feedbacks: List<Feedback>
)

@Serializable
data class Feedback(
    @SerialName("id")
    val id: String,
    @SerialName("browser")
    val browser: Browser? = null,
    @SerialName("labels")
    val labels: List<String>? = null,
    @SerialName("images")
    val images: Images,
    @SerialName("geo")
    val location: Location,
    @SerialName("comment")
    val comment: String,
    @SerialName("html_snippet")
    val html: String? = null
)

@Serializable
data class Browser(
    @SerialName("onLine")
    val onLine: Boolean? = false,
    @SerialName("product")
    val product: String? = null,
    @SerialName("appCodeName")
    val appCodeName: String? = null,
    @SerialName("userAgent")
    val userAgent: String? = null,
    @SerialName("platform")
    val platform: String? = null,
    @SerialName("productSub")
    val productSub: String? = null,
    @SerialName("language")
    val language: String? = null
)

@Serializable
data class Image(
    @SerialName("uri")
    val uri: String,
    @SerialName("width")
    val width: Int? = null,
    @SerialName("height")
    val height: Int? = null,
    @SerialName("url")
    val url: String
)

@Serializable
data class Images(
    @SerialName("screenshot")
    val screenshot: Image? = null,
    @SerialName("thumbnail")
    val thumbnail: Image? = null,
    @SerialName("cropped")
    val cropped: Image? = null,
    @SerialName("grid")
    val grid: Image? = null,
    @SerialName("list")
    val list: Image? = null,
    @SerialName("detail")
    val detail: Image? = null
)

@Serializable
data class Location(
    @SerialName("country")
    val country: String? = null,
    @SerialName("region")
    val region: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("lat")
    val lat: Double? = null,
    @SerialName("lon")
    val lon: Double? = null
)