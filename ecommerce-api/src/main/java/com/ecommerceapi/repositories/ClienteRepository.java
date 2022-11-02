package com.ecommerceapi.repositories;

import com.ecommerceapi.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//    //Trazer pedidos do cliente
//    @Query("select c from Cliente c left join  c.pedidos p where c.id =:id")
//    Cliente buscarClienteTrazendoPedidos(@Param("id") Long id);
}
