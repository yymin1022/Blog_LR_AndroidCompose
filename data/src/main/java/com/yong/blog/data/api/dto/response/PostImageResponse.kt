package com.yong.blog.data.api.dto.response

import com.google.gson.annotations.SerializedName

data class PostImageResponse(
    @SerializedName("ImageData") val base64Str: String
)