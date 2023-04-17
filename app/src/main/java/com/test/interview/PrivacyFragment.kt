package com.test.interview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.test.interview.databinding.FragmentPrivacyBinding

class PrivacyFragment : Fragment() {

    private lateinit var binding: FragmentPrivacyBinding
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*return inflater.inflate(R.layout.fragment_privacy, container, false)*/
        binding = FragmentPrivacyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cbPrivacy.setOnCheckedChangeListener { _, isChecked ->
            binding.moveToHome.isEnabled = isChecked
        }

        binding.moveToHome.setOnClickListener {
            findNavController().navigate(R.id.action_privacyFragment_to_mainFragment)
        }

        // Initialize onBackPressedCallback
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finishAffinity()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}
