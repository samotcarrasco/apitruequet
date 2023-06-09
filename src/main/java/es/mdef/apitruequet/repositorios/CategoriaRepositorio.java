package es.mdef.apitruequet.repositorios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.mdef.apitruequet.entidades.CategoriaConId;

public interface CategoriaRepositorio extends JpaRepository<CategoriaConId, Long> {
	
	Optional<CategoriaConId> findByCategoria(String nombre);

	@Query(value="SELECT DISTINCT categoria FROM categorias WHERE categoria_padre_id is null", nativeQuery = true)
	String[] grupos();
}