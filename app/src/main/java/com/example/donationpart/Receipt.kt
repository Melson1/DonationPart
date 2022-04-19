package com.example.donationpart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.donationpart.databinding.PaymentBinding
import com.example.donationpart.databinding.ReceiptBinding

class Receipt : AppCompatActivity() {
    private lateinit var binding : ReceiptBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tfTransactionNo.setText("ABC104")

        val intent = intent
        val str = intent.getStringExtra("typeOfBank")
        val str2 = intent.getStringExtra("accountNum")
        val str3 = intent.getStringExtra("amount")
        binding.tfBankName.setText(str)
        binding.tfAccountNo.setText(str2)
        binding.tfPayment.setText(str3)

        binding.btnReturn.setOnClickListener(){
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }

        // calling the action bar
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setTitle("Receipt")
            actionBar.setDisplayHomeAsUpEnabled(false)
        }
    }
}