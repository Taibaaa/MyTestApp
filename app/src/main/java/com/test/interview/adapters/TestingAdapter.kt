package com.test.interview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.interview.R
import com.test.interview.models.TestModel

class TestingAdapter(private var items: List<TestModel>) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val STRING_TYPE = 0
        private const val INT_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            STRING_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.string_items, parent, false)
                StringViewHolder(view)
            }
            INT_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.int_items, parent, false)
                IntViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            STRING_TYPE -> {
                val item = items[position]
                (holder as StringViewHolder).bind(item)
            }
            INT_TYPE -> {
                val item = items[position]
                (holder as IntViewHolder).bind(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].value != -1) INT_TYPE else STRING_TYPE
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val stringTextView: TextView = itemView.findViewById(R.id.textView)

        fun bind(item: TestModel) {
            stringTextView.text = item.stringValue
        }
    }

    inner class IntViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val intTextView: TextView = itemView.findViewById(R.id.textView)

        fun bind(item: TestModel) {
            intTextView.text = item.value.toString()
        }
    }

    fun updateItems(items: List<TestModel>) {
        this.items = items
        notifyDataSetChanged()
    }
}




