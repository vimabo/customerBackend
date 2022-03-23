/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.brujula.customers.dto;

import com.cmc.brujula.customers.model.Customers;



/**
 *
 * @author bocanegravm
 */
public class CustomerDto {

    private int id;
    private String firstName;
    private String lastName;
    private String company;
    private String mensaje;

    public static CustomerDto createCustomerDto(Customers customer){
        return new CustomerDto(customer.getId(), customer.getFirstName(), 
                customer.getLastName(), customer.getCompany());
    }
    
    public CustomerDto(){
        
    }

    public CustomerDto(int id, String firstName, String lastName, String company) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setCompany(company);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    @Override
    public String toString() {
        return "CustomerDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
