package es.mdef.apitruequet.repositorios;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.mdef.apitruequet.entidades.MaterialConId;
import jakarta.transaction.Transactional;

public interface MaterialRepositorio extends JpaRepository<MaterialConId, Long> {
	
	
    //método personalizado. Por cada 3 materiales ofertados no inventariables, se regalarán 50 milis	
	@Query("SELECT COUNT(*) FROM MaterialConId m WHERE m.dptoOferta = :param")
	    int calcularBonificacion(@Param("param") String dptoOferta);
	


	@Modifying
	@Transactional
	@Query(value = "UPDATE public.materiales SET "
					+ "tipo_material = 'I',"
					+ "numero_serie = :numero_serie, " 
					+ "noc = :noc, "
					+ "bonificacion = null "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarInventariable(
	    @Param("numero_serie") String numSerie,
	    @Param("noc") String noc,
	    @Param("id") Long id
	);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.materiales SET "
					+ "tipo_material = 'N',"
					+ "numero_serie = null, " 
					+ "noc = null, "
					+ "bonificacion = :bonificacion "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarNoInventariable(
	    @Param("bonificacion") int bonificacion,
	    @Param("id") Long id
	);
	

	
}	
