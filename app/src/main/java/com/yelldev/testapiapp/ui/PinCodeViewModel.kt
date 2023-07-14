package com.yelldev.testapiapp.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.yelldev.testapiapp.MainActivity
import com.yelldev.testapiapp.R
import kotlin.concurrent.timer

class PinCodeViewModel(application: Application) : AndroidViewModel(application) {

	var correctpin = "NONE"
	var tryCount = 0
	val maxTry = 5

	val pinCode = MutableLiveData<String>().also {
		it.value = ""
	}
	var pinBlock = false

	fun onNunClick(context: Activity,number: Char){
		if(number == ' ') return
		if(pinBlock){
			Toast.makeText(context,"App blocked by lil",Toast.LENGTH_SHORT).show()
			pinCode.postValue("")
			return
		}
		val existingPassCode = pinCode.value ?: ""
		val newPassCode = existingPassCode + number
		pinCode.postValue(newPassCode)
		if (newPassCode == correctpin){
			openMain(context)
		}else{
			if(newPassCode.length>3){

				if(correctpin == "NONE"){
					context.getSharedPreferences("pin",0x0000)
						.edit()
						.putString(PinCodeFragment.PIN,newPassCode)
						.apply()
					openMain(context)
				}else{
					val timer = object: CountDownTimer(100, 500) {
						override fun onTick(millisUntilFinished: Long) {}
						override fun onFinish() {
							Toast.makeText(context,"Wrong pin",Toast.LENGTH_SHORT).show()
							pinCode.postValue("")
							tryCount ++
							if (tryCount>=maxTry){
								Toast.makeText(context,"Too many fails.\nApp will blocked",Toast.LENGTH_SHORT).show()
								blockPin()
							}}
					}.start()
				}
			}
		}
	}

	fun openMain(context: Activity){
		val timer = object: CountDownTimer(100, 500) {
			override fun onTick(millisUntilFinished: Long) {}
			override fun onFinish() {
				val nav = context.findNavController(R.id.fr_pin_text)
				context.findNavController(R.id.fr_pin_text)
					.navigate(R.id.action_pinCodeFragment_to_mainFragment
						,null,
						NavOptions.Builder().setPopUpTo(nav.graph.startDestinationId, true).build())
			}
		}.start()
	}

	private fun blockPin() {
		pinBlock = true
		val timer = object: CountDownTimer(10000, 1000) {
			override fun onTick(millisUntilFinished: Long) {}

			override fun onFinish() {
				tryCount = 0
				pinBlock = false}
		}.start()
	}

	fun onEraseClicked() {
		val droppedLast = pinCode.value?.dropLast(1) ?: ""
		pinCode.postValue(droppedLast)
	}}