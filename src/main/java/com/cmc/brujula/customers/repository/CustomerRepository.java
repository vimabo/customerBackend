/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.brujula.customers.repository;

import com.cmc.brujula.customers.model.Customers;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bocanegravm
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {

    
    
    
    Optional<Customers> findById(int id);

    /*@Query("SELECT c FROM Cliente c WHERE c.tipoIdentificacion = :tipoIdentificacion "
            + "AND c.numeroIdentificacion = :numeroIdentificacion")
    public Customers searchByTipoIdAndId(@Param("tipoIdentificacion") TipoIdentificacion tipoIdentificacion,
            @Param("numeroIdentificacion") long numeroIdentificacion);*/

}
