package com.sohel.mansuri.contactapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sohel.mansuri.contactapp.models.Contact
import com.sohel.mansuri.contactapp.repositories.ContactsRepository
import kotlinx.android.synthetic.main.activity_add_edit_contact.*
import kotlin.random.Random

class AddEditContactActivity : AppCompatActivity() {

    private var INVALID_CONTACT_ID = -1
    var idOfContactToEdit: Int = INVALID_CONTACT_ID

    private var addMode = false
    private var contactToEdit: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_contact)

        idOfContactToEdit = intent.getIntExtra("CONTACT_ID", INVALID_CONTACT_ID)
        if(idOfContactToEdit == INVALID_CONTACT_ID) addMode = true
        if(addMode) {
            addSaveButton.text = "Add"
        } else {
            addSaveButton.text = "Save"
            contactToEdit = ContactsRepository.getSingleContact(idOfContactToEdit)
            nameET.setText(contactToEdit?.name ?: "")
            phoneNumberET.setText(contactToEdit?.phoneNumber ?: "")
        }

        addSaveButton.setOnClickListener {
            val nameFromET = nameET.text.toString()
            val phoneNumberFromET = phoneNumberET.text.toString()

            if(addMode) {
                ContactsRepository.addContact(Contact(Random.nextInt(500, 1000), nameFromET, phoneNumberFromET))
            } else {
                contactToEdit?.name = nameFromET
                contactToEdit?.phoneNumber = phoneNumberFromET
                ContactsRepository.editContact(contactToEdit ?: Contact(-1, "", ""))
            }
            finish()
        }
    }
}
