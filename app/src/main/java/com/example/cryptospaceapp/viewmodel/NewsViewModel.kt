package com.example.cryptospaceapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptospaceapp.model.news_model.NewsModel
import com.example.cryptospaceapp.repository.NewsRepository
import com.example.cryptospaceapp.util.Constant
import com.example.cryptospaceapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    var response: MutableLiveData<Response<NewsModel>> = MutableLiveData(Response.Loading())

    init {
        getAllNews()
    }

    private fun getAllNews() {
        viewModelScope.launch {
            response.value = repository.getAllNews().value
            if (response.value != null) {
                delay(1000)
                response.value!!.data?.articles?.forEach {
                    Log.d(
                        Constant.LOG_TAG,
                        "NEWS: ${it.title} -> ${it.author} || ${it.publishedAt}"
                    )
                }
            }
        }
    }
}