package com.example.codepathweek1project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)


        val questionEditText = findViewById<EditText>(R.id.flashcard_question_edittext)
        val answerEditText = findViewById<EditText>(R.id.flashcard_answer_edittext)
        val rightChoiceAnswerEditText = findViewById<EditText>(R.id.flashcard_right_answer)
        val firstWrongChoiceEditText = findViewById<EditText>(R.id.flashcard_wrong_AnswerTwo)


        val saveButton = findViewById<ImageView>(R.id.flashcard_save_button)

        saveButton.setOnClickListener {
            val questionString = questionEditText.text.toString()
            val answerString = answerEditText.text.toString()
            val rightAnswerStringChoice = rightChoiceAnswerEditText.text.toString()
            val firstWrongStringChoice = firstWrongChoiceEditText.text.toString()


            val data = Intent()
            data.putExtra("QUESTION_KEY", questionString)
            data.putExtra("ANSWER_KEY", answerString)
            data.putExtra("rightAnswer_Key", rightAnswerStringChoice)
            data.putExtra("wrongChoiceOne_Key", firstWrongStringChoice)

            setResult(RESULT_OK, data)
            finish()
            //Toast.makeText(applicationContext())


        }

        val cancelButton = findViewById<ImageView>(R.id.flashcard_cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }

    }
}