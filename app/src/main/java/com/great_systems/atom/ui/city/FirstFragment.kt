package com.great_systems.atom.ui.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.great_systems.atom.Const.CITY_NAME_KEY
import com.great_systems.atom.ui.MainActivityViewModel
import com.great_systems.atom.R
import com.great_systems.atom.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val arg = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CityAdapter { cityName ->
            arg.putString(CITY_NAME_KEY, cityName)
        }

        val mLayoutManager = LinearLayoutManager(requireContext())
        binding.rvCity.layoutManager = mLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            mLayoutManager.orientation
        )
        binding.rvCity.addItemDecoration(dividerItemDecoration)
        binding.rvCity.adapter = adapter

        binding.buttonFirst.isEnabled = false
        activityViewModel.mapCityCharges.observe(this@FirstFragment) { map ->
            map.keys.toList().sortedBy { it }.let { cities ->
                adapter.setItems(cities)
                binding.buttonFirst.isEnabled = true
            }
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, arg)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activityViewModel.mapCityCharges.removeObservers(this@FirstFragment)
        _binding = null
    }
}