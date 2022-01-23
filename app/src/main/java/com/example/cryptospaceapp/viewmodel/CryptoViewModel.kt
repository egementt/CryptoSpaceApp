package com.example.cryptospaceapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptospaceapp.model.crypto_model.CryptoModelItem
import com.example.cryptospaceapp.repository.CryptoRepository
import com.example.cryptospaceapp.util.Constant
import com.example.cryptospaceapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(private val repository: CryptoRepository):ViewModel() {

    var response: MutableLiveData<Response<ArrayList<CryptoModelItem>>> = MutableLiveData(Response.Loading())

    init {
        getAllCryptos()
    }

    private fun getAllCryptos(){
        viewModelScope.launch {
            response.value = repository.getAllCurrencies().value
            if (response.value != null){
                    delay(1000)
                response.value!!.data?.forEach {
                    Log.d(Constant.LOG_TAG, "${it.name} -> ${it.image} -> ${it.price_change_percentage_24h}")
                }
            }
        }
    }
}