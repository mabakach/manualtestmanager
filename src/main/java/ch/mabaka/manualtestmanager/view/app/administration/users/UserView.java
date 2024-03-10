/*
 * The MIT License
 *
 * Copyright (c) 2009-2021 PrimeTek
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ch.mabaka.manualtestmanager.view.app.administration.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;

import ch.mabaka.manualtestmanager.persistence.entities.authorization.User;
import ch.mabaka.manualtestmanager.service.authorization.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class UserView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    UserService userService;
    private User selectedUser;
    private List<User> selectedUsers;

    @PostConstruct
    public void init() {
        selectedUsers = new ArrayList<>();
    }

    public List<User> getUsers() {
        return userService.findAll();
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(final User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<User> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(final List<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public void openNew() {
        selectedUser = new User();
    }

    public void saveUser() {
        
    	if (selectedUser.getSysid() == null) {
    		selectedUser = userService.save(selectedUser);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Added"));
        } else {
        	selectedUser = userService.save(selectedUser);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteUser() {
    	userService.delete(selectedUser);
        selectedUser = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedUsers()) {
            int size = selectedUsers.size();
            return size > 1 ? size + " users selected" : "1 user selected";
        }

        return "Delete";
    }

    public boolean hasSelectedUsers() {
        return selectedUsers != null && !selectedUsers.isEmpty();
    }

    public void deleteSelectedUsers() {
    	selectedUsers.forEach(u -> userService.delete(u));
        selectedUsers = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Users Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

}