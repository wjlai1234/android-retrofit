package com.example.mytest.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytest.MainActivity
import com.example.mytest.adapter.ArticlesAdapter
import com.example.mytest.api.ApiInterface
import com.example.mytest.articles.Result
import com.example.mytest.articles.Articles
import com.example.mytest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


class ArticleViewModel : ViewModel() {
    private val BASE_URL = "https://api.nytimes.com/svc/topstories/"
    private var temArrayList: ArrayList<Result> = arrayListOf()
    private var newArrayList: ArrayList<Result> = arrayListOf()

    fun getMyData(binding: ActivityMainBinding, activity: MainActivity) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<Articles?> {
            override fun onResponse(
                call: Call<Articles?>,
                response: Response<Articles?>
            ) {
                val responseBody = response.body()!!
                val myStringBuilder = StringBuilder()
                myStringBuilder.append(responseBody)
                //binding.textView.text = myStringBuilder
                Log.i("MainActivtyS", ":${responseBody.results.size}")
                for (myData in responseBody.results) {
                        newArrayList.add(myData)
                    }
                temArrayList.addAll(newArrayList)
                binding.apply {
                    rvArt.apply {
                        layoutManager =
                            LinearLayoutManager(
                                activity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                        adapter = ArticlesAdapter(responseBody.results)
                    }
                    textView.text ="Top Article for you {${temArrayList.size}}"
                }
            }

            override fun onFailure(call: Call<Articles?>, t: Throwable) {
                Log.i("MainActivtyS", ":" + t.message)
            }

        })
    }

    fun search(
        binding: ActivityMainBinding, activity: MainActivity
    ) {

        binding.apply {

            tiSearch.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                    androidx.appcompat.widget.SearchView.OnQueryTextListener {

                    override fun onQueryTextChange(newText: String?): Boolean {
                        temArrayList.clear()
                        val searchText = newText!!.lowercase()
                        if (searchText.isNotEmpty()) {
                            newArrayList.forEach {
                                if (it.title?.lowercase()
                                        ?.contains(searchText)
                                ) {
                                    temArrayList.add(it)
                                }
                            }
                            binding.apply {
                                rvArt.apply {
                                    layoutManager =
                                        LinearLayoutManager(
                                            activity,
                                            LinearLayoutManager.VERTICAL,
                                            false
                                        )
                                    adapter = ArticlesAdapter(temArrayList)
                                }
                                textView.text ="Top Article for you {${temArrayList.size}}"
                            }

                        } else {
                            temArrayList.clear()
                            temArrayList.addAll(newArrayList)
                            binding.apply {
                                rvArt.apply {
                                    layoutManager =
                                        LinearLayoutManager(
                                            activity,
                                            LinearLayoutManager.VERTICAL,
                                            false
                                        )
                                    adapter = ArticlesAdapter(temArrayList)
                                }
                                textView.text ="Top Article for you {${temArrayList.size}}"
                            }

                        }
                        return false
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }


                })
            }
        }
    }
}