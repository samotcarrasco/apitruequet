package es.mdef.apitruequet.repositorios;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.apitruequet.entidades.CategoriaConId;
import es.mde.acing.utils.CategoriaImpl.TipoGrupo;

public interface CategoriaRepositorio extends JpaRepository<CategoriaConId, Long> {

	List<CategoriaConId> findByGrupo(TipoGrupo tipoGrupo);

	
}