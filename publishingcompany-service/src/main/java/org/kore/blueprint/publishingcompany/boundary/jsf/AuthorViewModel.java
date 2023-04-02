/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jsf;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
public class AuthorViewModel {
    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    private String firstname;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    private String lastname;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Override
    public String toString() {
        return "AuthorViewModel{" + "firstname=" + firstname + ", lastname=" + lastname + '}';
    }

}
