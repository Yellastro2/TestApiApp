package com.yelldev.testapiapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yelldev.testapiapp.MainActivity
import com.yelldev.testapiapp.adapt.ItemAdapter
import com.yelldev.testapiapp.api.ApiResult
import com.yelldev.testapiapp.data.MeteorData
import com.yelldev.testapiapp.databinding.FragmentMainBinding
import com.yelldev.testapiapp.di.CusApplication

class MainFragment : Fragment() {

	private var _binding: FragmentMainBinding? = null
	private val mBinding get() = _binding!!
	private lateinit var viewModel: ViewModelTraining

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentMainBinding.inflate(layoutInflater, container, false)
		return mBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel =
			(activity?.application as CusApplication).provideViewModel(ViewModelTraining::class.java, activity as MainActivity)

		val trainingAdapter = ItemAdapter()
		mBinding.trainingAdapter.adapter = trainingAdapter
		viewModel.liveData.observe(requireActivity()) {
			if (it is ApiResult.Success && it.data != null) {
				trainingAdapter.setData(it.data)
			} else {
				Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
			}
		}
	}



	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}