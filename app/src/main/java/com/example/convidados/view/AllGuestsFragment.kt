package com.example.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.R
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.view.adapter.GuestAdapter
import com.example.convidados.view.listener.Guestlistener
import com.example.convidados.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter = GuestAdapter()
    private lateinit var mListener : Guestlistener

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all_guests, container, false)


        initializeRecyclerView(root)
        setListener()
        setObservers()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.loadAll(GuestConstants.GUEST_CONSTANTS.FILTERS.ALL)
    }

    private fun setListener(){
        mListener = object : Guestlistener{
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST_CONSTANTS.GUEST_ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.loadAll(GuestConstants.GUEST_CONSTANTS.FILTERS.ALL)
            }
        }
        mAdapter.attachListener(mListener)

    }

    private fun setObservers(){
        mViewModel.guests.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuestList(it)
        })
    }

    private fun initializeRecyclerView(root : View){
        val recyclerView = root.findViewById<RecyclerView>(R.id.rv_all_guests)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mAdapter
    }
}
