package es.mdef.apitruequet.repositorios;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.apitruequet.entidades.AcuartelamientoConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;

public interface AcuartelamientoRepositorio extends JpaRepository<AcuartelamientoConId, Long> {
    Optional<AcuartelamientoConId> findByAbreviatura(String abreviatura);
}