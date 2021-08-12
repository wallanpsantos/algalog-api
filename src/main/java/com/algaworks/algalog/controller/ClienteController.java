package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.ClienteModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class ClienteController {

    @PersistenceContext //injeta um EntityManager na var de instancia
    private EntityManager entitymanager;

    @GetMapping("/clientes")
    public List<ClienteModel> getClientes() {
        // Primeiro param "from ClienteModel" tem que se o nome da Classe
        return entitymanager.createQuery("from ClienteModel", ClienteModel.class).getResultList();
    }

//    @GetMapping("/clientes")
//    public ResponseEntity<List<ClienteModel>> getClientes() {
//
//        return new ResponseEntity<>(entitymanager.createQuery("from ClienteModel", ClienteModel.class)
//                .getResultList(), HttpStatus.OK);
//    }

}
