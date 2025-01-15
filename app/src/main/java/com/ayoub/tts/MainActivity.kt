package com.ayoub.tts

import android.app.Activity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.Locale

class MainActivity : Activity() {
    private var t1: TextToSpeech? = null
    private lateinit var ed1: EditText
    private lateinit var b1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ed1 = findViewById(R.id.editText)
        b1 = findViewById(R.id.button)

        t1 = TextToSpeech(applicationContext) { status ->
            if (status != TextToSpeech.ERROR) {
                t1?.language = Locale.UK
            }
        }

        b1.setOnClickListener {
            val toSpeak = ed1.text.toString()
            Toast.makeText(applicationContext, toSpeak, Toast.LENGTH_SHORT).show()
            t1?.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onPause() {
        t1?.stop()
        t1?.shutdown()
        super.onPause()
    }
}
