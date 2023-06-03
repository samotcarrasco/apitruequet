package es.mdef.apitruequet.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.CategoriaImpl.TipoGrupo;
import es.mdef.apitruequet.entidades.CategoriaConId;


@Relation(itemRelation="categoria")
public class CategoriaPostModel extends RepresentationModel<CategoriaPostModel>{


  private String categoria;
	private String descripcion;
	private int minMilis;
	private int maxMilis;
	private CategoriaConId categoriaPadre;

	public String getCategoria() {
		return categoria;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public int getMinMilis() {
		return minMilis;
	}

	public int getMaxMilis() {
		return maxMilis;
	}
	
	public CategoriaConId getCategoriaPadre() {
		return categoriaPadre;
	}
	
	
	@Override
	public String toString() {
		return "Categoria MODELO POST [" + getCategoria() + "]";
	}
	
}
