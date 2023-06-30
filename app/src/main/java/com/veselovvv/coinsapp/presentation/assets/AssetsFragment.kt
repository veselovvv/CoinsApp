package com.veselovvv.coinsapp.presentation.assets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.veselovvv.coinsapp.R
import com.veselovvv.coinsapp.databinding.FragmentAssetsBinding
import dagger.hilt.android.AndroidEntryPoint

// TODO @AndroidEntryPoint
class AssetsFragment : Fragment() {
    private var _binding: FragmentAssetsBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy {
        requireActivity().findNavController(R.id.fragment_container_view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAssetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}