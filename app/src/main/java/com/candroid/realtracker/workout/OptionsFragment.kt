package com.candroid.realtracker.workout

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.candroid.realtracker.R

/**
 * A simple [Fragment] subclass.
 */
class OptionsFragment : Fragment() {
lateinit var img:ImageView
 lateinit var cardPlus:CardView
 lateinit var cardSolder:CardView
 lateinit var cardSub:CardView
 lateinit var cardTriceps:CardView
 lateinit var cardCardio:CardView
 lateinit var cardBack:CardView
 lateinit var cardLeg:CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_options, container, false)
img=view.findViewById(R.id.img)


        cardSolder=view.findViewById(R.id.cardSolder)
        cardBack=view.findViewById(R.id.cardBack)
        cardLeg=view.findViewById(R.id.cardLeg)
        cardCardio=view.findViewById(R.id.cardCardio)
cardPlus=view.findViewById(R.id.cardPlus)
        cardSub=view.findViewById(R.id.cardSub)
        cardTriceps=view.findViewById(R.id.cardTriceps)
        cardLeg.setOnClickListener {
            it.findNavController().navigate(R.id.action_optionsFragment_to_legFragment)

        }
        cardCardio.setOnClickListener {
            it.findNavController().navigate(R.id.action_optionsFragment_to_cardioFragment)

        }
        cardBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_optionsFragment_to_backFragment)

        }

        cardTriceps.setOnClickListener {
            it.findNavController().navigate(R.id.action_optionsFragment_to_tricepsFragment)
        }
        img.setOnClickListener {
    val intent=Intent(context, MainActivity::class.java)
startActivity(intent)
}
        cardSub.setOnClickListener {
            it.findNavController().navigate(R.id.action_optionsFragment_to_BFragment)
        }

cardPlus.setOnClickListener {

    it.findNavController().navigate(R.id.action_optionsFragment_to_bicepsFragment)


}
        cardSolder.setOnClickListener {
            it.findNavController().navigate(R.id.action_optionsFragment_to_solderFragment)
        }


    return view



    }



}
