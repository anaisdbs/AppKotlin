package com.example.appkotlin.presentation.recyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appkotlin.data.remote.RecyclerList
import com.example.appkotlin.data.remote.api.RetroInstance
import com.example.appkotlin.data.remote.api.RetroService
import retrofit2.Call
import retrofit2.Response

class RecyclerActivityViewModel: ViewModel() {

    val recyclerListData: MutableLiveData<RecyclerList> = MutableLiveData()


    fun getRecyclerListDataObserver(): MutableLiveData<RecyclerList>{
        return recyclerListData
    }

    fun makeApiCall(input: String){ //appel à l'api
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(input)
        call.enqueue(object : retrofit2.Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if(response.isSuccessful) {

                    recyclerListData.postValue(response.body())
                }else{
                    recyclerListData.postValue(null)
                }
            }
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                recyclerListData.postValue(null)
            }

        })
    }

}

