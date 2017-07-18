package com.contact.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface ContactServiceAsync  {
    void listContacts(AsyncCallback<List<Contact>> callback);
    void addContact(Contact contact, AsyncCallback<Void> callback);
    void removeContact(Contact contact, AsyncCallback<Void> callback);
    void updateContact(Contact contact, AsyncCallback<Void> callback);
}
