package com.example.my_application.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.my_application.databinding.ItemHomeScheduleBinding
import com.example.my_application.model.ScheduleResponse
import android.util.Log
import android.view.View
import com.example.my_application.R
import java.time.format.DateTimeFormatter
class HomeScheduleAdapter :
    ListAdapter<ScheduleResponse, HomeScheduleAdapter.ViewHolder>
        (DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val binding = ItemHomeScheduleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    class ViewHolder(private val binding: ItemHomeScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

        fun bind(item: ScheduleResponse) {
            val habit = item.habit

            binding.tvTitle.text = habit?.name ?: "Unknown Habit"
            binding.tvCategory.text = habit?.category?.name ?: ""

            if (habit?.description.isNullOrBlank()) {
                binding.tvDescription.visibility = View.GONE
            } else {
                binding.tvDescription.text = habit?.description
                binding.tvDescription.visibility = View.VISIBLE
            }

            // Format time: "08:00 - 08:30"
            val start = item.startTime?.format(timeFormat)
            val end = item.endTime?.format(timeFormat)
            binding.tvTime.text = when {
                start != null && end != null -> "$start - $end"
                start != null -> start
                else -> "--:--"
            }

            // Set notes visibility
            if (item.notes.isNullOrBlank()) {
                binding.tvNotes.visibility = View.GONE
            } else {
                binding.tvNotes.text = item.notes
                binding.tvNotes.visibility = View.VISIBLE
            }

            // Set icon based on category
            val iconRes = when (habit?.category?.name) {
                "Exercise" -> R.drawable.ic_gym
                "Reading" -> R.drawable.ic_read
                "Study" -> R.drawable.ic_code
                "Hydration" -> R.drawable.ic_water
                "Writing" -> R.drawable.ic_write
                "Running" -> R.drawable.ic_run
                else -> R.drawable.ic_other
            }
            binding.ivIcon.setImageResource(iconRes)
        }
    }
    class DiffCallback : DiffUtil.ItemCallback<ScheduleResponse>() {
        override fun areItemsTheSame(oldItem: ScheduleResponse, newItem:
        ScheduleResponse): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: ScheduleResponse, newItem:
        ScheduleResponse): Boolean {
            return oldItem == newItem
        }
    }
}
