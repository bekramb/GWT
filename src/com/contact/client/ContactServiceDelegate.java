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
                                            gui.serviceEventListContactsFailed(caught);
                                        }

                                        public void onSuccess(List<Contact> result) {
                                            gui.serviceEventListRetrievedFromService(result);

                                        }
                                    }
        );
    }

    void addContact(final Contact contact) {
        contactService.addContact(contact, new AsyncCallback<Void> () {
                    public void onFailure(Throwable caught) {
                        gui.serviceEventAddContactFailed(caught);
                    }

                    public void onSuccess(Void result) {
                        gui.serviceEventAddContactSuccessful();
                    }
                }
        );
    }

    void updateContact(final Contact contact) {
        contactService.updateContact(contact, new AsyncCallback<Void> () {
                    public void onFailure(Throwable caught) {
                        gui.serviceEventUpdateContactFailed(caught);
                    }

                    public void onSuccess(Void result) {
                        gui.serviceEventUpdateSuccessful();
                    }
                }
        );
    }

    void removeContact(final Contact contact) {
        contactService.removeContact(contact, new AsyncCallback<Void>() {
                    public void onFailure(Throwable caught) {
                        gui.serviceEventRemoveContactFailed(caught);
                    }

                    public void onSuccess(Void result) {
                        gui.serviceEventRemoveContactSuccessful();
                    }
                }
        );
    }
}
