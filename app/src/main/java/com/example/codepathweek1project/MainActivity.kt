package com.example.codepathweek1project

import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.myButton).setOnClickListener {
            val intent = Intent(this, EndingActivity::class.java)
            startActivity(intent)
        }


        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)

        flashcardQuestion.setOnClickListener {
            flashcardAnswer.visibility = View.VISIBLE
            flashcardQuestion.visibility = View.INVISIBLE
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            // This code is executed in StartingActivity after we come back from EndingActivity

            // This extracts any data that was passed back from EndingActivity
            val data: Intent? = result.data
            // ToDo: Execute more code here

        }

        findViewById<View>(R.id.myButton).setOnClickListener {
            val intent = Intent(this, EndingActivity::class.java)
            // Launch EndingActivity with the resultLauncher so we can execute more code
            // once we come back here from EndingActivity
            resultLauncher.launch(intent)
        }

        findViewById<View>(R.id.button).setOnClickListener {
            finish()
        }




        findViewById<TextView>(R.id.flashcard_question).setOnClickListener {
            findViewById<TextView>(R.id.flashcard_question).visibility = View.INVISIBLE;
            findViewById<TextView>(R.id.flashcard_answer).visibility = View.VISIBLE;
        }

        findViewById<TextView>(R.id.flashcard_answer).setOnClickListener {
            findViewById<TextView>(R.id.flashcard_question).visibility = View.VISIBLE;
            findViewById<TextView>(R.id.flashcard_answer).visibility = View.INVISIBLE;
        }

        findViewById<TextView>(R.id.choiceOne).setOnClickListener {
            findViewById<TextView>(R.id.choiceOne).setBackgroundColor(Color.RED);
        }

        findViewById<TextView>(R.id.choiceTwo).setOnClickListener {
            findViewById<TextView>(R.id.choiceTwo).setBackgroundColor(Color.RED)
        }

        findViewById<TextView>(R.id.choiceThree).setOnClickListener {
            findViewById<TextView>(R.id.choiceThree).setBackgroundColor(Color.GREEN)
        }


        findViewById<ImageView>(R.id.answers_showing).setOnClickListener() {
            findViewById<ImageView>(R.id.answers_showing).visibility = View.INVISIBLE
            findViewById<ImageView>(R.id.answers_notShowing).visibility = View.VISIBLE
            findViewById<TextView>(R.id.choiceOne).visibility = View.INVISIBLE;
            findViewById<TextView>(R.id.choiceTwo).visibility = View.INVISIBLE;
            findViewById<TextView>(R.id.choiceThree).visibility = View.INVISIBLE;
        }

        findViewById<ImageView>(R.id.answers_notShowing).setOnClickListener() {
            findViewById<ImageView>(R.id.answers_notShowing).visibility = View.INVISIBLE
            findViewById<ImageView>(R.id.answers_showing).visibility = View.VISIBLE
            findViewById<TextView>(R.id.choiceOne).visibility = View.VISIBLE;
            findViewById<TextView>(R.id.choiceTwo).visibility = View.VISIBLE;
            findViewById<TextView>(R.id.choiceThree).visibility = View.VISIBLE;
        }



    }
}