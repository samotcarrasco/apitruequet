package es.mdef.apitruequet.REST;


import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.CategoriaImpl.TipoGrupo;
import es.mdef.apitruequet.entidades.CategoriaConId;


@Relation(itemRelation="categoria")
public class CategoriaModel extends RepresentationModel<CategoriaModel>{
	
	private Long id;
	private String categoria;
	private String descripcion;
	private String grupo;
	private int minMilis;
	private int maxMilis;
	private int numMateriales;


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getMinMilis() {
		return minMilis;
	}

	public void setMinMilis(int minMilis) {
		this.minMilis = minMilis;
	}

	public int getMaxMilis() {
		return maxMilis;
	}

	public void setMaxMilis(int maxMilis) {
		this.maxMilis = maxMilis;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getNumMateriales() {
		return numMateriales;
	}
	
	public void setNumMateriales(int numMateriales) {
		this.numMateriales = numMateriales;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "Categoria MODELO [id=" + getId() + ", nombre" + getCategoria() + "]";
	}

	
}
