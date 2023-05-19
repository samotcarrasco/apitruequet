package es.mdef.apitruequet.REST;



import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.DepartamentoL.TipoAcuartelamiento;
import es.mde.acing.utils.DepartamentoL.TipoEmpleo;


@Relation(itemRelation="departamento")
public class DepartamentoPostModel extends RepresentationModel<CategoriaModel>{
	
	private Long id;
	
	private String nombre;
	private String abreviatura;
	private TipoAcuartelamiento acuartelamiento;
	private String email;
	private TipoEmpleo responsableEmpleo;
	private String responsableNombre;
	private String telefono;
	private String direccion;
	private String latitud;
	private String longitud;
	
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public TipoAcuartelamiento getAcuartelamiento() {
		return acuartelamiento;
	}


	public TipoEmpleo getResponsableEmpleo() {
		return responsableEmpleo;
	}

	public String getResponsableNombre() {
		return responsableNombre;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public String getEmail() {
		return email;
	}

	
	
	public String getDireccion() {
		return direccion;
	}

	public String getLatitud() {
		return latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	@Override
	public String toString() {
		return "DPTO MODELO [id=" + getId() + ", nombre" + getNombre() + "]";
	}
	
	
}
