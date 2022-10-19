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
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()

    var currentCardDisplayIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()


        /*if(allFlashcards.size > 0) {
            findViewById<TextView>(R.id.flashcard_question).text = allFlashcards[0].question
            findViewById<TextView>(R.id.flashcard_answer).text = allFlashcards[0].answer
        }*/
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        val flashcardChoiceOne = findViewById<TextView>(R.id.choiceOne)
        val flashcardChoiceTwo = findViewById<TextView>(R.id.choiceTwo)

        if (allFlashcards.size > 0) {
            flashcardQuestion.text = allFlashcards[0].question
            flashcardAnswer.text = allFlashcards[0].answer
        }
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            // This code is executed in StartingActivity after we come back from EndingActivity

            // This extracts any data that was passed back from EndingActivity
            val data: Intent? = result.data
            // ToDo: Execute more code here

            if (data != null) {
                val questionString = data.getStringExtra("QUESTION_KEY")
                val answerString = data.getStringExtra("ANSWER_KEY")
                val rightAnswerString = data.getStringExtra("rightAnswer_Key")
                val wrongChoiceOne = data.getStringExtra("wrongChoiceOne_Key")

                flashcardQuestion.text = questionString
                flashcardAnswer.text = answerString
                flashcardChoiceOne.text = rightAnswerString
                flashcardChoiceTwo.text = wrongChoiceOne



                if (!questionString.isNullOrEmpty() && !answerString.isNullOrEmpty()) {
                    flashcardDatabase.insertCard(Flashcard(questionString, answerString))
                    allFlashcards = flashcardDatabase.getAllCards().toMutableList()
                }
            } else {
                Log.i("Federico: MainActivity", "Returned null data from AddCardActivity")
            }

        }

        val addQuestionButton = findViewById<ImageView>(R.id.add_question_button)
        addQuestionButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }

        val nextButton = findViewById<ImageView>(R.id.next_button)
        nextButton.setOnClickListener {
            currentCardDisplayIndex++

            if (currentCardDisplayIndex >= allFlashcards.size){
                currentCardDisplayIndex = 0
            }

            allFlashcards = flashcardDatabase.getAllCards().toMutableList()

           val question = allFlashcards[currentCardDisplayIndex].question
           val answer = allFlashcards[currentCardDisplayIndex].answer

            flashcardQuestion.text = question
            flashcardAnswer.text = answer

        }

        val editQuestionButton = findViewById<ImageView>(R.id.edit_question_button)
        editQuestionButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }

        /*findViewById<ImageView>(R.id.delete_question_button).setOnClickListener {
            val flashcardQuestionToDelete = findViewById<TextView>(R.id.flashcard_question).text.toString()
            flashcardDatabase.deleteCard(flashcardQuestionToDelete)
        }*/




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

        var shouldShowAnswers = true

        val eyeVisible = findViewById<ImageView>(R.id.answers_showing)

        eyeVisible.setOnClickListener {
            if (shouldShowAnswers){
                eyeVisible.setImageResource(R.drawable.eye_closed)
                findViewById<TextView>(R.id.choiceOne).visibility = View.VISIBLE;
                findViewById<TextView>(R.id.choiceTwo).visibility = View.VISIBLE;
                findViewById<TextView>(R.id.choiceThree).visibility = View.VISIBLE;
                shouldShowAnswers = false
            } else {
                eyeVisible.setImageResource(R.drawable.eye_open)
                findViewById<TextView>(R.id.choiceOne).visibility = View.INVISIBLE;
                findViewById<TextView>(R.id.choiceTwo).visibility = View.INVISIBLE;
                findViewById<TextView>(R.id.choiceThree).visibility = View.INVISIBLE;
                shouldShowAnswers = true
            }
        }

    }
}