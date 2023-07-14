package com.yelldev.testapiapp.ui

 import android.content.SharedPreferences
 import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import android.widget.TextView
 import android.widget.Toast
import androidx.fragment.app.Fragment
 import androidx.recyclerview.widget.GridLayoutManager
 import com.yelldev.testapiapp.MainActivity
 import com.yelldev.testapiapp.R
 import com.yelldev.testapiapp.adapt.ItemAdapter
 import com.yelldev.testapiapp.adapt.NunAdapter
 import com.yelldev.testapiapp.api.ApiResult
import com.yelldev.testapiapp.databinding.FragmentMainBinding
 import com.yelldev.testapiapp.databinding.LayoutPinCodeDotsBinding
 import com.yelldev.testapiapp.di.CusApplication
 import java.util.prefs.Preferences
 import java.util.stream.IntStream.range

class PinCodeFragment : Fragment() {
	companion object{
		val PIN = "pin"

	}

	private var _binding: LayoutPinCodeDotsBinding? = null
	private val mBinding get() = _binding!!
	private lateinit var viewModel: PinCodeViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		_binding = LayoutPinCodeDotsBinding.inflate(layoutInflater, container, false)
		return mBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)


		viewModel =
			(activity?.application as CusApplication).provideViewModel(PinCodeViewModel::class.java, this)

		val fPref = requireContext().getSharedPreferences("pin",0x0000)
		if(fPref.contains(PIN)){
			viewModel.correctpin = fPref.getString(PIN,"1111").toString()
			viewModel.pinCode.observe(requireActivity()) {
				var fText = ""
				for (i in it.indices){
					fText += " * "
				}
				mBinding.frPinText.text = fText
			}
		}else{
			mBinding.frPinTitle.text = "Set pin for future access"
		}

		viewModel.pinCode.observe(requireActivity()) {
			var fText = ""
			for (i in it.indices){
				fText += " * "
			}
			mBinding.frPinText.text = fText
		}
		mBinding.frPinList.adapter = NunAdapter(){viewModel.onNunClick(requireActivity(),it)}
		mBinding.frPinList.layoutManager = GridLayoutManager(context,3)
	}



	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}