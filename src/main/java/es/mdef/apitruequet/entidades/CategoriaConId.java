package es.mdef.apitruequet.entidades;


public class CategoriaConId  extends es.mde.acing.utils.CategoriaImpl {
	

	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	    
//
//	@Override
//	@OneToMany(targetEntity=MaterialConId.class)
//	public List<Material> getMateriales() {
//		return (List<Material>) super.getMateriales();
//	}
	
	@Override
	public String toString() {
		return "Categoria [id=" + getId() + ", nombre" + getCategoria() + "]";
	}
	
}