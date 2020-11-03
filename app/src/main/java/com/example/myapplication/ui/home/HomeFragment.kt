package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import kotlinx.android.synthetic.main.auction_dashboard_card.*
import kotlinx.android.synthetic.main.pre_offer_auction_dashboar_card.*

class HomeFragment : Fragment() {

    lateinit var auctionDashboardCard: View
    lateinit var preOfferDashboardCard : View

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        auctionDashboardCard = inflater.inflate(R.layout.auction_dashboard_card, container, false)
        preOfferDashboardCard = inflater.inflate(R.layout.pre_offer_auction_dashboar_card, container, false)
       // val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
           // textView.text = it
        })
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        show_auction_list_txt.setOnClickListener {

        }

        show_pre_offer_list_txt.setOnClickListener {

        }
    }
}