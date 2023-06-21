package es.mdef.apitruequet.entidades;


import es.mde.acing.utils.Departamento;
import es.mde.acing.utils.DepartamentoImpl;

public class DepartamentoConId extends DepartamentoImpl implements Departamento{
	
	private Long id;
	private int numMateriales;
	
	public void incremaentarMateriales() {
		this.setNumMateriales(this.getNumMateriales() + 1);
	}

	public void decrementarMateriales() {
		this.setNumMateriales(this.getNumMateriales() - 1);
	}
	

	public int getNumMateriales() {
		return numMateriales;
	}

	public void setNumMateriales(int numMateriales) {
		this.numMateriales = numMateriales;
	}

	
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
		return "DepartamentoConId [id=" + id + super.toString();
	}
}