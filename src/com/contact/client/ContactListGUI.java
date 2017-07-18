package com.contact.client;

import java.util.List;


import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.HTMLTable.Cell;


public class ContactListGUI {

    private static final String CONTACT_LISTING_ROOT_PANEL = "contactListing";
    private static final String CONTACT_FORM_ROOT_PANEL = "contactForm";
    private static final String CONTACT_STATUS_ROOT_PANEL = "contactStatus";
    private static final String CONTACT_TOOL_BAR_ROOT_PANEL = "contactToolBar";
    private static final int EDIT_LINK = 3;
    private static final int REMOVE_LINK = 4;

    protected Button addButton;
    protected Button updateButton;
    protected PushButton addNewButton;
    protected TextBox nameField;
    protected TextBox emailField;
    protected TextBox phoneField;
    protected Label status;
    protected Grid contactGrid;
    protected Grid formGrid;

    private List<Contact> contacts;
    private Contact currentContact;
    protected ContactServiceDelegate contactService;

    public void init() {
        addButton = new Button("Добавить");
        addNewButton = new PushButton(new Image("img/add.png"));
        updateButton = new Button("Обновить");
        nameField = new TextBox();
        emailField = new TextBox();
        phoneField = new TextBox();
        status = new Label();
        contactGrid = new Grid(2, 5);

        buildForm();
        placeWidgets();
    }

    private void buildForm() {
        formGrid = new Grid(4, 3);
        formGrid.setVisible(false);

        formGrid.setWidget(0, 0, new Label("Имя"));
        formGrid.setWidget(0, 1, nameField);

        formGrid.setWidget(1, 0, new Label("Email"));
        formGrid.setWidget(1, 1, emailField);

        formGrid.setWidget(2, 0, new Label("Телефон"));
        formGrid.setWidget(2, 1, phoneField);

        formGrid.setWidget(3, 0, updateButton);
        formGrid.setWidget(3, 1, addButton);

    }

    private void placeWidgets() {
        RootPanel.get(CONTACT_LISTING_ROOT_PANEL).add(contactGrid);
        RootPanel.get(CONTACT_FORM_ROOT_PANEL).add(formGrid);
        RootPanel.get(CONTACT_STATUS_ROOT_PANEL).add(status);
        RootPanel.get(CONTACT_TOOL_BAR_ROOT_PANEL).add(addNewButton);
    }


    private void loadForm(Contact contact) {
        this.formGrid.setVisible(true);
        currentContact = contact;
        this.emailField.setText(contact.getEmail());
        this.phoneField.setText(contact.getPhone());
        this.nameField.setText(contact.getName());
    }


    private void copyFieldDateToContact() throws Exception {
        String email = emailField.getText();
        String name = nameField.getText();
        String phone = phoneField.getText();
        if (email.isEmpty() || !email.contains("@") || name.isEmpty() || phone.isEmpty()) {
            throw new Exception("fields empty");
        }
        currentContact.setEmail(email);
        currentContact.setName(name);
        currentContact.setPhone(phone);
    }

    public void guiEventContactGridClicked(Cell cellClicked) {
        int row = cellClicked.getRowIndex();
        int col = cellClicked.getCellIndex();

        Contact contact = this.contacts.get(row);
        this.status.setText("Выбран " + contact.getName());

        if (col == EDIT_LINK) {
            this.addNewButton.setVisible(false);
            this.updateButton.setVisible(true);
            this.addButton.setVisible(false);
            this.emailField.setReadOnly(true);
            loadForm(contact);
        } else if (col == REMOVE_LINK) {
            this.contactService.removeContact(contact);
        }
    }


    public void guiEventAddButtonClicked() {
        addNewButton.setVisible(true);
        formGrid.setVisible(false);
        try {
            copyFieldDateToContact();
            this.phoneField.getText();
            this.contactService.addContact(currentContact);
        } catch (Exception e) {
            Window.alert("Заполните поля.");
        }
    }

    public void guiEventUpdateButtonClicked() {
        addNewButton.setVisible(true);
        formGrid.setVisible(false);
        try {
            copyFieldDateToContact();
            this.contactService.updateContact(currentContact);
        } catch (Exception e) {
            Window.alert("Заполните поля.");
        }
    }

    public void guiEventAddNewButtonClicked() {
        this.addNewButton.setVisible(false);
        this.updateButton.setVisible(false);
        this.addButton.setVisible(true);
        this.emailField.setReadOnly(false);
        loadForm(new Contact());
    }


    public void serviceEventListRetrievedFromService(List<Contact> result) {
        status.setText("Список контактов");
        this.contacts = result;
        this.contactGrid.clear();
        this.contactGrid.resizeRows(this.contacts.size());
        int row = 0;

        for (Contact contact : result) {
            this.contactGrid.setWidget(row, 0, new Label(contact.getName()));
            this.contactGrid.setWidget(row, 1, new Label(contact.getPhone()));
            this.contactGrid.setWidget(row, 2, new HTML("<a href='mailto:" + contact.getEmail() + "'>" + contact.getEmail() + "</a>"));
            this.contactGrid.setWidget(row, EDIT_LINK, new Hyperlink(new SafeHtml() {
                @Override
                public String asString() {
                    return "<img src=img/pencil.png>";
                }
            }, null));
            this.contactGrid.setWidget(row, REMOVE_LINK, new Hyperlink(new SafeHtml() {
                @Override
                public String asString() {
                    return "<img src=img/delete.png>";
                }
            }, null));
            row++;
        }
    }

    public void serviceEventAddContactSuccessful() {
        status.setText("Контакт  добавлен");
        this.contactService.listContacts();
    }

    public void serviceEventUpdateSuccessful() {
        status.setText("Контакт был обновлен");
        this.contactService.listContacts();
    }

    public void serviceEventRemoveContactSuccessful() {
        status.setText("Контакт удален");
        this.contactService.listContacts();

    }

    public void serviceEventUpdateContactFailed(Throwable caught) {
        status.setText("Обновление не завершено");
    }

    public void serviceEventAddContactFailed(Throwable caught) {
        status.setText("Добавление не завершено");
    }

    public void serviceEventRemoveContactFailed(Throwable caught) {
        status.setText("Удаление не завершено");
    }

    public void serviceEventListContactsFailed(Throwable caught) {
        status.setText("Невозможно получить список контактов");

    }

}
