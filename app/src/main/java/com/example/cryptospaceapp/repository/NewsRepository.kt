package com.example.cryptospaceapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cryptospaceapp.model.news_model.NewsModel
import com.example.cryptospaceapp.service.network.NewsService
import com.example.cryptospaceapp.util.Constant
import com.example.cryptospaceapp.util.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: NewsService) {

    private val response: MutableLiveData<Response<NewsModel>> = MutableLiveData()

    suspend fun getAllNews(): MutableLiveData<Response<NewsModel>> {
        try {
            response.value = Response.Loading()
            val data = api.listNews()
            if (data.totalResults > 0) {
                response.value = Response.Success(data)
                if (response.value != null) {
                    Log.d(
                        Constant.LOG_TAG,
                        "NEWS_REPO: ${response.value?.data?.totalResults} & ${response.value.toString()} "
                    )
                }
            }
        }catch (e: Exception){
            response.value = Response.Error(message = e.localizedMessage)
        }
        return response
    }
}