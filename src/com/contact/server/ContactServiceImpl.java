package com.contact.server;


import com.contact.client.ContactService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.contact.client.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl extends RemoteServiceServlet implements ContactService {
    private static final long serialVersionUID = 1L;
    private ContactDAO contactDAO = new ContactDAOMock();

    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    public List<Contact> listContacts() {
        List<Contact> listContacts = contactDAO.listContacts();
        return new ArrayList<Contact>(listContacts);
    }

    public void removeContact(Contact contact) {
        contactDAO.removeContact(contact);

    }

    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }


}
