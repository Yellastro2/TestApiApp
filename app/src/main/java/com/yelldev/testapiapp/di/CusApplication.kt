package com.yelldev.testapiapp.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.yelldev.testapiapp.api.ApiService
import com.yelldev.testapiapp.api.RetrofitClient
import com.yelldev.testapiapp.ui.MainViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CusApplication : Application() {

	private lateinit var viewModelFactory: MainViewModelFactory
	private lateinit var retrofit: ApiService

	override fun onCreate() {
		super.onCreate()
		retrofit = Retrofit.Builder()
			.baseUrl(RetrofitClient.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build().create(ApiService::class.java)
		viewModelFactory = MainViewModelFactory(retrofit)
	}

	fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner) =
		ViewModelProvider(owner, viewModelFactory)[clazz]
}