package com.g2.ago


import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val parada1 = LatLng(43.330306, -3.029750)
        val parada2 = LatLng(43.330611, -3.030861)
        val parada3 = LatLng(43.330778, -3.031583)
        val parada4 = LatLng(43.328917, -3.031500)
        val parada5 = LatLng(43.328861, -3.032944)
        val parada6 = LatLng(43.333750, -3.038722)
        val parada7 = LatLng(43.334417, -3.039278)
        val arrayParadas= arrayOf(parada1,parada2,parada3,parada4,parada5,parada6,parada7)
        for(i in 0..6){
            val nomMarcador="Parada${i+1}"
            googleMap.addMarker(MarkerOptions().position(arrayParadas[i]).title("Marker in $nomMarcador"))
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(arrayParadas[0], 15f))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}