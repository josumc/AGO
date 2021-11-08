package com.g2.ago

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.g2.ago.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.qsactivity.*

//import com.g2.ago.databinding.QSActivityBinding

class QSActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qsactivity)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //llamar
        imgllamada.setOnClickListener{
            val nTel = Uri.parse("tel:"+txtllamada.text.toString())
            val phone = Intent(Intent.ACTION_DIAL, nTel)
            startActivity(phone)
        }
        txtllamada.setOnClickListener{
            val nTel = Uri.parse("tel:"+txtllamada.text.toString())
            val phone = Intent(Intent.ACTION_DIAL, nTel)
            startActivity(phone)
        }
        //enlace a WhatsAPP
        imgwas.setOnClickListener{
            abrirweb("https://www.instagram.com/ago_Santurtzi/?hl=es")
        }
        txtwas.setOnClickListener{
            abrirweb("https://www.instagram.com/ago_Santurtzi/?hl=es")
        }
        //Enlace a Gmail
        imggmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, "ago@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            intent.putExtra(Intent.EXTRA_TEXT, "")

            startActivity(Intent(intent))
        }
        txtmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, "ago@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            intent.putExtra(Intent.EXTRA_TEXT, "")

            startActivity(Intent(intent))
        }
        //Enlace a instagram
        imginsta.setOnClickListener{
            abrirweb("https://www.instagram.com/ago_Santurtzi/?hl=es")
        }
        txtinsta.setOnClickListener{
            abrirweb("https://www.instagram.com/ago_Santurtzi/?hl=es")
        }
        //Enlace a twiter
        imgtwiter.setOnClickListener{
            abrirweb("https://twitter.com/ago_santurtzi")
        }
        txttuit.setOnClickListener{
            abrirweb("https://twitter.com/ago_santurtzi")
        }
        //enlace a facebook
        imgfacebook.setOnClickListener{
            abrirweb("https://es-la.facebook.com/ago_santurtzi/")
        }
        txtfacebook.setOnClickListener{
            abrirweb("https://es-la.facebook.com/ago_santurtzi/")
        }


    }
    fun abrirweb(web:String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(web))
        startActivity(browserIntent)
    }
}