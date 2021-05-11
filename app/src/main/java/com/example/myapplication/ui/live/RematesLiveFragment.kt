package com.example.myapplication.ui.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRematesLiveBinding
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel

class RematesLiveFragment : Fragment() {

    lateinit var binding: FragmentRematesLiveBinding

    private val list = mutableListOf<CarouselItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remates_live, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRematesLiveBinding.bind(view)
        //val carousel: ImageCarousel = binding.carousel THIS LIBRARY DOESN'T WORK WITH DATA BINDING!

        val carousel: ImageCarousel = view.findViewById(R.id.carousel)

        list.add( CarouselItem( imageUrl = "https://www.defrentealcampo.com.ar/wp-content/uploads/2020/11/84.jpg",
                                caption = "Remate en Minas"))
        list.add( CarouselItem( imageUrl = "https://www.angustresmarias.com/wp-content/uploads/2020/08/FINAL_FB-1080x675.jpg",
                caption = "Cabaña Campo Claro - Florida"))
        list.add( CarouselItem( imageUrl = "https://agroverdad.com.ar/wp-content/uploads/2020/08/braford-lossocavones-remate-650x404.jpg",
                caption = "Estancia Los Montes - Durazno"))

       carousel.addData(list)


        binding.nextRematesTv.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_remates_live_to_navigation_next_remates_fragment)
        }

        binding.preOfferTv.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_remates_live_to_pre_offer_fragment)
        }
    }

}