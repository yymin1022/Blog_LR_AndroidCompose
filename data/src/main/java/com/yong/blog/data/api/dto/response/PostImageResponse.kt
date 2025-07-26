package com.yong.blog.data.api.dto.response

import com.google.gson.annotations.SerializedName
import com.yong.blog.domain.model.PostImage

data class PostImageResponse(
    @SerializedName("ImageData") val base64Str: String
)

fun PostImageResponse.toDomain() =
    PostImage(
        base64Str = this.base64Str
    )