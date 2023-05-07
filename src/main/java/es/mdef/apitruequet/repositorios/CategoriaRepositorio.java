package es.mdef.apitruequet.repositorios;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.apitruequet.entidades.Categoria;
import es.mdef.apitruequet.entidades.Categoria.TipoGrupo;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

	List<Categoria> findByGrupo(TipoGrupo tipoGrupo);

	
}