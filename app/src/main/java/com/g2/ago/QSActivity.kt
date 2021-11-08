package com.g2.ago

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.g2.ago.databinding.QsactivityBinding
//import kotlinx.android.synthetic.main.qsactivity.*

//import com.g2.ago.databinding.QSActivityBinding

class QSActivity : AppCompatActivity() {
    lateinit var binding:QsactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qsactivity)
        binding = QsactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //llamar
        binding.imgllamada.setOnClickListener{
            val nTel = Uri.parse("tel:"+binding.txtllamada.text.toString())
            val phone = Intent(Intent.ACTION_DIAL, nTel)
            startActivity(phone)
        }
        binding.txtllamada.setOnClickListener{
            val nTel = Uri.parse("tel:"+binding.txtllamada.text.toString())
            val phone = Intent(Intent.ACTION_DIAL, nTel)
            startActivity(phone)
        }
        //enlace a WhatsAPP
        binding.imgwas.setOnClickListener{
            abrirweb("https://wa.me/48980?text=Me%20interesa%20el%20auto%20que%20estás%20vendiendo")
        }
        binding.txtwas.setOnClickListener{
            abrirweb("https://wa.me/48980?text=Buenos%20días.%20Necesito%20ayuda%20con%20la%20aplicacion.")
        }
        //Enlace a Gmail
        binding.imggmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, "ago@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            intent.putExtra(Intent.EXTRA_TEXT, "")

            startActivity(Intent(intent))
        }
        binding.txtmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, "ago@gmail.com")
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            intent.putExtra(Intent.EXTRA_TEXT, "")

            startActivity(Intent(intent))
        }
        //Enlace a instagram
        binding.imginsta.setOnClickListener{
            abrirweb("https://www.instagram.com/ago_Santurtzi/?hl=es")
        }
        binding.txtinsta.setOnClickListener{
            abrirweb("https://www.instagram.com/ago_Santurtzi/?hl=es")
        }
        //Enlace a twiter
        binding.imgtwiter.setOnClickListener{
            abrirweb("https://twitter.com/ago_santurtzi")
        }
        binding.txttuit.setOnClickListener{
            abrirweb("https://twitter.com/ago_santurtzi")
        }
        //enlace a facebook
        binding.imgfacebook.setOnClickListener{
            abrirweb("https://es-la.facebook.com/ago_santurtzi/")
        }
        binding.txtfacebook.setOnClickListener{
            abrirweb("https://es-la.facebook.com/ago_santurtzi/")
        }


    }
    fun abrirweb(web:String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(web))
        startActivity(browserIntent)
    }
}