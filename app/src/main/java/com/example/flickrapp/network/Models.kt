package com.example.flickrapp.network

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPhoto(
    @SerialName("title") val title: String?,
    @SerialName("link") val link: String?,
    @SerialName("media") val media: PhotoMedia?,
    @SerialName("date_taken") val dateTaken: Instant?,
    @SerialName("description") val description: String?,
    @SerialName("published") val published: Instant?,
    @SerialName("author") val author: String?,
    @SerialName("author_id") val authorId: String?,
    @SerialName("tags") val tags: String?
)

@Serializable
data class PhotoMedia(@SerialName("m") val photoUrl: String?)

@Serializable
data class PhotosResponse(
    @SerialName("title") val title: String?,
    @SerialName("link") val link: String?,
    @SerialName("description") val description: String?,
    @SerialName("modified") val modified: Instant?,
    @SerialName("generator") val generator: String?,
    @SerialName("items") val items: List<NetworkPhoto>?,
)