package com.example.bayrakquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SonucActivity : AppCompatActivity() {

    private lateinit var btnTekrar:Button
    private lateinit var tvBasari:TextView
    private lateinit var tvSonuc:TextView
    private lateinit var tvTampuan:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sonuc)

        btnTekrar = findViewById(R.id.btnTekrar)
        tvBasari = findViewById(R.id.tvBasari)
        tvSonuc = findViewById(R.id.tvSonuc)
        tvTampuan = findViewById(R.id.tvTampuan)

        val dogruSayac = intent.getIntExtra("dogruSayac",0)

        tvSonuc.text = "$dogruSayac DOĞRU ${10-dogruSayac} YANLIŞ"

        tvBasari.text = "%${(dogruSayac*100)/10}"

        if(((dogruSayac*100)/10)==100){
            tvTampuan.text = "ZEHİR GİBİSİN ZEHİRRR"
        }

        btnTekrar.setOnClickListener {
            startActivity(Intent(this@SonucActivity,QuizActivity::class.java))
            finish()
        }

    }
}