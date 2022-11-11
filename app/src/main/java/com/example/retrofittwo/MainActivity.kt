package com.example.retrofittwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofittwo.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/posts/1/"
private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyData2Item>?> {
            override fun onResponse(
                call: Call<List<MyData2Item>?>,
                response: Response<List<MyData2Item>?>
            ) {
                val respondeBody = response.body()!!
                val myStringBuilder = StringBuilder()

                for(myData in respondeBody){
                    myStringBuilder.append(myData.email)
                    myStringBuilder.append("\n")
                    myStringBuilder.append(myData.name)
                }

                binding.textViewLista.text=myStringBuilder
                //cambio
            }

            override fun onFailure(call: Call<List<MyData2Item>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}