package es.mdef.apitruequet.entidades;

import java.util.List;

import es.mde.acing.utils.Acuartelamiento;
import es.mde.acing.utils.AcuartelamientoImpl;
import es.mde.acing.utils.Departamento;


public class AcuartelamientoConId extends AcuartelamientoImpl implements Acuartelamiento{
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}