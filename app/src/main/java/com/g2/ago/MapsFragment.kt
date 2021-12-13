package com.g2.ago

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.g2.ago.databinding.FragmentMapsBinding
import com.google.android.gms.maps.GoogleMap
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.geofencing.GeofencingApi
import com.tomtom.online.sdk.geofencing.GeofencingException
import com.tomtom.online.sdk.geofencing.report.FenceDetails
import com.tomtom.online.sdk.geofencing.report.Report
import com.tomtom.online.sdk.geofencing.report.ReportCallback
import com.tomtom.online.sdk.geofencing.report.ReportQuery
import com.tomtom.online.sdk.location.LocationUpdateListener
import com.tomtom.online.sdk.map.*
import com.tomtom.online.sdk.map.style.layers.Visibility
import com.tomtom.online.sdk.routing.OnlineRoutingApi
import com.tomtom.online.sdk.routing.RoutingApi
import com.tomtom.online.sdk.routing.RoutingException
import com.tomtom.online.sdk.routing.route.*
import com.tomtom.online.sdk.routing.route.description.TravelMode
import com.tomtom.online.sdk.search.OnlineSearchApi
import com.tomtom.online.sdk.search.SearchApi
import com.tomtom.online.sdk.routing.route.information.FullRoute
import com.tomtom.online.sdk.map.ApiKeyType
import com.tomtom.online.sdk.map.driving.*
import com.tomtom.online.sdk.map.route.RouteLayerStyle
import java.util.*
import kotlin.concurrent.schedule


class MapsFragment : Fragment(), OnMapReadyCallback {

    private var Activityppal: JuegoActivity? = null
    private lateinit var binding: FragmentMapsBinding
    private lateinit var tomtomMap: TomtomMap
    private lateinit var searchApi: SearchApi
    private lateinit var routingApi: RoutingApi
    private lateinit var geofencingApi: GeofencingApi
    private var user: Location? = null
    private var route: Route? = null
    private val style = RouteLayerStyle.Builder()
        .color(R.color.Primary)
        .build()
    private lateinit var routeSettings: RouteSettings
    private lateinit var chevron: Chevron
    private lateinit var chevronIcon: Icon
    private var chevronPosition: ChevronPosition? = null
    private val travelMode = TravelMode.PEDESTRIAN
    private var departurePosition: LatLng? = null
    private var destinationPosition: LatLng? = null
    private var departureIcon: Icon? = null
    private var destinationIcon: Icon? = null
    private val parada1: LatLng = LatLng(43.330306, -3.029750)
    private val parada2: LatLng = LatLng(43.330611, -3.030861)
    private val parada3: LatLng = LatLng(43.330778, -3.031583)
    private val parada4: LatLng = LatLng(43.328917, -3.031500)
    private val parada5: LatLng = LatLng(43.328861, -3.032944)
    private val parada6: LatLng = LatLng(43.333750, -3.038722)
    private val parada7: LatLng = LatLng(43.334417, -3.039278)
    private val paradas: List<LatLng> = listOf(parada1, parada2, parada3, parada4, parada5, parada6, parada7)
    private val parada1Id: UUID = UUID.fromString("37d037ee-54c0-41d2-8a91-c6c1020f8157")
    private val parada2Id: UUID = UUID.fromString("8456f46b-df8e-489f-86b3-a3b036d67414")
    private val parada3Id: UUID = UUID.fromString("9cb8fbf6-8871-4767-bc5f-fcebafc3943f")
    private val parada4Id: UUID = UUID.fromString("3860b9fb-64de-4f2f-a8d6-91d88e7e16a7")
    private val parada5Id: UUID = UUID.fromString("957aa4a1-a7ef-4654-a3ad-f27724d67412")
    private val parada6Id: UUID = UUID.fromString("6aac243b-efe3-4388-ab13-eac69ca529a9")
    private val parada7Id: UUID = UUID.fromString("3a374021-de04-48ee-bba9-ef7288c17abc")
    private val projectId: UUID = UUID.fromString("32ee11b5-ed68-4f3d-a6dd-912b1ef33a93")
    //val arrayParadas= arrayOf(parada1,parada2,parada3,parada4,parada5,parada6,parada7)
    private val LAYERS_IN_3D_REGEX = listOf("Subway Station 3D", "Place of worship 3D", "Railway Station 3D", "Government Administration Office 3D", "Other building 3D", "School building 3D", "Other town block 3D", "Factory building 3D", "Hospital building 3D", "Hotel building 3D", "Cultural Facility 3D").joinToString(separator = "|")
    private lateinit var waypointG: Icon
    private lateinit var waypointR: Icon
    private lateinit var waypointV: Icon
    private var reporte: List<FenceDetails>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(layoutInflater)

