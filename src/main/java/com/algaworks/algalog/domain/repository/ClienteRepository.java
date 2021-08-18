package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * É um componente do Spring onde é gerenciado pelo proprio Spring Interface repositorio que defini as entidades que no
 * caso cliente
 */
@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    List<ClienteModel> findByNomeContaining(String nome);

    Optional<ClienteModel> findByEmail(String email);
}
