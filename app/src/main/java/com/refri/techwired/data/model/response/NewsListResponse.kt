package com.refri.techwired.data.model.response

import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @SerializedName("articles")
    var articles: List<Article>
)