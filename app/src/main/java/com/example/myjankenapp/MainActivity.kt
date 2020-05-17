package com.example.myjankenapp

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var  timer: Timer
    lateinit var handler: Handler
    var win_number: Int = 0
    var lose_number: Int = 0
    var win_number_text: String = "0"
    var lose_number_text: String = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPaper.setOnClickListener(this)
        buttonRock.setOnClickListener(this)
        buttonScissors.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        var f = 0


        handler = Handler()
        timer = Timer()
        timer.schedule(2000,100,{runOnUiThread{
            f++

            if (f == 1) {
                imageViewDefault.setImageResource(R.drawable.rock_black)
            }else if(f == 2){
                imageViewDefault.setImageResource(R.drawable.scissors_black)
            }else{
                imageViewDefault.setImageResource(R.drawable.paper_black)
            }

            if (f == 3){
                f = 0
            }
        }})
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }


    override fun onClick(v: View?) {

        buttonRock.isEnabled = false
        buttonScissors.isEnabled = false
        buttonPaper.isEnabled = false

        val n = Random().nextInt(3)
        if (n==0){
            imageViewResult.setImageResource(R.drawable.rock_black)
        }else if (n==1){
            imageViewResult.setImageResource(R.drawable.scissors_black)
        }else{
            imageViewResult.setImageResource(R.drawable.paper_black)
        }
        textViewResult.text = getString(R.string.pon)


        when(v?.id) {
            R.id.buttonRock
            -> buttonRock.setBackgroundResource(R.drawable.rock_red)

            R.id.buttonScissors
            -> buttonScissors.setBackgroundResource(R.drawable.scissors_red)

            R.id.buttonPaper
            -> buttonPaper.setBackgroundResource(R.drawable.paper_red)
        }


        imageViewResult.visibility = View.VISIBLE
        imageViewDefault.visibility = View.INVISIBLE
        handler.postDelayed(Runnable {
            when(v?.id){
                R.id.buttonRock
                -> {
                    buttonRock.setBackgroundResource(R.drawable.rock_black)
                    if (n == 0) {
                        textViewResult.text = getString(R.string.draw)
                    } else if (n == 1) {
                        textViewResult.text = getString(R.string.win)
                        win_number++
                        win_number_text = win_number.toString()
                    } else {
                        textViewResult.text = getString(R.string.lose)
                        lose_number++
                        lose_number_text = lose_number.toString()
                    }
                }

                R.id.buttonScissors
                -> {
                    buttonScissors.setBackgroundResource(R.drawable.scissors_black)
                    if (n==0){
                        textViewResult.text = getString(R.string.lose)
                        lose_number++
                        lose_number_text = lose_number.toString()
                    }else if (n==1){
                        textViewResult.text = getString(R.string.draw)
                    }else {
                        textViewResult.text = getString(R.string.win)
                        win_number++
                        win_number_text = win_number.toString()
                    }
                }

                R.id.buttonPaper
                -> {
                    buttonPaper.setBackgroundResource(R.drawable.paper_black)
                    if (n == 0) {
                        textViewResult.text = getString(R.string.win)
                        win_number++
                        win_number_text = win_number.toString()
                    } else if (n == 1) {
                        textViewResult.text = getString(R.string.lose)
                        lose_number++
                        lose_number_text = lose_number.toString()
                    } else {
                        textViewResult.text = getString(R.string.draw)
                    }
                }
            }
            textView.text = win_number_text + "勝" + lose_number_text + "敗"
        },1000)

        handler.postDelayed(Runnable {
            buttonRock.isEnabled = true
            buttonScissors.isEnabled = true
            buttonPaper.isEnabled = true

            textViewResult.text = getString(R.string.jannkenn)
            imageViewResult.visibility = View.INVISIBLE
            imageViewDefault.visibility = View.VISIBLE
        },2000)

    }
}
