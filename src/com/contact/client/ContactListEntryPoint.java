package com.contact.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

public class ContactListEntryPoint implements EntryPoint {
    private ContactListGUI gui;
    private ContactServiceDelegate delegate;

    public void onModuleLoad() {

        gui = new ContactListGUI();
        delegate = new ContactServiceDelegate();
        gui.contactService = delegate;
        delegate.gui = gui;
        gui.init();
        delegate.listContacts();
        wireGUIEvents();


    }

    private void wireGUIEvents() {
        gui.contactGrid.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                Cell cellForEvent = gui.contactGrid.getCellForEvent(event);
                gui.gui_eventContactGridClicked(cellForEvent);
            }});

        gui.addButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                gui.gui_eventAddButtonClicked();
            }});

        gui.updateButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                gui.gui_eventUpdateButtonClicked();
            }});

        gui.addNewButton.addClickHandler(new ClickHandler(){
            public void onClick(ClickEvent event) {
                gui.gui_eventAddNewButtonClicked();
            }});

    }
}
