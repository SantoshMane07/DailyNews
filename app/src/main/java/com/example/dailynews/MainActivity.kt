package com.example.dailynews

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() , NewsitemClicked{

    private var madapter = NewsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview.layoutManager= LinearLayoutManager(this)
        recyclerview.adapter=madapter
        fetchData()

    }
    private fun fetchData(){
        val call:Call<RNews>  = ApiController.getInstance().getApi().getNewsData()
        //Log.d("tif", "fetchData: $call")
        call.enqueue(object : Callback<RNews?> {
            override fun onResponse(call: Call<RNews?>, response: Response<RNews?>) {
                val data=response.body()!!
                //Log.d("g", "onResponse: ${data.toString()}")
                madapter.updateNews(data.articles)
            }

            override fun onFailure(call: Call<RNews?>, t: Throwable) {
                Toast.makeText(applicationContext,"$t",Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onitemClicked(fullNews: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(fullNews.url))
    }
}

