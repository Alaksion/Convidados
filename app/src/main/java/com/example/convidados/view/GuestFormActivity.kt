package com.example.convidados.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewmodel.GuestFormViewModel
import com.example.convidados.R
import com.example.convidados.service.constants.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)
        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        setObservers()
        loadData()
        rb_present.isChecked = true
    }

    private fun setListeners() {
        bt_save.setOnClickListener(this)
    }

    private fun setObservers() {
        mViewModel.saveGuestSuccess.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.guest.observe(this, Observer {
            et_name.setText(it.name)
            if (it.presence) {
                rb_present.isChecked = true
            } else {
                rb_absent.isChecked = true
            }
        })
    }

    private fun saveGuest() {
        val guestName = et_name.text.toString()
        val presence = rb_present.isChecked
        mViewModel.save(mGuestId, guestName, presence)
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUEST_CONSTANTS.GUEST_ID)
            mViewModel.loadGuest(mGuestId)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            bt_save.id -> saveGuest()
            else -> return
        }
    }
}