        initTomTomServices()
        initUIViews()

        binding.UbicacionButton.setOnClickListener {
            this.tomtomMap.centerOnMyLocation();
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Sharedapp.users.user = ""
        Sharedapp.puntopartida.Partida = ""
        Sharedapp.puntojuego.Juego = ""
        Sharedapp.modolibre.modo = false
    }

    private fun clearMap() {
        tomtomMap.clear()
        departurePosition = null
        destinationPosition = null
        route = null
        chevronPosition = null
        tomtomMap.drivingSettings.removeChevrons()
    }

    private fun handleApiError(e: Throwable) {
        Toast.makeText(requireActivity().applicationContext, getString(R.string.api_response_error, e.localizedMessage), Toast.LENGTH_LONG).show()
    }

    private fun initUIViews() {
        departureIcon = Icon.Factory.fromResources(requireActivity().applicationContext, R.drawable.ic_map_route_departure)
        destinationIcon = Icon.Factory.fromResources(requireActivity().applicationContext, R.drawable.ic_map_route_destination)
        chevronIcon = Icon.Factory.fromResources(requireActivity().applicationContext, R.drawable.chevron_shadow)
        waypointG = Icon.Factory.fromDrawable("Gris", bitmapDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_location_on_24_gris)!!))
        waypointR = Icon.Factory.fromDrawable("Rojo", bitmapDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_location_on_24_rojo)!!))
        waypointV = Icon.Factory.fromDrawable("Verde", bitmapDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_location_on_24_verde)!!))
    }

    private fun initTomTomServices() {
        val mapKeys: MutableMap<ApiKeyType, String> = EnumMap(com.tomtom.online.sdk.map.ApiKeyType::class.java)
        mapKeys[ApiKeyType.MAPS_API_KEY] = "CBGLwGo9IgVptoM3zxy6G9zE4rxjFVAG"
        val mapProperties = MapProperties.Builder()
            .keys(mapKeys)
            .build()
        val mapFragment = MapFragment.newInstance(mapProperties)
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(R.id.map, mapFragment).commit()
        mapFragment.getAsyncMap(this)

        searchApi = OnlineSearchApi.create(requireActivity().applicationContext, "CBGLwGo9IgVptoM3zxy6G9zE4rxjFVAG")
        routingApi = OnlineRoutingApi.create(requireActivity().applicationContext, "CBGLwGo9IgVptoM3zxy6G9zE4rxjFVAG")
        geofencingApi = GeofencingApi(requireActivity().applicationContext, "CBGLwGo9IgVptoM3zxy6G9zE4rxjFVAG")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onMapReady(tomtomMap: TomtomMap) {
        this.tomtomMap = tomtomMap
        this.tomtomMap.let {
            it.uiSettings.compassView.hide()
            it.uiSettings.currentLocationView.hide()
            if (!Sharedapp.modolibre.modo) {

                it.isMyLocationEnabled = true

                val location = LocationUpdateListener { location ->
                    user = location
                    if (chevronPosition != null) {
                        chevronPosition = ChevronPosition.Builder(location).build()
                        chevron.position = chevronPosition
                    }
                    if (route != null) {
                        routeSettings.updateProgressAlongRoute(route!!.id, location)
                    }

                    rango(projectId)
                    if (reporte != null) {
                        if (reporte!!.size > 0) {
                            when (reporte!![0].fence.id) {
                                parada1Id -> {
                                    if (Sharedapp.puntopartida.Partida == "0") {
//                                        Toast.makeText(
//                                            context,
//                                            "Has entrado al juego No.1",
//                                            Toast.LENGTH_SHORT
//                                        ).show()
                                        Activityppal = activity as JuegoActivity?
                                        Activityppal?.onPasarDato("0")
                                    }
                                }
                                parada2Id -> {
                                    if (Sharedapp.puntopartida.Partida == "1") {
                                        Toast.makeText(
                                            context,
                                            "Has entrado al juego No.2",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Activityppal = activity as JuegoActivity?
                                        Activityppal?.onPasarDato("1")
                                    }
                                }
                                parada3Id -> {
                                    if (Sharedapp.puntopartida.Partida == "2") {
                                        Toast.makeText(
                                            context,
                                            "Has entrado al juego No.3",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Activityppal = activity as JuegoActivity?
                                        Activityppal?.onPasarDato("2")
                                    }
                                }
                                parada4Id -> {
                                    if (Sharedapp.puntopartida.Partida == "3") {
                                        Toast.makeText(
                                            context,
                                            "Has entrado al juego No.4",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Activityppal = activity as JuegoActivity?
                                        Activityppal?.onPasarDato("3")
                                    }
                                }
                                parada5Id -> {
                                    if (Sharedapp.puntopartida.Partida == "4") {
                                        Toast.makeText(
                                            context,
                                            "Has entrado al juego No.5",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Activityppal = activity as JuegoActivity?
                                        Activityppal?.onPasarDato("4")
                                    }
                                }
                                parada6Id -> {
                                    if (Sharedapp.puntopartida.Partida == "5") {
                                        Toast.makeText(
                                            context,
                                            "Has entrado al juego No.6",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Activityppal = activity as JuegoActivity?
                                        Activityppal!!.onPasarDato("5")
                                    }
                                }
                                parada7Id -> {
                                    if (Sharedapp.puntopartida.Partida == "6") {
                                        Toast.makeText(
                                            context,
                                            "Has entrado al juego No.7",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Activityppal = activity as JuegoActivity?
                                        Activityppal?.onPasarDato("6")
                                    }
                                }
                            }
                        }
                    }

                }

                it.addLocationUpdateListener(location)

                Timer().schedule(2000) {
                    when (Sharedapp.puntopartida.Partida) {
                        "0" -> {
                            clearMap()
                            drawRute(parada1)
                            drawWaypoints(parada1, waypointR)
                            drawWaypoints(parada2, waypointG)
                            drawWaypoints(parada3, waypointG)
                            drawWaypoints(parada4, waypointG)
                            drawWaypoints(parada5, waypointG)
                            drawWaypoints(parada6, waypointG)
                            drawWaypoints(parada7, waypointG)
                        }
                        "1" -> {
                            clearMap()
                            drawRute(parada2)
                            drawWaypoints(parada1, waypointV)
                            drawWaypoints(parada2, waypointR)
                            drawWaypoints(parada3, waypointG)
                            drawWaypoints(parada4, waypointG)
                            drawWaypoints(parada5, waypointG)
                            drawWaypoints(parada6, waypointG)
                            drawWaypoints(parada7, waypointG)
                        }
                        "2" -> {
                            clearMap()
                            drawRute(parada3)
                            drawWaypoints(parada1, waypointV)
                            drawWaypoints(parada2, waypointV)
                            drawWaypoints(parada3, waypointR)
                            drawWaypoints(parada4, waypointG)
                            drawWaypoints(parada5, waypointG)
                            drawWaypoints(parada6, waypointG)
                            drawWaypoints(parada7, waypointG)
                        }
                        "3" -> {
                            clearMap()
                            drawRute(parada4)
                            drawWaypoints(parada1, waypointV)
                            drawWaypoints(parada2, waypointV)
                            drawWaypoints(parada3, waypointV)
                            drawWaypoints(parada4, waypointR)
                            drawWaypoints(parada5, waypointG)
                            drawWaypoints(parada6, waypointG)
                            drawWaypoints(parada7, waypointG)
                        }
                        "4" -> {
                            clearMap()
                            drawRute(parada5)
                            drawWaypoints(parada1, waypointV)
                            drawWaypoints(parada2, waypointV)
                            drawWaypoints(parada3, waypointV)
                            drawWaypoints(parada4, waypointV)
                            drawWaypoints(parada5, waypointR)
                            drawWaypoints(parada6, waypointG)
                            drawWaypoints(parada7, waypointG)
                        }
                        "5" -> {
                            clearMap()
                            drawRute(parada6)
                            drawWaypoints(parada1, waypointV)
                            drawWaypoints(parada2, waypointV)
                            drawWaypoints(parada3, waypointV)
                            drawWaypoints(parada4, waypointV)
                            drawWaypoints(parada5, waypointV)
                            drawWaypoints(parada6, waypointR)
                            drawWaypoints(parada7, waypointG)
                        }
                        "6" -> {
                            clearMap()
                            drawRute(parada7)
                            drawWaypoints(parada1, waypointV)
                            drawWaypoints(parada2, waypointV)
                            drawWaypoints(parada3, waypointV)
                            drawWaypoints(parada4, waypointV)
                            drawWaypoints(parada5, waypointV)
                            drawWaypoints(parada6, waypointV)
                            drawWaypoints(parada7, waypointR)
                        }
                    }
                }
                val layers = tomtomMap.styleSettings.findLayersById(LAYERS_IN_3D_REGEX)
                layers.forEach {
                    it.visibility = Visibility.VISIBLE
                }
                routeSettings = it.routeSettings
            }else {
                clearMap()
                paradas.forEach{ marker ->
                    drawWaypoints(marker, waypointR)
                }
                val markerListener = TomtomMapCallback.OnMarkerClickListener { marker ->
                    Activityppal = activity as JuegoActivity?
                    Activityppal?.onPasarDato(marker.id.toString())
                }

                it.addOnMarkerClickListener(markerListener)
            }
        }
    }

    private fun drawWaypoints(latLng: LatLng, icon: Icon){
        tomtomMap.addMarker(MarkerBuilder(latLng)
            .icon(icon))
    }

    private fun drawRute(latLng: LatLng){
        departurePosition = LatLng(user!!.latitude, user!!.longitude)
        destinationPosition = latLng

        val routeDescriptor = RouteDescriptor.Builder()
            .travelMode(travelMode)
            .considerTraffic(false)
            .build()

        val routeCalculationDescriptor = RouteCalculationDescriptor.Builder()
            .routeDescription(routeDescriptor)
            .build()

        val routeSpecification = RouteSpecification.Builder(departurePosition!!, destinationPosition!!)
            .routeCalculationDescriptor(routeCalculationDescriptor)
            .build()

        routingApi.planRoute(routeSpecification, object : RouteCallback, MatcherListener {
            override fun onSuccess(routePlan: RoutePlan) {
                displayRoutes(routePlan.routes)
                tomtomMap.displayRoutesOverview()
            }

            override fun onError(error: RoutingException) {
                handleApiError(error)
                clearMap()
            }

            private fun displayRoutes(routes: List<FullRoute>) {
                for(fullRoute in routes) {
                    route = tomtomMap.addRoute(RouteBuilder(fullRoute.getCoordinates())
                        .style(RouteStyle.DEFAULT_ROUTE_STYLE)
//                                .startIcon(departureIcon)
//                                .endIcon(destinationIcon)
                        .tag("OLEOLEOLE LOS CARACOLE"))
                }
                routeSettings.activateProgressAlongRoute(route!!.id, style)
                val routeMatchingProvider = LatLngTraceMatchingDataProvider.fromPoints(routes.first().getCoordinates())
                val matcher = MatcherFactory.createMatcher(routeMatchingProvider)
                matcher.setMatcherListener(this)
                matcher.match(user!!)
            }

            override fun onMatched(matchResult: MatchResult) {
                chevronPosition = ChevronPosition.Builder(user!!).build()
                val chevronBuilder = ChevronBuilder.create(chevronIcon, chevronIcon)
                chevron = tomtomMap.drivingSettings.addChevron(chevronBuilder)
                chevron.isDimmed = tomtomMap.is2D
                chevron.position = chevronPosition
                chevron.show()
                tomtomMap.drivingSettings.startTracking(chevron)
                tomtomMap.overlaySettings.removeOverlays()
            }
        })
    }

    private fun bitmapDrawable(drawable: Drawable): BitmapDrawable {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return BitmapDrawable(resources, bitmap)
    }

    private fun rango(uuid: UUID){
        val query = ReportQuery.Builder(user!!)
            .projectId(uuid)
            .range(500F)
            .build()

        val resultListener = object :
            ReportCallback {
            override fun onSuccess(report: Report) {
                reporte = report.inside
            }

            override fun onError(error: GeofencingException) {
                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
            }
        }

        geofencingApi.obtainReport(query, resultListener)

        }
}