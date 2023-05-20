package es.mdef.apitruequet.entidades;

import java.util.List;

import es.mde.acing.utils.Material;

public class DepartamentoConId extends es.mde.acing.utils.DepartamentoImpl {
	
	private Long id;
	
//	private List<Material> materialesAdquiridos;			
//    private List<Material> materialesOfertados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public void aumentarCredito(int cantidad) {
	    super.setCredito(super.getCredito() + cantidad);
	}
	
	
		@Override
	public String toString() {
		return "Departamento [id=" + getId() + "]";
	}
	
}