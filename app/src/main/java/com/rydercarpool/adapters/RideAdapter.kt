package com.rydercarpool.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.rydercarpool.R
import com.rydercarpool.models.Ride

class RideAdapter(private val onItemClick: (Ride) -> Unit, private val onBookClick: (Ride) -> Unit) :
    ListAdapter<Ride, RideAdapter.RideViewHolder>(RideDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ride, parent, false)
        return RideViewHolder(view)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride = getItem(position)
        holder.bind(ride)
        holder.itemView.setOnClickListener { onItemClick(ride) }
        holder.btnBook.setOnClickListener { onBookClick(ride) }
    }

    class RideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFromLocation: TextView = itemView.findViewById(R.id.tvFromLocation)
        private val tvToLocation: TextView = itemView.findViewById(R.id.tvToLocation)
        private val tvDateTime: TextView = itemView.findViewById(R.id.tvDateTime)
        private val tvSeats: TextView = itemView.findViewById(R.id.tvSeats)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val tvDriverName: TextView = itemView.findViewById(R.id.tvDriverName)
        private val tvRating: TextView = itemView.findViewById(R.id.tvRating)
        val btnBook: MaterialButton = itemView.findViewById(R.id.btnBook)

        fun bind(ride: Ride) {
            tvFromLocation.text = ride.fromLocation
            tvToLocation.text = ride.toLocation
            tvDateTime.text = "${ride.date} at ${ride.time}"
            tvSeats.text = "${ride.availableSeats} seats available"
            tvPrice.text = "$${ride.price}"
            tvDriverName.text = "Driver: ${ride.driverName}"
            tvRating.text = "⭐ ${ride.driverRating} (${ride.totalRatings} reviews)"
        }
    }

    object RideDiffCallback : DiffUtil.ItemCallback<Ride>() {
        override fun areItemsTheSame(oldItem: Ride, newItem: Ride): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ride, newItem: Ride): Boolean {
            return oldItem == newItem
        }
    }
}