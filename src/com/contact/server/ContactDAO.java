package com.contact.server;

import com.contact.client.Contact;

import java.util.List;

public interface ContactDAO {
    void addContact(Contact contact);
    void removeContact(Contact contact);
    void updateContact(Contact contact);
    List<Contact> listContacts();
}