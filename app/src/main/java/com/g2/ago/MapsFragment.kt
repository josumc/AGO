package com.g2.ago

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.g2.ago.databinding.FragmentMapsBinding
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.location.LocationUpdateListener
import com.tomtom.online.sdk.map.*
import com.tomtom.online.sdk.map.R
import com.tomtom.online.sdk.map.driving.LatLngTraceMatchingDataProvider
import com.tomtom.online.sdk.map.driving.MatchResult
import com.tomtom.online.sdk.map.driving.MatcherFactory
import com.tomtom.online.sdk.map.driving.MatcherListener
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
import java.util.*


class MapsFragment : Fragment(), OnMapReadyCallback, LocationUpdateListener {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var tomtomMap: TomtomMap
    private lateinit var searchApi: SearchApi
    private lateinit var routingApi: RoutingApi
    private var user: Location? = null
    private var route: Route? = null
    private lateinit var chevron: Chevron
    private val travelMode = TravelMode.PEDESTRIAN
    private var departurePosition: LatLng? = null
    private var destinationPosition: LatLng? = null
    private var departureIcon: Icon? = null
    private var destinationIcon: Icon? = null
    val parada1 = LatLng(43.330306, -3.029750)
    val parada2 = LatLng(43.330611, -3.030861)
    val parada3 = LatLng(43.330778, -3.031583)
    val parada4 = LatLng(43.328917, -3.031500)
    val parada5 = LatLng(43.328861, -3.032944)
    val parada6 = LatLng(43.333750, -3.038722)
    val parada7 = LatLng(43.334417, -3.039278)
    val arrayParadas= arrayOf(parada1,parada2,parada3,parada4,parada5,parada6,parada7)
    val LAYERS_IN_3D_REGEX = listOf("Subway Station 3D", "Place of worship 3D", "Railway Station 3D", "Government Administration Office 3D", "Other building 3D", "School building 3D", "Other town block 3D", "Factory building 3D", "Hospital building 3D", "Hotel building 3D", "Cultural Facility 3D").joinToString(separator = "|")
    lateinit var waypointG: Drawable
    lateinit var waypointR: Drawable
    lateinit var waypointV: Drawable
    var bitmapDrawable: BitmapDrawable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(layoutInflater)

        initTomTomServices()
        initUIViews()

        val drawable = this.resources.getDrawable(R.drawable.ic_baseline_location_on_24_rojo, theme)
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        bitmapDrawable = BitmapDrawable(this.resources, bitmap)

        binding.UbicacionButton.setOnClickListener {

        }
        return binding.root
    }

    private fun clearMap() {
        tomtomMap.clearRoute()
        departurePosition = null
        destinationPosition = null
        route = null
    }

    private fun handleApiError(e: Throwable) {
        Toast.makeText(requireActivity().applicationContext, getString(R.string.api_response_error, e.localizedMessage), Toast.LENGTH_LONG).show()
    }

    private fun initUIViews() {
        departureIcon = Icon.Factory.fromResources(requireActivity().applicationContext, R.drawable.ic_map_route_departure)
        destinationIcon = Icon.Factory.fromResources(requireActivity().applicationContext, R.drawable.ic_map_route_destination)
        waypointG = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_location_on_24_gris, null)!!
        waypointR = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_location_on_24_rojo, null)!!
        waypointV = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_location_on_24_verde, null)!!
    }

    private fun initTomTomServices() {
        val mapKeys: MutableMap<ApiKeyType, String> = EnumMap(com.tomtom.online.sdk.map.ApiKeyType::class.java)
        mapKeys[ApiKeyType.MAPS_API_KEY] = "CBGLwGo9IgVptoM3zxy6G9zE4rxjFVAG"
        val mapProperties = MapProperties.Builder()
            .keys(mapKeys)
            .build()
        val mapFragment = MapFragment.newInstance(mapProperties)
        val j: JuegoActivity? = activity as JuegoActivity?
        j?.replaceMapFragment(mapFragment)
        mapFragment.getAsyncMap(this)

        searchApi = OnlineSearchApi.create(requireActivity().applicationContext, "CBGLwGo9IgVptoM3zxy6G9zE4rxjFVAG")
        routingApi = OnlineRoutingApi.create(requireActivity().applicationContext, "CBGLwGo9IgVptoM3zxy6G9zE4rxjFVAG")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onMapReady(tomtomMap: TomtomMap) {
        this.tomtomMap = tomtomMap
        this.tomtomMap.let {
            it.isMyLocationEnabled = true
            user = it.userLocation
            val layers = tomtomMap.styleSettings.findLayersById(LAYERS_IN_3D_REGEX)
            layers.forEach {
                it.visibility = Visibility.VISIBLE
            }
            arrayParadas.forEach {
                tomtomMap.addMarker(MarkerBuilder(it)
                    .icon(Icon.Factory.fromDrawable("$it",bitmapDrawable!!))
                    .markerBalloon(SimpleMarkerBalloon("$it"))
                    .tag(it)
                    .iconAnchor(MarkerAnchor.Bottom))
            }

            val markerListener = TomtomMapCallback.OnMarkerClickListener { marker ->
                clearMap()
                Log.v(TAG, "LA POSICION ES: ${it.userLocation}")
                departurePosition = LatLng(user!!.latitude, user!!.longitude)
                destinationPosition = LatLng(marker.position.latitude, marker.position.longitude)

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
                        val routeMatchingProvider = LatLngTraceMatchingDataProvider.fromPoints(routes.first().getCoordinates())
                        val matcher = MatcherFactory.createMatcher(routeMatchingProvider)
                        matcher.setMatcherListener(this)
                        matcher.match(user!!)
                    }

                    override fun onMatched(matchResult: MatchResult) {
                        val chevronPosition = ChevronPosition.Builder(it.userLocation!!).build()
                        val chevronBuilder = ChevronBuilder.create(Icon.Factory.fromDrawable("$it",bitmapDrawable!!), Icon.Factory.fromDrawable("$it",bitmapDrawable!!))
                        chevron = tomtomMap.drivingSettings.addChevron(chevronBuilder)
                        chevron.isDimmed = it.is2D
                        chevron.position = chevronPosition
                        chevron.show()
                        tomtomMap.drivingSettings.startTracking(chevron)
                    }
                })
            }

            tomtomMap.addOnMarkerClickListener(markerListener)
            tomtomMap.addLocationUpdateListener(this)

        }
    }

    override fun onLocationChanged(location: Location?) {
        user = tomtomMap.userLocation
    }
}