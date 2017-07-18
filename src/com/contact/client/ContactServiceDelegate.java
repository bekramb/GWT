package com.contact.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public class ContactServiceDelegate {
    private ContactServiceAsync contactService = GWT.create(ContactService.class);
    ContactListGUI gui;

    void listContacts() {
        contactService.listContacts(new AsyncCallback<List<Contact>> () {
                                        public void onFailure(Throwable caught) {
                                            gui.service_eventListContactsFailed(caught);
                                        }

                                        public void onSuccess(List<Contact> result) {
                                            gui.service_eventListRetrievedFromService(result);

                                        }
                                    }
        );
    }

    void addContact(final Contact contact) {
        contactService.addContact(contact, new AsyncCallback<Void> () {
                    public void onFailure(Throwable caught) {
                        gui.service_eventAddContactFailed(caught);
                    }

                    public void onSuccess(Void result) {
                        gui.service_eventAddContactSuccessful();
                    }
                }
        );
    }

    void updateContact(final Contact contact) {
        contactService.updateContact(contact, new AsyncCallback<Void> () {
                    public void onFailure(Throwable caught) {
                        gui.service_eventUpdateContactFailed(caught);
                    }

                    public void onSuccess(Void result) {
                        gui.service_eventUpdateSuccessful();
                    }
                }
        );
    }

    void removeContact(final Contact contact) {
        contactService.removeContact(contact, new AsyncCallback<Void>() {
                    public void onFailure(Throwable caught) {
                        gui.service_eventRemoveContactFailed(caught);
                    }

                    public void onSuccess(Void result) {
                        gui.service_eventRemoveContactSuccessful();
                    }
                }
        );
    }
}
