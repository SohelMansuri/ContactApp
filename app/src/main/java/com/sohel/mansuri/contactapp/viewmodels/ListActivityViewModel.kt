package com.sohel.mansuri.contactapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sohel.mansuri.contactapp.models.Contact
import com.sohel.mansuri.contactapp.repositories.ContactsRepository

class ListActivityViewModel : ViewModel() {
    private var contacts: MutableLiveData<List<Contact>> = MutableLiveData()

    fun init() {
        contacts = ContactsRepository.getContacts()
    }

    fun getListOfContactsInStringFormat() : MutableLiveData<List<String>> {
        val listOfContactStrings = arrayListOf<String>()
        contacts.value?.map {
            it.name?.let { name ->
                listOfContactStrings.add(name)
            }
        }

        val tempContacts = MutableLiveData<List<String>>()
        tempContacts.value = listOfContactStrings
        return tempContacts
    }

    fun getContactIDFromName(name: String): Int? {
        contacts.value?.let {
            for (i in 0 until it.size) {
                if(it[i].name.equals(name)) {
                    return it[i].id
                }
            }
        }
        return null
    }
}