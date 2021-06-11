package com.candroid.realtracker.chatbot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.candroid.realtracker.R
import com.candroid.realtracker.chatbot.Constant.RECEIVE_ID
import com.candroid.realtracker.chatbot.Constant.SEND_ID
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.*

class ChatActivity : AppCompatActivity() {
    lateinit var rv_messages: RecyclerView
    lateinit var adapter: MessageAdapter
    val messageList = mutableListOf<ListMessage>()
    val botName = listOf("Rohan", "sallu", "shivam")
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        rv_messages = findViewById(R.id.rv_messages)
        toolbar = findViewById(R.id.toolbar)
        setUpToolbar()


        Recyclers()
        clickEvents()
        val random = (0..1).random()
        customBotMessage("Hello! Today you're speaking with ${botName[random]}, how may I help?")


    }

    private fun clickEvents() {

        //Send a message
        btn_send.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(1000)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun Recyclers() {
        adapter = MessageAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }


    fun sendMessage() {
        val message = et_message.text.toString()
        val time = TimeStamp.time()

        if (message.isNotEmpty()) {
            messageList.add(
                ListMessage(
                    message,
                    time,
                    SEND_ID
                )
            )
            et_message.setText("")
            adapter.setUp(
                ListMessage(
                    message,
                    time,
                    SEND_ID
                )
            )
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    fun botResponse(message: String) {
        val time = TimeStamp.time()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponse.response(message)
                messageList.add(
                    ListMessage(
                        response,
                        time,
                        RECEIVE_ID
                    )
                )
                adapter.setUp(
                    ListMessage(
                        response,
                        time,
                        RECEIVE_ID
                    )
                )
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }


    }

    private fun customBotMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val time = TimeStamp.time()
                messageList.add(
                    ListMessage(
                        message,
                        time,
                        RECEIVE_ID
                    )
                )
                adapter.setUp(
                    ListMessage(
                        message,
                        time,
                        RECEIVE_ID
                    )
                )
                rv_messages.scrollToPosition(adapter.itemCount - 1)

            }
        }
    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Back"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_l, R.anim.slide_out_r)
    }

}
