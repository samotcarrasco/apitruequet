package es.mdef.apitruequet.repositorios;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.mdef.apitruequet.entidades.AcuartelamientoConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;

public interface AcuartelamientoRepositorio extends JpaRepository<AcuartelamientoConId, Long> {
    Optional<AcuartelamientoConId> findByAbreviatura(String abreviatura);
    
	 @Query(value="SELECT DISTINCT abreviatura FROM acuartelamientos", nativeQuery = true)
	 String[] bases();
	 
}