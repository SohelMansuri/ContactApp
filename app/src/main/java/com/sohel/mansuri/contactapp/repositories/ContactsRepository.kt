package com.sohel.mansuri.contactapp.repositories

import android.arch.lifecycle.MutableLiveData
import com.sohel.mansuri.contactapp.models.Contact

object ContactsRepository {
    private val contacts: ArrayList<Contact> = arrayListOf()

    fun getContacts(): MutableLiveData<List<Contact>> {
        initContacts()

        val data = MutableLiveData<List<Contact>>()
        data.value = contacts
        return data
    }

    private fun initContacts() {
        contacts.add(Contact(1, "Mom", "16302920102"))
        contacts.add(Contact(2, "Dad", "13829298383"))
        contacts.add(Contact(3, "Friend", "12726378282"))
        contacts.add(Contact(4, "Colleague", "18273930303"))
        contacts.add(Contact(5, "Cousin", "12232332222"))
        contacts.add(Contact(6, "Student", "13012928181"))
        contacts.add(Contact(7, "Teacher", "17658435648"))
        contacts.add(Contact(8, "Dog", "18272344567"))
        contacts.add(Contact(9, "Cat", "17283932929"))
        contacts.add(Contact(10, "Neighbor", "12343323456"))
        contacts.add(Contact(11, "Gamer", "19289292727"))
        contacts.add(Contact(12, "Gamer 1", "18299298383"))
        contacts.add(Contact(13, "Gamer 2", "18289398383"))
        contacts.add(Contact(14, "Gamer 3", "12829298282"))
        contacts.add(Contact(15, "Gamer 4", "17675645467"))
    }

    fun getSingleContact(idOfContactToEdit: Int): Contact? {
        for(contact in contacts) {
            if(contact.id == idOfContactToEdit) {
                return contact
            }
        }
        return null
    }

    fun addContact(contact: Contact) {
        contacts.add(contact)
    }

    fun editContact(contact: Contact) {
        for(i in 0 until contacts.size) {
            if(contacts[i].id == contact.id) {
                contacts[i] = contact
                break
            }
        }
    }
}