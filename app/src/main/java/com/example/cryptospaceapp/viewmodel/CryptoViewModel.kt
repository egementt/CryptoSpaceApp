package com.example.cryptospaceapp.viewmodel

import android.util.Log

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
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
class CryptoViewModel @Inject constructor(private val repository: CryptoRepository) : ViewModel() {

    var currentPage: Int = 1
    var response: MutableLiveData<Response<ArrayList<CryptoModelItem>>> =
        MutableLiveData(Response.Loading())
    var searchList: MutableLiveData<MutableList<CryptoModelItem>> =
        MutableLiveData(emptyList<CryptoModelItem>().toMutableList())

    init {
        getAllCryptos()
    }

    fun getAllCryptos(page: Int = 1) {
        viewModelScope.launch {
            currentPage = page
            response.value = repository.getAllCurrencies(page = page).value
            if (response.value != null) {
                delay(1000)
                response.value!!.data?.forEach {
                    Log.d(
                        Constant.LOG_TAG,
                        "${it.name} -> ${it.image} -> ${it.price_change_percentage_24h}"
                    )
                }
            }
        }
    }

    fun searchCrypto(key: String) {
        if (response.value != null) {
            val targetList = response.value!!.data
            val keyList = targetList?.filter { cryptoModelItem ->
                cryptoModelItem.name.contains(key, true).and(key.length >= 2 || key.isEmpty())
            }
            if (keyList!!.isNotEmpty()){
                keyList.forEach { Log.d(Constant.LOG_TAG, "Item Found: key: $key -> found: $it") }
                searchList.value = keyList.toMutableList()
            }
        }else{
            getAllCryptos()
            searchCrypto(key)
        }
    }
}