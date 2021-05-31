package com.candroid.realtracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_options.*
import java.time.Instant

/**
 * A simple [Fragment] subclass.
 */
class OptionsFragment : Fragment() {
lateinit var img:ImageView
 lateinit var cardPlus:CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_options, container, false)
img=view.findViewById(R.id.img)
cardPlus=view.findViewById(R.id.cardPlus)
        img.setOnClickListener {
    val intent=Intent(context,MainActivity::class.java)
startActivity(intent)
}
cardPlus.setOnClickListener {

    it.findNavController().navigate(R.id.action_optionsFragment_to_bicepsFragment)


}
    return view
    }



}
