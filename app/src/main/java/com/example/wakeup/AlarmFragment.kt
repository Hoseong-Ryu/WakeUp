package com.example.wakeup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wakeup.databinding.FragmentAlarmBinding
import com.example.wakeup.databinding.FragmentMapsBinding


class AlarmFragment : Fragment() {
    private var binding: FragmentAlarmBinding? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container, false)
        val view = binding!!.root
        val place = arrayListOf<Place>()
        for (i in 0..30) {
            place.add(Place("서울", 100))
        }
        binding!!.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AlarmAdapter(place) {place ->
                Toast.makeText(context, "$place", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

}