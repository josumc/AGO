package com.g2.ago

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.g2.ago.databinding.QsactivityBinding

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
            abrirweb("https://api.whatsapp.com/send?phone=+3448980&text=Buenos%20d%C3%ADas.%20Necesito%20ayuda%20con%20la%20aplicacion.")
        }
        binding.txtwas.setOnClickListener{
            abrirweb("https://api.whatsapp.com/send?phone=+3448980&text=Buenos%20d%C3%ADas.%20Necesito%20ayuda%20con%20la%20aplicacion.")
        }
        //Enlace a Gmail
        binding.imggmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("G2_2DM3@fptxurdinaga.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "")
            intent.putExtra(Intent.EXTRA_TEXT, "")

            startActivity(Intent(intent))
        }
        binding.txtmail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("G2_2DM3@fptxurdinaga.com"))
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