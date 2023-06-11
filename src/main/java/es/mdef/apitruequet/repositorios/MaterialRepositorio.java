package es.mdef.apitruequet.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.mdef.apitruequet.entidades.MaterialConId;
import jakarta.transaction.Transactional;

public interface MaterialRepositorio extends JpaRepository<MaterialConId, Long> {
	
	
    //método personalizado. al ofertar NoInventariables, se bonificará con un 10% del total de milis no inventariables ofertaos
	//utilizamos calesce para que si no hay valores, devuelva 0
	@Query(value="SELECT COALESCE(round(sum(milis)*0.1), 0) FROM public.materiales "
						+ "WHERE dptoo_Id = :param AND tipo_material = 'N'", nativeQuery = true) 
	int calcularBonificacion(@Param("param") long dptoOferta);
	 

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
