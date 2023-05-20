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
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="dptoO_id")
//	@NotNull(message="El departamento es obligatorio")
//	private DepartamentoConId dptoOferta;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="dptoA_id", nullable = true)
//	private DepartamentoConId dptoAdquisicion;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="categoria_id")
//	@NotNull(message="La categoria es obligatoria")
//	private es.mde.acing.utils.Categoria categoria;
	

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