package es.mdef.apitruequet.entidades;

public class Departamento extends es.mde.acing.utils.DepartamentoL{
	
	private Long id;
	
			

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

		@Override
	public String toString() {
		return "Departamento [id=" + getId() + "]";
	}
	
}