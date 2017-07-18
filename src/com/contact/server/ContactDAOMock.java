package com.contact.server;

import com.contact.client.Contact;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContactDAOMock implements ContactDAO {

    Map<String, Contact> map = new LinkedHashMap<String, Contact>();

    {
        map.put("name@gmail.com",
                new Contact("Андрей", "name@gmail.com", "520-555-1212"));
        map.put("name2@gmail.com",
                new Contact("Сергей", "name2@gmail.com", "520-555-1213"));
        map.put("name3@gmail.com",
                new Contact("Володя", "name3@gmail.com", "520-555-1214"));

    }

    public void addContact(Contact contact) {
        String email = contact.getEmail();
        map.put(email, contact);
    }

    public List<Contact> listContacts() {
        return Collections.unmodifiableList(new ArrayList<Contact>(map.values()));
    }

    public void removeContact(Contact contact) {
        map.remove(contact.getEmail());
    }

    public void updateContact(Contact contact) {
        map.put(contact.getEmail(), contact);
    }

}
