package com.yelldev.testapiapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.yelldev.testapiapp.MainActivity
import com.yelldev.testapiapp.R
import com.yelldev.testapiapp.adapt.ItemAdapter
import com.yelldev.testapiapp.api.ApiResult
import com.yelldev.testapiapp.databinding.FragmentItemBinding
import com.yelldev.testapiapp.databinding.FragmentMainBinding
import com.yelldev.testapiapp.di.CusApplication

class ItemFragment : Fragment() {

	companion object{
		val ITEM_INDEX = "item_index"
	}

	private var _binding: FragmentItemBinding? = null
	private val mBinding get() = _binding!!
	private lateinit var viewModel: ViewModelTraining

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentItemBinding.inflate(layoutInflater, container, false)
		return mBinding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel =
			(activity?.application as CusApplication).provideViewModel(ViewModelTraining::class.java, activity as MainActivity)

		arguments?.let {
			val fData =
				viewModel.liveData.value!!.data!![it.getInt(ITEM_INDEX)]
			mBinding.frItemName.text = fData.name
			val closeApr = fData.closeApproachData[0]
			mBinding.frItemBody.text = closeApr.orbitingBody
			mBinding.frItemAstro.text = closeApr.missDistance.astronomical
			mBinding.frItemLunar.text = closeApr.missDistance.lunar
			mBinding.frItemKilom.text = closeApr.missDistance.kilometers.split(".")[0]
			mBinding.frItemMoment.text = closeApr.closeApproachDateFull
			mBinding.frItemSizeMax.text = fData.estimatedDiameter.kilometers.estimatedDiameterMax.toString()
			mBinding.frItemSizeMin.text = fData.estimatedDiameter.kilometers.estimatedDiameterMin.toString()
			mBinding.frItemHours.text = closeApr.relativeVelocity.kilometersPerHour
			mBinding.frItemSecond.text = closeApr.relativeVelocity.kilometersPerSecond

			mBinding.btnBack.setOnClickListener { findNavController().popBackStack()}
		}


	}



	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}