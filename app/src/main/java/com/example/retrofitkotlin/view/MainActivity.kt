package com.example.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import com.example.retrofitkotlin.adapter.RecylerViewAdapter
import com.example.retrofitkotlin.model.CryptoModel
import com.example.retrofitkotlin.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private val  BASE_URL = "https://raw.githubusercontent.com/"
private var cryptoModels: ArrayList<CryptoModel>? = null
private val recylerViewAdapter : RecylerViewAdapter? = null


class MainActivity(view: View) : AppCompatActivity(), RecylerViewAdapter.RowHolder(view) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadData()
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()
        call.enqueue(object: Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = ArrayList(it)

                        cryptoModels?.let {  }

                        recylerViewAdapter = RecylerViewAdapter(cryptoModels!!,this@MainActivity)
                        recyclerView.adapter = recylerViewAdapter


                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })




    }
    override fun onItemClick(cryptoModel: CryptoModel){
        Toast.makeText(this,"Clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()


    }
}