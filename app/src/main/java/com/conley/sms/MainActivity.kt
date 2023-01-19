package com.conley.sms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.IdRes
import com.twilio.Twilio
import com.twilio.http.TwilioRestClient
import com.twilio.rest.api.v2010.account.Message
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // removes the bar at the top of the activity
        try { this.supportActionBar!!.hide()
        } catch (e: NullPointerException) { }

        // phone number
        // 13204560991

        val sendButton: Button = findViewById(R.id.btn_send_sms)
        var phoneNumber: EditText = findViewById(R.id.phone_number)
        var messageContent: EditText = findViewById(R.id.message_content)



        sendButton.setOnClickListener {
            if(phoneNumber.length() < 10 || phoneNumber.length() > 11){
                Toast.makeText(applicationContext, "Incorrect Phone Number Format", Toast.LENGTH_SHORT)
            }else {
                sendMessage(phoneNumber.text.toString(), messageContent.text.toString())
            }
        }


        }

    fun sendMessage(number: String, messageContent: String){

        val ACCOUNT_SID: String = "AC5e990a3c1a51ba0f1ae4480c4620c299"
        val AUTH_TOKEN: String = "8380b62bf1dfa492c270870c3863e334"
        val myNumber: String = "+13204560991"

        val client = TwilioRestClient.Builder(
            ACCOUNT_SID,
            AUTH_TOKEN
        ).build()

        MessageCreator(
            PhoneNumber(myNumber),
            PhoneNumber(number),
            messageContent
        ).create(client)

        Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_SHORT)


    }


}

