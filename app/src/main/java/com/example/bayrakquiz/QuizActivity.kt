package com.example.bayrakquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var btnA:Button
    private lateinit var btnB:Button
    private lateinit var btnC:Button
    private lateinit var btnD:Button
    private lateinit var ivBayrak: ImageView
    private lateinit var tvDogru: TextView
    private lateinit var tvYanlis: TextView
    private lateinit var tvSorusayi: TextView

    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var tumSecenekler:HashSet<Bayraklar>
    private lateinit var dogruSoru:Bayraklar
    private lateinit var vt:VeritabaniYardimcisi

    private var soruSayac = 0
    private var dogruSayac = 0
    private var yanlisSayac = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        btnA = findViewById(R.id.btnA)
        btnB = findViewById(R.id.btnB)
        btnC = findViewById(R.id.btnC)
        btnD = findViewById(R.id.btnD)
        ivBayrak = findViewById(R.id.ivBayrak)
        tvDogru = findViewById(R.id.tvDogru)
        tvYanlis = findViewById(R.id.tvYanlis)
        tvSorusayi = findViewById(R.id.tvSorusayi)


        vt = VeritabaniYardimcisi(this)
        sorular =Bayraklardao().random5BayrakGetir(vt)

        soruYukle()


        btnA.setOnClickListener {
            dogruKontrol(btnA)
            soruSayacKontrol()

        }
        btnB.setOnClickListener {
            dogruKontrol(btnB)
            soruSayacKontrol()


        }
        btnC.setOnClickListener {
            dogruKontrol(btnC)
            soruSayacKontrol()

        }
        btnD.setOnClickListener {
            dogruKontrol(btnD)
            soruSayacKontrol()

        }
    }
    fun soruYukle(){
        tvSorusayi.text="${soruSayac+1}. Soru"

        dogruSoru = sorular.get(soruSayac)

        ivBayrak.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))

        yanlisSecenekler = Bayraklardao().random3YanlisGetir(vt,dogruSoru.bayrak_id)

        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))

        btnA.text = tumSecenekler.elementAt(0).bayrak_ad
        btnB.text = tumSecenekler.elementAt(1).bayrak_ad
        btnC.text = tumSecenekler.elementAt(2).bayrak_ad
        btnD.text = tumSecenekler.elementAt(3).bayrak_ad
    }

    fun soruSayacKontrol(){
        soruSayac++
        if(soruSayac!=10){
            soruYukle()
        }else{
            val intent = Intent(this@QuizActivity,SonucActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(intent)
            finish()
        }

    }
    fun dogruKontrol(button: Button){
        val butonYazi = button.text.toString()
        val dogruCevap = dogruSoru.bayrak_ad

        if(butonYazi == dogruCevap){
            dogruSayac++
        }else{
            yanlisSayac++
        }
        tvDogru.text = "Doğru : $dogruSayac"
        tvYanlis.text = "Yanlış : $yanlisSayac"
    }
}