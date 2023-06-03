package es.mdef.apitruequet.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.mdef.apitruequet.entidades.CategoriaConId;
import es.mde.acing.utils.CategoriaImpl.TipoGrupo;

public interface CategoriaRepositorio extends JpaRepository<CategoriaConId, Long> {

	//List<CategoriaConId> findByGrupo(String tipoGrupo);
	
	 @Query(value="SELECT DISTINCT categoria FROM categorias WHERE categoria_padre_id is null", nativeQuery = true)
	 String[] grupos();

	
}