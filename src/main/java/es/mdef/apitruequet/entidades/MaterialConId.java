package es.mdef.apitruequet.entidades;



public class MaterialConId extends es.mde.acing.utils.MaterialImpl {
	private Long id;
	private String imgReducida;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getImgReducida() {
		return imgReducida;
	}
	public void setImgReducida(String imgReducida) {
		this.imgReducida = imgReducida;
	}
	@Override
	public String toString() {
		return "MATERIAL [Nombre=" + getNombre() +"]";
	}
	
		
}