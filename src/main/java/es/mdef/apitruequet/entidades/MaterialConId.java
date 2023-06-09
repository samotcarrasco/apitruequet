package es.mdef.apitruequet.entidades;

import java.time.LocalDate;

public class MaterialConId extends es.mde.acing.utils.MaterialImpl {
	private Long id;
	private String imgReducida;
	private LocalDate fechaEntregaFisica;

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

	public LocalDate getFechaEngregaFisica() {
		return fechaEntregaFisica;
	}
	public void setFechaEngregaFisica(LocalDate fechaEngregaFisica) {
		this.fechaEntregaFisica = fechaEngregaFisica;
	}

//	public List<MaterialConId> algunos() {
//		//MaterialRepositorio repo = new MaterialRepositorio();
//	    List<MaterialConId> materiales = MaterialRepositorio.algunos();
//	    Collections.shuffle(materiales); 
//		return materiales;
//	}
	
	
	
	@Override
	public String toString() {
		return "MATERIAL [Nombre=" + getNombre() +"]";
	}
		
}