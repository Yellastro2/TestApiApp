package com.yelldev.testapiapp.adapt

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yelldev.testapiapp.R
import com.yelldev.testapiapp.data.MeteorData

class NunAdapter(val onItemClick: (Char) -> Unit) :
	RecyclerView.Adapter<NunAdapter.TrainingViewHolder>() {

	class TrainingViewHolder(view: View) : RecyclerView.ViewHolder(view)

	private var nunbers: List<Char> = listOf('1','2','3','4','5','6','7','8','9',' ','0')

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val viewToInflate =  R.layout.pin_item

		return TrainingViewHolder(layoutInflater.inflate(viewToInflate, parent, false))
	}
//
//	override fun getItemViewType(position: Int): Int {
//		return training[position].type.ordinal
//	}

	override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
		val item = nunbers[position]

		with(holder.itemView) {
			findViewById<TextView>(R.id.it_pin_num).text = item.toString()
			findViewById<TextView>(R.id.it_pin_num).setOnClickListener { onItemClick(item) }
		}

	}

	override fun getItemCount() = nunbers.size

}