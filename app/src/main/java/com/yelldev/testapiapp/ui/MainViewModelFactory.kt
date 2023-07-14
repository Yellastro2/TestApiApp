package com.yelldev.testapiapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yelldev.testapiapp.api.ApiService

class MainViewModelFactory constructor(
	private val retrofit: ApiService
) : ViewModelProvider.Factory {

	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(ViewModelTraining::class.java)) {
			return ViewModelTraining(retrofit) as T
		}else if(modelClass.isAssignableFrom(PinCodeViewModel::class.java)){
			return PinCodeViewModel(Application()) as T
			}
		throw java.lang.IllegalArgumentException("Unknown class name")
	}
}