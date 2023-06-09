package es.mdef.apitruequet.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.UnidadImpl.TipoEmpleo;

@Relation(collectionRelation = "departamentos")
public class DepartamentoListaModel extends RepresentationModel<CategoriaModel> {

	private Long id;
	private String nombre;
	private String abreviatura;
	private String acuartelamientoN;
	private String email;
	private int credito;
	private TipoEmpleo responsableEmpleo;
	private String responsableNombre;
	private String telefono;
	private String direccion;
	private String latitud;
	private String longitud;
	private int numMateriales;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public int getCredito() {
		return credito;
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

	public int getNumMateriales() {
		return numMateriales;
	}

	public void setNumMateriales(int numMateriales) {
		this.numMateriales = numMateriales;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public void setResponsableEmpleo(TipoEmpleo responsableEmpleo) {
		this.responsableEmpleo = responsableEmpleo;
	}

	public void setResponsableNombre(String responsableNombre) {
		this.responsableNombre = responsableNombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "DPTO MODELO [id=" + getId() + ", nombre" + getAbreviatura() + "]";
	}

	public String getAcuartelamientoN() {
		return acuartelamientoN;
	}

	public void setAcuartelamientoN(String acuartelamientoN) {
		this.acuartelamientoN = acuartelamientoN;
	}

}
