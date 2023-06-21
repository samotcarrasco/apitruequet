package es.mdef.apitruequet.entidades;

import java.util.List;

import es.mde.acing.utils.Categoria;

public class CategoriaConId  extends es.mde.acing.utils.CategoriaImpl {
	
	private Long id;
	private int numMateriales;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void incremaentarMateriales() {
		this.setNumMateriales(this.getNumMateriales() + 1);
	}

	public void decrementarMateriales() {
		this.setNumMateriales(this.getNumMateriales() - 1);
	}
	

	public int getNumMateriales() {
		return numMateriales;
	}

	public void setNumMateriales(int numMateriales) {
		this.numMateriales = numMateriales;
	}	

	@Override
	public List<Categoria> getCategoriasHijas(){
		
		return super.getCategoriasHijas();
	}
	@Override
	public String toString() {
		return "Categoria [id=" + getId() + ", nombre" + getCategoria() + "]";
	}

	
}