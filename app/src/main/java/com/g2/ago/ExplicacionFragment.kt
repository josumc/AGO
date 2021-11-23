package com.g2.ago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.g2.ago.databinding.FragmentExplicacionBinding
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ExplicacionFragment : Fragment() {
    lateinit var binding: FragmentExplicacionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentExplicacionBinding.inflate(layoutInflater)
        val explicacion=explicaciones()
        binding.textView.typeWrite(this, explicacion, 33L)

        return binding.root
    }
    //Esta funcion se utiliza para cargar las letas del texto a utilizar de una en una
    fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, text: String, intervalMs: Long) {
        this@typeWrite.text = ""
        lifecycleOwner.lifecycleScope.launch {
            repeat(text.length) {
                delay(intervalMs)
                this@typeWrite.text = text.take(it + 1)
                binding.textView.setOnClickListener {
                    lifecycleOwner.lifecycleScope.cancel()
                    binding.textView.text=explicaciones()
                }
            }
        }
    }
    fun explicaciones(): String {
        var testua="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec consequat, sapien quis sodales lobortis, dui tellus bibendum dui, at consequat metus nisl ac nunc. Vivamus. "
        when(Sharedapp.puntopartida.Partida){
            "0"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"-> testua="Kaixo lagun! Ni “Bella Charo” sardina-saltzailea naiz eta gaur elkarrekin emakumeen papera Santurtziko historian ezagutuko dugu."
                    "2"-> testua="Santurtzi Bizkaiko udalerria da. Gaur egun 46.069 biztanle dituen arrantza portua. Herri honen historian emakumeen papera oso garrantzitsua izan da beti, horregatik aldarrikatu nahi dugu eta emakumeei merezi duten garrantzia eman nahi diegu. Hori lortzeko zure laguntza behar dugu. Istorioan zehar agertzen diren froga guztiak gainditu behar dituzu pistak lortzeko. Pista guztiak lortzen dituzunean, Santurtziko historiaren atzean dagoen misterioa jakingo duzu."
                }
            }
            "1"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){
                    "1"->"Santurtzi izan da baxurako arrantzarako ontzi gehien erabili dituen Bizkaiko portuetako bat. Antzinean, trainerua arrantzarako erabiltzen zen, baina pixkanaka motorrek mugitutako itsasontziak erabiltzen hasi ziren arrain gehiago hartu ahal izateko."
                    "2"->"Santurtziren izena San Jorge (gizon bat) santutik datorren arren, Karmengo Ama Birjina (emakume bat) da Santurtziko zaindaria. Karmengo Ama Birjina oso garrantzitsua da Santurtzirentzat. Horregatik, zure laguntza behar dugu puzzle hau konpontzeko! Prest zaude?"
                    "3"->"Primeran! Lehenengo froga bete duzu, hemen daukazu zure saria! Santurtziko jendeak sinesten du Karmengo Amak arrantzale eta herriko biztanle guztiak babesten dituela. Uztailaren 16a Karmengo eguna da eta itsas prozesio bat egiten da. Goazen hurrengo frogara!"
                }
            }
            "2"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){

                }
            }
            "3"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){

                }
            }
            "4"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){

                }
            }
            "5"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){

                }
            }
            "6"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){

                }
            }
            "7"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){

                }
            }
            "8"->{
                when(Sharedapp.puntojuego.PuntoJuego.toString()){

                }
            }
            
        }
        return testua
    }

}