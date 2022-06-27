package com.hfad.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        val beginButton = view.findViewById<Button>(R.id.begin_button)

        beginButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_welcomeFragment_to_gameFragment)
        }

        // Inflate the layout for this fragment
        return view
    }
}