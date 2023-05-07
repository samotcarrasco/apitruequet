package es.mdef.apitruequet.repositorios;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.apitruequet.entidades.Categoria;
import es.mdef.apitruequet.entidades.Departamento;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByAbreviatura(String abreviatura);

}