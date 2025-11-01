package com.rydercarpool.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rydercarpool.models.Ride
import com.rydercarpool.R

class RideListAdapter(
    private val rideList: List<Ride>,
    private val onItemClick: (Ride) -> Unit
) : RecyclerView.Adapter<RideListAdapter.RideViewHolder>() {

    inner class RideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDriver: TextView = itemView.findViewById(R.id.tvDriver)
        val tvPickup: TextView = itemView.findViewById(R.id.tvPickup)
        val tvDestination: TextView = itemView.findViewById(R.id.tvDestination)
        val tvFare: TextView = itemView.findViewById(R.id.tvFare)
        val btnBook: Button = itemView.findViewById(R.id.btnBook)

        init {
            btnBook.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(rideList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ride, parent, false)
        return RideViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val currentRide = rideList[position]
        
        holder.tvDriver.text = "Driver: ${currentRide.driverName}"
        holder.tvPickup.text = "From: ${currentRide.pickupLocation}"
        holder.tvDestination.text = "To: ${currentRide.destination}"
        holder.tvFare.text = "Fare: $${currentRide.fare}"
        holder.btnBook.text = "Book Ride"
    }

    override fun getItemCount() = rideList.size
}