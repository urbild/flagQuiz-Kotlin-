package com.example.bayrakquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {

    private lateinit var btnBasla:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        veritabaniKopyala()

        btnBasla = findViewById(R.id.btnBasla)


        btnBasla.setOnClickListener {
            startActivity(Intent(this@MainActivity,QuizActivity::class.java))
        }
    }
    fun veritabaniKopyala(){
        val copyHelper = DatabaseCopyHelper(this)

        try{
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch(e:Exception){
            e.printStackTrace()
        }
    }
}