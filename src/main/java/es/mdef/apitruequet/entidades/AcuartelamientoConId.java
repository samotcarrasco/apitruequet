package es.mdef.apitruequet.entidades;


import es.mde.acing.utils.Acuartelamiento;
import es.mde.acing.utils.AcuartelamientoImpl;


public class AcuartelamientoConId extends AcuartelamientoImpl implements Acuartelamiento{
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}