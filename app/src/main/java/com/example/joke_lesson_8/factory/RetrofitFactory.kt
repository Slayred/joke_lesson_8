package com.example.joke_lesson_8.factory


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class RetrofitFactory {

  companion object{
      private fun getOkHttpInstance(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
          logging.level = HttpLoggingInterceptor.Level.BASIC
          return OkHttpClient().newBuilder().ignoreAllSSLErrors()
              .addInterceptor(logging)
              .build()
      }

      fun getRetrofitInstance(url: String): Retrofit{
          return Retrofit.Builder()
              .baseUrl(url)
              .client(getOkHttpInstance())
              .addConverterFactory(GsonConverterFactory.create())
              .build()
      }

      //fun getService(url: String): NewJokeService = getRetrofitInstance(url).create(NewJokeService::class.java)
      fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
          val naiveTrustManager = object : X509TrustManager {
              override fun checkClientTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) = Unit

              override fun checkServerTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) = Unit

              override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate>? = arrayOf()
          }

          val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
              val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
              init(null, trustAllCerts, SecureRandom())
          }.socketFactory

          sslSocketFactory(insecureSocketFactory, naiveTrustManager)
          hostnameVerifier(HostnameVerifier { _, _ -> true })
          return this
      }
  }
}