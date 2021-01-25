package com.example.convidados.service.repostiory

import android.content.Context
import com.example.convidados.service.model.GuestModel

class GuestRepository constructor(context: Context) {

    private val mDatabase = GuestDatabase.getDatabase(context).guestDao()

    fun getAll(): List<GuestModel> {
        return mDatabase.getAll()
    }

    fun getAbsents(): List<GuestModel> {
        return mDatabase.getAbsents()
    }

    fun getPresents(): List<GuestModel> {
        return mDatabase.getPresents()
    }

    fun getSingle(guestId: Int): GuestModel {
        return mDatabase.getSingle(guestId)
    }

    fun save(guest: GuestModel): Boolean {
        return mDatabase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDatabase.update(guest) > 0
    }

    fun delete(guest: GuestModel): Boolean {
        return mDatabase.delete(guest) > 0
    }

}
