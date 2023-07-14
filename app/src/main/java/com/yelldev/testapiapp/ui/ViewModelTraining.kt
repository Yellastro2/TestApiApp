package com.yelldev.testapiapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yelldev.testapiapp.api.ApiResult
import com.yelldev.testapiapp.api.ApiService
import com.yelldev.testapiapp.data.MeteorData
import com.yelldev.testapiapp.data.NeoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ViewModelTraining(private val retrofit: ApiService) : ViewModel() {

	var liveData = MutableLiveData<ApiResult<List<MeteorData>>>()

	init {
		loadListTraining()
	}

	fun loadListTraining() {
		viewModelScope.launch(Dispatchers.IO) {
			val next = LocalDate.now().plusDays(1)
			val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
			val currentDate = LocalDateTime.now().format(formatter)
			val nextDate = next.format(formatter)


			val result = retrofit.getItemsData(currentDate,nextDate)
			if (result.isSuccessful) {
//				liveData.postValue(ApiResult.Success(result.body()))
				val fList = ArrayList<MeteorData>()
				val fNeo = result.body()!!
				for (qKey in fNeo.nearEarthObjects.keys){
					fList.addAll(fNeo.nearEarthObjects.get(qKey)!!)
				}
				liveData.postValue(ApiResult.Success(fList))
			} else liveData.postValue(ApiResult.Error("Not Loading"))
		}
	}

}