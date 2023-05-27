package es.mdef.apitruequet.repositorios;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.apitruequet.entidades.DepartamentoConId;

public interface DepartamentoRepositorio extends JpaRepository<DepartamentoConId, Long> {
    Optional<DepartamentoConId> findByAbreviatura(String abreviatura);
}