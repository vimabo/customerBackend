/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.brujula.customers.services;

import com.cmc.brujula.customers.dto.CustomerDto;
import com.cmc.brujula.customers.model.Customers;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmc.brujula.customers.repository.CustomerRepository;

/**
 *
 * @author bocanegravm
 */
@Service
@Transactional
public class CustomerServices {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Metodo para buscar todos los clientes en BD.
     *
     * @author Victor Bocanegra
     * @return List<Customers>
     */
    public List<Customers> list() {
        return customerRepository.findAll();
    }

    /**
     * Metodo para buscar un cliente por id en BD.
     *
     * @param id
     * @author Victor Bocanegra
     * @return CustomerDto
     */
    public CustomerDto getCustomer(int id) {
        CustomerDto response;
        Optional<Customers> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return CustomerDto.createCustomerDto(customer.get());
        } else {
            response = new CustomerDto();
            response.setMensaje("El cliente con id: " + id + " no se encuentra en BD");
            return response;
        }

    }

    /**
     * Metodo para guardar un cliente en BD.
     *
     * @author Victor Bocanegra
     * @param request
     * @return CustomerDto
     */
    public CustomerDto saveCliente(CustomerDto request) {

        CustomerDto response = validateRequest(request, true);

        if (response.getMensaje() == null) {
            Customers customer = new Customers();
            customer.setFirstName(request.getFirstName());
            customer.setLastName(request.getLastName());
            customer.setCompany(request.getCompany());
            customer = customerRepository.save(customer);
            response.setId(customer.getId());
            response.setMensaje("Cliente creado con id: " + customer.getId() + " satisfatoriamente");
            return response;
        } else {
            return response;
        }
    }

    /**
     * Metodo para actualizar un cliente en BD.
     *
     * @author Victor Bocanegra
     * @param request
     * @return CustomerDto
     */
    public CustomerDto updateCliente(CustomerDto request) {

        CustomerDto response = validateRequest(request, false);

        if (response.getMensaje() == null) {
            Optional<Customers> customerOpt = customerRepository.findById(request.getId());
            if (customerOpt.isPresent()) {
                Customers customer = customerOpt.get();
                customer.setFirstName(request.getFirstName());
                customer.setLastName(request.getLastName());
                customer.setCompany(request.getCompany());
                customer = customerRepository.save(customer);

                response.setId(customer.getId());
                response.setMensaje("OK");
                return response;
            } else {
                response = new CustomerDto();
                response.setMensaje("El cliente con id: " + request.getId() + " no se encuentra en BD");
                return response;
            }
        } else {
            return response;
        }
    }

    /**
     * Metodo para realizar las validaciones.
     *
     * @author Victor Bocanegra
     * @param request CustomerDto
     * @param control boolean
     * @return CustomerDto
     */
    public CustomerDto validateRequest(CustomerDto request, boolean control) {
        CustomerDto response = new CustomerDto();

        if (request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            if (request.getFirstName().length() > 10
                    && request.getFirstName().length() < 50) {
                if (request.getLastName() != null && !request.getLastName().isEmpty()) {
                    if (request.getLastName().length() > 10
                            && request.getLastName().length() < 100) {
                        if (request.getCompany() != null && !request.getCompany().isEmpty()) {
                            if (!control) {
                                if (request.getId() == 0) {
                                    response.setMensaje("el id es obligatorio para la actualizacion");
                                }
                            }
                        } else {
                            response.setMensaje("La company es obligatorio");
                        }
                    } else {
                        response.setMensaje("El lastName no cumple con el formato "
                                + "debe ser mayor a 10 caracteres y menor de 100");
                    }
                } else {
                    response.setMensaje("El lastName es obligatorio");
                }
            } else {
                response.setMensaje("El firstName no cumple con el formato "
                        + "debe ser mayor a 10 caracteres y menor de 50");
            }
        } else {
            response.setMensaje("El firstName es obligatorio");
        }
        return response;
    }

    /**
     * Metodo para borrar un cliente en BD.
     *
     * @author Victor Bocanegra
     * @param id
     */
    public void deleteCliente(int id) {
        customerRepository.deleteById(id);
    }
}
