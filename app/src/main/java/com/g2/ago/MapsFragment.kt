package com.g2.ago

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.g2.ago.databinding.FragmentMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private lateinit var fusedLocation: FusedLocationProviderClient
    var Activityppal: Comunicador?=null
    lateinit var binding: FragmentMapsBinding
    lateinit var googleMapp: GoogleMap
    var marcador:String=""
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)
            return@OnMapReadyCallback
        }
        googleMap.isMyLocationEnabled=true
        googleMap.uiSettings.isMyLocationButtonEnabled = false
        //googleMap.uiSettings.isZoomControlsEnabled=true
        googleMap.uiSettings.isCompassEnabled=false
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
            googleMap.addMarker(MarkerOptions().position(it).title("Marker in $it"))
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

        fusedLocation.lastLocation.addOnSuccessListener {
            if (it != null) {
                val ubicacion = LatLng(it.latitude, it.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f))
                println("Ubicación actual. Latitud: "+it.latitude+". Longitud: "+it.longitude)
            }
        }

        googleMap.setOnMarkerClickListener { marker ->
            //Genera un mensaje "Prueba: "+mX .Donde X es la posición del array
            println("Prueba: "+marker.id)
            Activityppal=requireContext() as Comunicador
            Activityppal!!.onPasarDato(marker.id)
            true
        }

        googleMapp = googleMap
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapsBinding.inflate(layoutInflater)

        binding.UbicacionButton.setOnClickListener(){

            fusedLocation.lastLocation.addOnSuccessListener {
                if (it != null) {
                    val ubicacion = LatLng(it.latitude, it.longitude)
                    googleMapp.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f))
                    println("Ubicación actual. Latitud: "+it.latitude+". Longitud: "+it.longitude)
                }
            }

        }

        fusedLocation = LocationServices.getFusedLocationProviderClient(requireActivity())
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
    
}