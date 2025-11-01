package com.rydercarpool.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rydercarpool.models.Ride
import com.rydercarpool.R

class RideHistoryAdapter(
    private val rideList: List<Ride>
) : RecyclerView.Adapter<RideHistoryAdapter.RideHistoryViewHolder>() {

    inner class RideHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fromLocation: TextView = itemView.findViewById(R.id.fromLocation)
        val toLocation: TextView = itemView.findViewById(R.id.toLocation)
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val status: TextView = itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideHistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ride_history, parent, false)
        return RideHistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RideHistoryViewHolder, position: Int) {
        val currentRide = rideList[position]
        
        // Remove unnecessary Elvis operators since properties are non-nullable
        holder.fromLocation.text = "From: ${currentRide.pickupLocation}"
        holder.toLocation.text = "To: ${currentRide.destination}"
        holder.date.text = "Date: ${currentRide.rideDate}"
        holder.status.text = "Status: ${currentRide.status}"
        
        // Set status color
        when (currentRide.status) {
            "Completed" -> holder.status.setTextColor(holder.itemView.context.getColor(android.R.color.holo_green_dark))
            "Cancelled" -> holder.status.setTextColor(holder.itemView.context.getColor(android.R.color.holo_red_dark))
            "Upcoming", "Confirmed", "Searching" -> holder.status.setTextColor(holder.itemView.context.getColor(android.R.color.holo_blue_dark))
            else -> holder.status.setTextColor(holder.itemView.context.getColor(android.R.color.darker_gray))
        }
    }

    override fun getItemCount() = rideList.size
}