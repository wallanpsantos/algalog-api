package com.algaworks.algalog.domain.repository;

import com.algaworks.algalog.domain.model.EntregaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<EntregaModel, Long> {
}
