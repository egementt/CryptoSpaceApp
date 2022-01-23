package com.example.cryptospaceapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cryptospaceapp.model.crypto_model.CryptoModelItem
import com.example.cryptospaceapp.service.network.CryptoService
import com.example.cryptospaceapp.util.Constant
import com.example.cryptospaceapp.util.Response
import javax.inject.Inject

class CryptoRepository @Inject constructor(private val api: CryptoService) {

    private val response : MutableLiveData<Response<ArrayList<CryptoModelItem>>> =  MutableLiveData()

    suspend fun getAllCurrencies(): MutableLiveData<Response<ArrayList<CryptoModelItem>>> {
        try {
            response.value = Response.Loading()
            val data = api.listCurrencies()
            if (!data.isEmpty()){
                response.value = Response.Success(data)
                if (response.value != null){
                    Log.d(Constant.LOG_TAG, "REPO: ${response.value?.data?.size} & ${response.value.toString()} ")

                }
            }
        }catch (e: Exception){
            response.value = Response.Error(message = e.localizedMessage)
        }
        return response
    }
}