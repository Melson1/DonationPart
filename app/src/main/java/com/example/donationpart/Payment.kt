package com.example.donationpart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.donationpart.databinding.PaymentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


class Payment : AppCompatActivity(), AdapterView.OnItemSelectedListener{
    private lateinit var binding : PaymentBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference
    lateinit var spinner: Spinner
    lateinit var tvTypeOfBank: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //database things
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Donor")


        //declarations
        val number = "228833"
        tvTypeOfBank = findViewById(R.id.tvTypeOfBank)
        spinner = findViewById(R.id.spinner)


        //receiving values from other activity
        val intent = intent
        val str = intent.getStringExtra("donationAmount")
        binding.tvAmount.setText(str)
        binding.tvAmount.setFocusable(false)
        tvTypeOfBank.setFocusable(false)

        binding.btnTac.setOnClickListener(){
            Toast.makeText(this, "Your TAC no. is $number", Toast.LENGTH_SHORT).show()
        }

        binding.btnCancel.setOnClickListener(){
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }

        binding.btnDonateTo.setOnClickListener(){
            if (TextUtils.isEmpty(binding.tvAccount.text.toString())) {
                binding.tvHint2.setVisibility(View.VISIBLE)
            }
            else if (TextUtils.isEmpty(binding.tvTac.text.toString())) {
                binding.tvHint3.setVisibility(View.VISIBLE)
                binding.tvHint2.setVisibility(View.INVISIBLE)
            }
            else if(binding.tvTac.text.toString() != number){
                binding.tvHint3.setVisibility(View.INVISIBLE)
                Toast.makeText(this, "Your TAC no. is incorrect", Toast.LENGTH_SHORT).show()
            }
            else{
                //notifications
                val amount: String = binding.tvAmount.getText().toString()
                val bank: String = tvTypeOfBank.getText().toString()
                val cardNumber: String = binding.tvAccount.getText().toString()
                val message: String = "You have successfully transferred $amount using $bank linked with card number $cardNumber."
                NotificationHelper(this, message).Notification()

                //real-time date
                val sdf = SimpleDateFormat("dd/M/yyyy")
                val currentDate = sdf.format(Date())

                //database things
                val newDonor = Donor(currentDate, "ABC104", bank, amount)
                database.child("Donors' Information").child(cardNumber).setValue(newDonor)


                //bringing values to another activity
                val intent = Intent(this, Receipt::class.java)
                intent.putExtra("typeOfBank", bank);
                intent.putExtra("accountNum", cardNumber);
                intent.putExtra("amount", amount);
                startActivity(intent)
            }
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(this, R.array.bank_type,
            android.R.layout.simple_spinner_item).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }

        // calling the action bar
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setTitle("Payment")
            actionBar.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val text: String = parent?.getItemAtPosition(position).toString()
        tvTypeOfBank.text = text
    }
}

