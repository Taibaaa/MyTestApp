package com.test.interview

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.test.interview.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var onBackPressedCallback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ramazanMenu -> {
                    findNavController().navigate(R.id.ramzanFragment)
                    true
                }
                R.id.quranMenu -> {
                    findNavController().navigate(R.id.fragmentMenu2)
                    true
                }
                R.id.favoritesMenu -> {
                    findNavController().navigate(R.id.fragmentFavorite)
                    true
                }
                else -> false
            }
        }

        initializebackPress()
    }

    private fun initializebackPress() {
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                ExitDialogFragment().show(childFragmentManager, "exit_dialog")
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

    class ExitDialogFragment : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return AlertDialog.Builder(requireContext())
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Exit") { _, _ ->
                    requireActivity().finish()
                }
                .setNegativeButton("Cancel", null)
                .create()
        }
    }

}
