package com.example.donationpart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.donationpart.databinding.DonationFormBinding

class DonationForm : AppCompatActivity() {
    private lateinit var binding : DonationFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DonationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn5.setOnClickListener(){
            binding.tvInput.setText("RM 5")
        }
        binding.btn25.setOnClickListener(){
            binding.tvInput.setText("RM 25")
        }
        binding.btn50.setOnClickListener(){
            binding.tvInput.setText("RM 50")
        }
        binding.btn100.setOnClickListener(){
            binding.tvInput.setText("RM 100")
        }
        binding.btn250.setOnClickListener(){
            binding.tvInput.setText("RM 250")
        }
        binding.btn500.setOnClickListener(){
            binding.tvInput.setText("RM 500")
        }
        binding.btnDonate.setOnClickListener(){
            if (TextUtils.isEmpty(binding.tvInput.text.toString())) {
                binding.tvHint.setVisibility(View.VISIBLE)
            }
            else{
                val str: String = binding.tvInput.getText().toString()
                val intent = Intent(this, Payment::class.java)
                intent.putExtra("donationAmount", str);
                startActivity(intent)
            }
        }

        // calling the action bar
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setTitle("Cash Donation")
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