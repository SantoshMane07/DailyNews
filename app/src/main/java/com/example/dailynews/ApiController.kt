package com.example.dailynews

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiController() {

    companion object{
        final val Base_url="https://newsapi.org/v2/"
        private lateinit var retrofitobject: Retrofit
        @Volatile
        private var INSTANCE: ApiController? = null
        fun getInstance() =
              INSTANCE ?: synchronized(this) {
                  INSTANCE ?: ApiController().also {
                      INSTANCE = it
                  }
              }
    }

    init {
             retrofitobject= Retrofit.Builder()
            .baseUrl(Base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getApi() : NewsApiset {
      return retrofitobject.create(NewsApiset::class.java)
    }

}

//class MySingleton constructor(context: Context) {
//
//    companion object {
//        @Volatile
//        private var INSTANCE: MySingleton? = null
//        fun getInstance(context: Context) =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: MySingleton(context).also {
//                    INSTANCE = it
//                }
//            }
//    }
//
//    private val requestQueue: RequestQueue by lazy {
//        // applicationContext is key, it keeps you from leaking the
//        // Activity or BroadcastReceiver if someone passes one in.
//        Volley.newRequestQueue(context.applicationContext)
//    }
//    fun <T> addToRequestQueue(req: Request<T>) {
//        requestQueue.add(req)
//    }
//}