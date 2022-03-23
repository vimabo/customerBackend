/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.brujula.customers.controller;

import com.cmc.brujula.customers.dto.CustomerDto;
import com.cmc.brujula.customers.model.Customers;
import com.cmc.brujula.customers.services.CustomerServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author bocanegravm
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerServices services;

    /**
     * Metodo para buscar todos los clientes en BD.
     *
     * @author Victor Bocanegra
     * @return List<Customers>
     */
    @GetMapping("/listaCustomer")
    @ApiOperation(value = "Obtener Clientes", notes = "Metodo para buscar todos los clientes en BD")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operation OK")
    })
    public ResponseEntity<List<Customers>> list() {
        List<Customers> list = services.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    /**
     * Metodo para buscar un cliente por id en BD.
     *
     * @param id
     * @author Victor Bocanegra
     * @return CustomerDto
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Obtener Cliente", notes = "Metodo para buscar un cliente por id en BD")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operation OK"),
        @ApiResponse(code = 500, message = "BAD_REQUEST")
    })
    public ResponseEntity<?> get(@ApiParam(value = "Customer Id", required = true) @PathVariable int id) {

        CustomerDto response = services.getCustomer(id);
        if (response.getMensaje() != null) {
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(response, HttpStatus.OK);
        }
    }

    /**
     * Metodo para guardar un cliente en BD.
     *
     * @author Victor Bocanegra
     * @param request
     * @return CustomerDto
     */
    @PostMapping("/createCustomer")
    @ApiOperation(value = "Guardar Cliente", notes = "Metodo para guardar un cliente en BD")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operation OK"),
        @ApiResponse(code = 500, message = "BAD_REQUEST")
    })
    ResponseEntity<CustomerDto> create(@RequestBody CustomerDto request) {

        CustomerDto response = services.saveCliente(request);
        if (response.getId() > 0) {
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Metodo para actualizar un cliente en BD.
     *
     * @author Victor Bocanegra
     * @param request
     * @return CustomerDto
     */
    @PostMapping("/updateCustomer")
    @ApiOperation(value = "Actualizar Cliente", notes = "Metodo para actualizar un cliente en BD")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operation OK"),
        @ApiResponse(code = 500, message = "BAD_REQUEST")
    })
    ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto) {
        CustomerDto response = services.updateCliente(customerDto);
        if (response.getMensaje().equalsIgnoreCase("OK")) {
            response.setMensaje("Cliente Actualizado");
            return new ResponseEntity(response, HttpStatus.OK);
        } else {
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Metodo para borrar un cliente en BD.
     *
     * @author Victor Bocanegra
     * @param id
     */
    @DeleteMapping("/deleteCustomer/{id}")
    @ApiOperation(value = "Eliminar Cliente", notes = "Metodo para borrar un cliente en BD")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operation OK")
    })
    ResponseEntity<CustomerDto> delete(@PathVariable("id") int id) {

        services.deleteCliente(id);
        CustomerDto response = new CustomerDto();
        response.setMensaje("Cliente Eliminado");
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
