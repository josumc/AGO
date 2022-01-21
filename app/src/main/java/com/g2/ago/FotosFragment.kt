package com.g2.ago

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.FragmentFotosBinding


class FotosFragment : Fragment() {
    lateinit var binding: FragmentFotosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Sharedapp.atras.atras = "juegos"
        // Inflate the layout for this fragment
        binding = FragmentFotosBinding.inflate(layoutInflater)
        fotos()
        return binding.root
    }

    private fun fotos() {
        when(Sharedapp.puntopartida.Partida){
            "0"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        replaceFragment(MapsFragment2())
                    }
                    "2"-> {
                        replaceFragment(MapsFragment2())
                    }
                }
            }
            "1"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        binding.imagen.setImageResource(R.drawable.portua1argazkia)
                    }
                    "2"->{
                        binding.imagen.setImageResource(R.drawable.portua2argazkia)
                    }
                    "3"->{
                        replaceFragment(PuzzleFragment())
                    }
                    "5"->{
                        binding.imagen.setImageResource(R.drawable.portua3argazkia)
                    }
                }
            }
            "2"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        binding.imagen.setImageResource(R.drawable.kofradia1argazkia)
                    }
                    "2"->{
                        binding.imagen.setImageResource(R.drawable.kofradia2argazkia)
                    }
                    "3"->{
                        replaceFragment(MemoryFragment())
                    }
                }
            }
            "3"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        binding.imagen.setImageResource(R.drawable.sotera1argazkia)
                    }
                    "2"->{
                        binding.imagen.setImageResource(R.drawable.sotera2argazkia)
                    }
                    "3"->{
                        binding.imagen.setImageResource(R.drawable.sotera2argazkia)
                    }
                    "4"->{
                        replaceFragment(SLFragment())
                    }
                    "6"->{
                        binding.imagen.setImageResource(R.drawable.sotera3izaskunetxaniz)
                    }
                }
            }
            "4"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        binding.imagen.setImageResource(R.drawable.kioskoa1argazkia)

                    }
                    "2"->{
                        replaceFragment(PreguntasFragment())
                    }
                }
            }
            "5"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        binding.imagen.setImageResource(R.drawable.udaletxea1argazkia)
                    }
                    "2"->{
                        binding.imagen.setImageResource(R.drawable.udaletxea2argazkia)
                    }
                    "3"->{
                        replaceFragment(TestFragment())
                    }
                }
            }
            "6"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        binding.imagen.setImageResource(R.drawable.auzoa1argazkia)
                    }
                    "2"->{
                        binding.imagen.setImageResource(R.drawable.auzoa2argazkia)
                    }
                    "3"->{
                        replaceFragment(VFFragment())
                    }
                }
            }
            "7"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        binding.imagen.setImageResource(R.drawable.sardinera1argazkia)
                    }
                    "2"->{
                        binding.imagen.setImageResource(R.drawable.sardinera2argazkia)
                    }
                    "3"->{
                        binding.imagen.setImageResource(R.drawable.sardinera2argazkia)
                    }
                    "4"->{
                        replaceFragment(ParrafoFragment())
                    }

                }
            }
            "8"->{
                when(Sharedapp.puntojuego.Juego){
                    "1"->{
                        binding.imagen.setImageResource(R.drawable.hasierakoargazkia)
                    }
                    "2"->{
                        replaceFragment(APFragment())
                    }
                    "3"->{
                        replaceFragment(AnimacionFinalFragment())
                    }
                }
            }
        }
    }
    fun replaceFragment(fragment: Fragment) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        if(transaction != null) {
            transaction.replace(R.id.FragmentMapaJuego, fragment)
            transaction.disallowAddToBackStack()
            transaction.commit()
        }
    }

}