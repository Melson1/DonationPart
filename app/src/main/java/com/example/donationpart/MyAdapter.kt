package com.example.donationpart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private val donorList: List<Donor>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var tfDate2: TextView = view.findViewById(R.id.tfDate2)
        var tfTransactionNo2: TextView = view.findViewById(R.id.tfTransactionNo2)
        var tfBankName2: TextView = view.findViewById(R.id.tfBankName2)
        var tfPaid2: TextView = view.findViewById(R.id.tfPaid2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_view, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRec = donorList[position]

        holder.tfDate2.text = currentRec.date
        holder.tfTransactionNo2.text = currentRec.transactionNo
        holder.tfBankName2.text = currentRec.bankName
        holder.tfPaid2.text = currentRec.amount
    }

    override fun getItemCount(): Int {
        return donorList.size
    }
}