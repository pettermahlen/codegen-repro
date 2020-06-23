package com.pettermahlen

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoshiGenerated(
        val token: String
)
