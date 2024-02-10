package com.great_systems.atom.ui.charge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.great_systems.atom.Const.CITY_NAME_KEY
import com.great_systems.atom.ui.MainActivityViewModel
import com.great_systems.atom.R
import com.great_systems.atom.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private val activityViewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colors = listOf<Int>(
            ContextCompat.getColor(requireContext(), R.color.busy_color),
            ContextCompat.getColor(requireContext(), R.color.free_color)
        )

        val adapter = ChargerAdapter(colors) { chargers ->
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val mLayoutManager = LinearLayoutManager(requireContext())
        binding.rvCharger.layoutManager = mLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            mLayoutManager.orientation
        )
        binding.rvCharger.addItemDecoration(dividerItemDecoration)
        binding.rvCharger.adapter = adapter

        arguments?.getString(CITY_NAME_KEY)?.let { cityName ->
            activityViewModel.mapCityCharges.value?.let { chargers ->
                chargers[cityName]?.let { adapter.setItems (it) }
            }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}