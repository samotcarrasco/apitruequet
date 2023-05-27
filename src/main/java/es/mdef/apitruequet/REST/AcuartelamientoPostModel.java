package es.mdef.apitruequet.REST;



import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.AcuartelamientoImpl;
import es.mde.acing.utils.UnidadImpl.TipoEmpleo;
import es.mdef.apitruequet.entidades.AcuartelamientoConId;


@Relation(itemRelation="departamento")
public class AcuartelamientoPostModel extends RepresentationModel<CategoriaModel>{
	
	private String nombre;
	private String abreviatura;
	private String email;
	private TipoEmpleo responsableEmpleo;
	private String responsableNombre;
	private String telefono;
	private String direccion;
	private String latitud;
	private String longitud;

	public String getNombre() {
		return nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
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
		return "Acuartelamentio POST MODEL nombre" + getNombre() + "]";
	}
	
	
}
