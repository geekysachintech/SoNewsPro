package com.mrmobo.sonews.repository

import com.mrmobo.sonews.api.RetrofitInstance
import com.mrmobo.sonews.db.ArticleDatabase
import com.mrmobo.sonews.models.Article

class NewsRepository (val db: ArticleDatabase) {
    suspend fun getNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}