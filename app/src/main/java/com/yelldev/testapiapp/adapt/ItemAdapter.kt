package com.yelldev.testapiapp.adapt

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yelldev.testapiapp.R
import com.yelldev.testapiapp.data.MeteorData
import com.yelldev.testapiapp.ui.ItemFragment
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round

class ItemAdapter() :
	RecyclerView.Adapter<ItemAdapter.TrainingViewHolder>() {

	class TrainingViewHolder(view: View) : RecyclerView.ViewHolder(view)

	private var training: List<MeteorData> = emptyList()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val viewToInflate =  R.layout.listitem

		return TrainingViewHolder(layoutInflater.inflate(viewToInflate, parent, false))
	}
//
//	override fun getItemViewType(position: Int): Int {
//		return training[position].type.ordinal
//	}

	override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
		val item = training[position]

		with(holder.itemView) {
			val fBundle = Bundle()
			fBundle.putInt(ItemFragment.ITEM_INDEX,position)
			setOnClickListener { findNavController()
				.navigate(R.id.action_mainFragment_to_itemFragment,fBundle)}


			findViewById<TextView>(R.id.close_date).text = item.closeApproachData[0].closeApproachDate
			findViewById<TextView>(R.id.distance).text =
				"${item.closeApproachData[0].missDistance.kilometers.split(".")[0]} km"
			val df = DecimalFormat("#.##")
			df.roundingMode = RoundingMode.DOWN
			val roundoff = df.format(item.estimatedDiameter.kilometers.estimatedDiameterMax)
			findViewById<TextView>(R.id.diametr).text =
				"${roundoff} km"
			findViewById<TextView>(R.id.name).text = item.name
			findViewById<TextView>(R.id.speed).text =
				"${ item.closeApproachData[0].relativeVelocity.kilometersPerHour.split(".")[0] } km/h"
		}

	}

	override fun getItemCount() = training.size

	@SuppressLint("NotifyDataSetChanged")
	fun setData(data: List<MeteorData>) {
		training = data
		notifyDataSetChanged()
	}
}