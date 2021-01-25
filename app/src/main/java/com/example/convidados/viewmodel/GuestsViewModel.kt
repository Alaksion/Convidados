package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.service.constants.GuestConstants
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repostiory.GuestRepository
import java.lang.Error

class GuestsViewModel(app: Application) : AndroidViewModel(app) {

    private val mGuestRepository = GuestRepository(app.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()

    val guests: LiveData<List<GuestModel>> = mGuestList

    fun loadAll(filterId: Int) {

        when (filterId) {
            GuestConstants.GUEST_CONSTANTS.FILTERS.ALL ->
                mGuestList.value = mGuestRepository.getAll()

            GuestConstants.GUEST_CONSTANTS.FILTERS.PRESENTS ->
                mGuestList.value = mGuestRepository.getPresents()

            GuestConstants.GUEST_CONSTANTS.FILTERS.ABSENTS ->
                mGuestList.value = mGuestRepository.getAbsents()

            else -> throw Error("Invalid filterId, please check GuestConstants for a list of valid filters")
        }
    }

    fun delete(guestId: Int) {
        val guest = mGuestRepository.getSingle(guestId)
        mGuestRepository.delete(guest)
    }
}