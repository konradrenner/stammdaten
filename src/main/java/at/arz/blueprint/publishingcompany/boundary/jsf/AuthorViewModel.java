/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.boundary.jsf;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
