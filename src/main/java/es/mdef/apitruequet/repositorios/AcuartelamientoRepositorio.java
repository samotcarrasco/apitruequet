package es.mdef.apitruequet.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.mdef.apitruequet.entidades.AcuartelamientoConId;

public interface AcuartelamientoRepositorio extends JpaRepository<AcuartelamientoConId, Long> {
    Optional<AcuartelamientoConId> findByAbreviatura(String abreviatura);
    
	 @Query(value="SELECT DISTINCT abreviatura FROM acuartelamientos", nativeQuery = true)
	 String[] bases();
	 
}