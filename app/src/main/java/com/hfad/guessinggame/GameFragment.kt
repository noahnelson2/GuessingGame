package com.hfad.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.hfad.guessinggame.databinding.FragmentGameBinding
import androidx.navigation.findNavController
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel : GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        val view = binding.root

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.gameOver.observe(viewLifecycleOwner, Observer { newValue ->
            if(newValue) {
                val action = GameFragmentDirections
                    .actionGameFragmentToResultFragment(viewModel.wonLostMessage())
                view.findNavController().navigate(action)
            }
        })

        binding.guessButton.setOnClickListener(){
            var str = viewModel.incorrectGuesses.value.toString()
            var g = binding.guess.text.toString()
            val alpha = "abcdefghijklmnopqrstuvwxyz"

            if(alpha.contains(g, ignoreCase = true)){

                if(binding.guess.length() == 0){
                    val text = "Please select a character"
                    Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
                }

                else if( str.contains(g, ignoreCase = true) ) {
                    val t = "Already Guessed"
                    Toast.makeText(activity, t, Toast.LENGTH_LONG).show()
                }

                else{
                    viewModel.makeGuess(binding.guess.text.toString().uppercase())
                    binding.guess.text = null
                }
            }

            else{
                val note = "Invalid Guess"
                Toast.makeText(activity, note, Toast.LENGTH_LONG).show()
            }
        }

        binding.homeButton.setOnClickListener{
            view.findNavController()
                .navigate(R.id.action_gameFragment_to_welcomeFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}