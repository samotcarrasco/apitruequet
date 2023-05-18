package es.mdef.apitruequet.entidades;


public class Categoria extends es.mde.acing.utils.CategoriaL{
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	
	@Override
	public String toString() {
		return "Categoria [id=" + getId() + ", nombre" + getCategoria() + "]";
	}
	
}