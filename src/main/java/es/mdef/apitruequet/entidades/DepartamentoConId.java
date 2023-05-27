package es.mdef.apitruequet.entidades;

import java.util.List;

import es.mde.acing.utils.Acuartelamiento;
import es.mde.acing.utils.Departamento;
import es.mde.acing.utils.DepartamentoImpl;
import es.mde.acing.utils.Material;

public class DepartamentoConId extends DepartamentoImpl implements Departamento{
	
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void aumentarCredito(int cantidad) {
	    super.setCredito(super.getCredito() + cantidad);
	}
}