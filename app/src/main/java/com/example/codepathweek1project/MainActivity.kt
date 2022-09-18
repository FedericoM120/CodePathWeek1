package com.example.codepathweek1project

import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val isShowingAnswer: Boolean = true;

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