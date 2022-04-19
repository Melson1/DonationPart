package com.example.donationpart

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.content.Intent
import android.widget.ImageView
import com.example.donationpart.databinding.AccountBinding

class Account : AppCompatActivity() {
    private lateinit var binding : AccountBinding
    val REQUEST_CODE = 100
    lateinit var imgProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //declaration
        imgProfile = findViewById(R.id.imgProfile)


        //buttons function
        binding.tvForm.setOnClickListener() {
            val intent = Intent(this, DonationForm::class.java)
            startActivity(intent)
        }

        binding.tvHistory.setOnClickListener() {
            val intent = Intent(this, History::class.java)
            startActivity(intent)
        }

        binding.btnChgPic.setOnClickListener() {
            openGalleryForImage()
        }

        // calling the action bar
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setTitle("My Account")
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

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            imgProfile.setImageURI(data?.data) // handle chosen image
        }
    }
}