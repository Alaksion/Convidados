package com.example.convidados.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repostiory.GuestRepository

class GuestFormViewModel(app: Application) : AndroidViewModel(app) {

    private val mGuestRepository = GuestRepository(app.applicationContext)

    private val mSaveGuestSuccess = MutableLiveData<Boolean>()
    val saveGuestSuccess: LiveData<Boolean> = mSaveGuestSuccess

    private val mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest

    fun save(guestId: Int, name: String, presence: Boolean) {

        if (name.isEmpty()) {
            mSaveGuestSuccess.value = false
            return
        }
        val guest = GuestModel()
        guest.id = guestId
        guest.name = name
        guest.presence = presence

        mSaveGuestSuccess.value =
            if (guest.id == 0) mGuestRepository.save(guest) else mGuestRepository.update(guest)
    }

    fun loadGuest(id: Int) {
        val guest = mGuestRepository.getSingle(id)
        mGuest.value = guest
    }

}