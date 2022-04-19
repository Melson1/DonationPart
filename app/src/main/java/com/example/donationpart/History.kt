package com.example.donationpart

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.donationpart.databinding.HistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*


class History : AppCompatActivity(){
    private lateinit var binding : HistoryBinding
    private lateinit var database : DatabaseReference
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //database things
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Donor")


        database.child("Donors' Information").child("1234 4567").child("date").get().addOnSuccessListener { date->
            database.child("Donors' Information").child("1234 4567").child("transactionNo").get().addOnSuccessListener { transaction->
                database.child("Donors' Information").child("1234 4567").child("bankName").get().addOnSuccessListener { bank ->
                    database.child("Donors' Information").child("1234 4567").child("amount").get().addOnSuccessListener { amount ->
                        val donorList = listOf(
                            Donor("12/12/2012", "ABC101", "Public Bank", "RM 25"),
                            Donor("5/5/2015", "ABC102", "Maybank", "RM 100"),
                            Donor("2/2/2022", "ABC103", "Public Bank", "RM 250"),
                            Donor("${date.value}", "${transaction.value}", "${bank.value}", "${amount.value}"),
                        )
                        //recycler view
                        val viewHolder = MyAdapter(donorList)

                        binding.donorRecyclerView.adapter = viewHolder
                        binding.donorRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                        binding.donorRecyclerView.setHasFixedSize(true)
                    }
                }
            }
        }



        // calling the action bar
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setTitle("Donation History")
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }


    // this event will enable the back function to the button on press
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}