package com.mrmobo.sonews.models

import com.mrmobo.sonews.models.Article


data class NewsResponse(

    val articles: MutableList<Article>,

    val status: String,

    val totalResults: Int
)