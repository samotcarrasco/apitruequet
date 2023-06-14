package es.mdef.apitruequet.entidades;

import java.util.List;

import es.mde.acing.utils.Categoria;

public class CategoriaConId  extends es.mde.acing.utils.CategoriaImpl {
	
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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