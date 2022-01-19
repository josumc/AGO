package com.g2.ago

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

class MapsFragment2 : Fragment() {
    //Durante este fragment van a aparecer tres lineas marcadas como error pero que funcionan bien  (es un error de AS)
    private lateinit var fusedLocation: FusedLocationProviderClient
    lateinit var binding: FragmentMapsBinding
    lateinit var Activityppal: Comunicador
    lateinit var googleMap: GoogleMap
    lateinit var ubicacion:LatLng
    var marcadores:ArrayList<Marker> = arrayListOf()
    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { GoogleMap ->
        googleMap=GoogleMap

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        //Solicitud de permisos
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
            return@OnMapReadyCallback
        }
        //Configuración del mapa
        /*
        *
        Error 1
        *
         */
        if (!Sharedapp.modolibre.modo){
            googleMap.isMyLocationEnabled=true
            googleMap.uiSettings.isMyLocationButtonEnabled = false
            //googleMap.uiSettings.isZoomControlsEnabled=true
            googleMap.uiSettings.isCompassEnabled=false
        }
        //Código para introducir os puntos/marcadores
        //Coordenadas de las diferentes ubicaciones
        val parada1 = LatLng(43.330306, -3.029750)
        val parada2 = LatLng(43.330611, -3.030861)
        val parada3 = LatLng(43.330778, -3.031583)
        val parada4 = LatLng(43.328917, -3.031500)
        val parada5 = LatLng(43.328861, -3.032944)
        val parada6 = LatLng(43.333750, -3.038722)
        val parada7 = LatLng(43.334417, -3.039278)
        //Array de ubicaciones
        val arrayParadas= arrayOf(parada1,parada2,parada3,parada4,parada5,parada6,parada7)

        //Generar marcadores y ubicar la cámara
        arrayParadas.forEach {
            val marcador= googleMap.addMarker(MarkerOptions().position(it).title("Marker in $it"))
//            googleMap.addMarker(MarkerOptions().position(it).title("Marker in $it"))
            if (marcador != null) {
                marcadores.add(marcador)
            }
        }
        if (!Sharedapp.modolibre.modo&&Sharedapp.tipousu.tipo=="alumno"){
            cambiarMarcador(Sharedapp.puntopartida.Partida.toInt())
        }

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        requireContext(),
                        R.raw.styleday
                    )
                )
            } // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> {
                googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        requireContext(),
                        R.raw.stylenight
                    )
                )
            } // Night mode is active, we're using dark theme
        }

        /*
        *
        Error 2
        *
         */
        if(!Sharedapp.modolibre.modo){
            fusedLocation.lastLocation.addOnSuccessListener {
                ubicacion = LatLng(it.latitude, it.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f))
                println("Ubicación actual. Latitud: "+it.latitude+". Longitud: "+it.longitude)
            }
        }else{
            ubicacion = LatLng(43.3351509,-3.0331127)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f))
        }
        Activityppal=requireContext() as Comunicador
        //Se activa el botón de juego al seleccionar un punto
        if(Sharedapp.modolibre.modo){
            googleMap.setOnMarkerClickListener { marker ->
                //Genera un mensaje "Prueba: "+mX .Donde X es la id del marcador
                println("Prueba: "+marker.id)
                Activityppal.onPasarDato(marker.id.substring(1,2))
                true
            }
            val handler= Handler()
            handler.postDelayed({
                JuegoActivity().slideView(requireActivity().findViewById(R.id.FragmentMapaJuego), requireActivity().findViewById<LinearLayout>(R.id.Linearjuego).height)
            }, 500)
        }

        /*Autofocus de la cámara al cambiar la ubicación
        (ahora está comentado por una cuestión de funcionalidad)*/
        if(!Sharedapp.modolibre.modo){
            googleMap.setOnMyLocationChangeListener {
                ubicacion= LatLng(it.latitude, it.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 17f))
                var distancia=FloatArray(3)

                //Distancia con las paradas
//                if (Sharedapp.puntopartida.Partida.toInt() == 0){
//                    Location.distanceBetween(ubicacion.latitude,ubicacion.longitude,arrayParadas[Sharedapp.puntopartida.Partida.toInt()].latitude,arrayParadas[Sharedapp.puntopartida.Partida.toInt()].longitude,distancia)
//                }else if (Sharedapp.puntopartida.Partida.toInt() in 1..7){
//                    Location.distanceBetween(ubicacion.latitude,ubicacion.longitude,arrayParadas[Sharedapp.puntopartida.Partida.toInt()-1].latitude,arrayParadas[Sharedapp.puntopartida.Partida.toInt()-1].longitude,distancia)
//                }

                //Distancia con CIFP Txurdinaga LHII
                //Location.distanceBetween(ubicacion.latitude,ubicacion.longitude,43.257686, -2.902560,distancia)

                if (distancia[0]<50){
                    Activityppal.activarBoton(true)
                }else{
                    Activityppal.activarBoton(false)
                }
            }
        }

    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(layoutInflater)
        binding.UbicacionButton.setOnClickListener {

            /*
            *
            Error 3
            *
            */
            if(!Sharedapp.modolibre.modo) {
                fusedLocation.lastLocation.addOnSuccessListener {
                    ubicacion = LatLng(it.latitude, it.longitude)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f))
                }
            }
        }
        fusedLocation = LocationServices.getFusedLocationProviderClient(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Sharedapp.atras.atras = "mapa"
        val mapsFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapsFragment?.getMapAsync(callback)
    }
    fun cambiarMarcador(posicion:Int){
        marcadores.forEach {
            when {
                marcadores.indexOf(it)<(posicion-1) -> {
                    it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                }
                marcadores.indexOf(it)==(posicion-1) -> {
                    it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                }
                marcadores.indexOf(it)>(posicion-1) -> {
                    it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                }
            }
        }
    }


    override fun onDestroy() {
        JuegoActivity().slideView(requireActivity().findViewById(R.id.FragmentMapaJuego), 0)
        super.onDestroy()
    }

    override fun onPause() {
        JuegoActivity().slideView(requireActivity().findViewById(R.id.FragmentMapaJuego), 0)
        super.onPause()
    }

}