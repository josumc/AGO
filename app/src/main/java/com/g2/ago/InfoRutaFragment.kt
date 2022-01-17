package com.g2.ago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.FragmentInfoRutaBinding
import kotlinx.android.synthetic.main.fragment_info_ruta.*

private lateinit var binding: FragmentInfoRutaBinding
class InfoRutaFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoRutaBinding.inflate(layoutInflater)


        if(Sharedapp.tipousu.tipo=="alumno"||!Sharedapp.modolibre.modo){
            if (Sharedapp.puntopartida.Partida=="0"){
                replaceFragment(ExplicacionFragment())
            }else{
                //Genera el nombre del string que tiene que buscar
                val parada ="parada"+ Sharedapp.puntopartida.Partida
                //Usando la variable parada, busca el número de ID
                val actividad=activity
                val resID = resources.getIdentifier(parada,"string", actividad!!.packageName)

                //getString() recibe el ID que hemos recogido en la línea anterior y carga ese texto en el textView
                binding.Ubicacion.text=getString(resID)
            }
            when(Sharedapp.puntopartida.Partida){
                "1"->{
                    binding.ImagenInfoRuta.setImageResource(R.drawable.portua2argazkia)
                }
                "2"->{
                    binding.ImagenInfoRuta.setImageResource(R.drawable.kofradia1argazkia)
                }
                "3"->{
                    binding.ImagenInfoRuta.setImageResource(R.drawable.sotera1argazkia)
                }
                "4"->{
                    binding.ImagenInfoRuta.setImageResource(R.drawable.kioskoa1argazkia)
                }
                "5"->{
                    binding.ImagenInfoRuta.setImageResource(R.drawable.udaletxea1argazkia)
                }
                "6"->{
                    binding.ImagenInfoRuta.setImageResource(R.drawable.auzoa1argazkia)
                }
                "7"->{
                    binding.ImagenInfoRuta.setImageResource(R.drawable.sardinera1argazkia)
                }
            }

//            binding.EmpezarJuego.text=getString(R.string.Jarraitu)
            binding.EmpezarJuego.setOnClickListener{
                Toast.makeText(requireContext(), "${EmpezarJuego.isEnabled}", Toast.LENGTH_SHORT).show()
            }
        }
        if (Sharedapp.modolibre.modo){
            binding.Ubicacion.text=getString(R.string.GeralekuMezua)
            binding.EmpezarJuego.visibility= View.INVISIBLE

        }

        return binding.root
    }

    fun hasi(activar:Boolean){
        binding.EmpezarJuego.isEnabled = activar
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if(transaction != null) {
            transaction.replace(R.id.FragmentExplicacionJuego, fragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}