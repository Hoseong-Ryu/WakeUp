package com.example.wakeup

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.wakeup.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


class MapsFragment : Fragment() {
    private var binding : FragmentMapsBinding? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var mGeoCoder = Geocoder(context)
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        binding!!.setting.setOnClickListener {
            findNavController().navigate(R.id.action_mapsFragment_to_AlarmFragment)
        }

        binding!!.searchQuery.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val mResultLocation = mGeoCoder.getFromLocationName(binding!!.searchQuery.text.toString(),1)
                val mLat = mResultLocation[0].latitude
                val mLng = mResultLocation[0].longitude
                Toast.makeText(context,mLat.toString() + mLng.toString(),Toast.LENGTH_SHORT).show()
            }
            false
        }

        return view
    }

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val seoul = LatLng(37.5642135, 127.0016985)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(seoul))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}