package com.example.mytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mytest.api.ApiInterface
import com.example.mytest.articles.Articles
import com.example.mytest.databinding.ActivityMainBinding
import com.example.mytest.viewModel.ArticleViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val viewModel by lazy { ViewModelProvider(this).get(ArticleViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        viewModel.getMyData(binding,this)
        viewModel.search(binding,this)
    }
}