package com.example.mytest.api

import com.example.mytest.articles.Articles
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{
    @GET("v2/arts.json?api-key=HCF6a8QBQsCGcFLcr3rnuX1ikbwV9H3S")
    fun getData(): Call <Articles>
}