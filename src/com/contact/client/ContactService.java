package com.contact.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("contacts")
public interface ContactService extends RemoteService {
    List<Contact> listContacts();
    void addContact(Contact contact);
    void removeContact(Contact contact);
    void updateContact(Contact contact);
}
