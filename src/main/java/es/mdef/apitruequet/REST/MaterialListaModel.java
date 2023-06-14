package es.mdef.apitruequet.REST;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.MaterialImpl.EstadoMaterial;

@Relation(collectionRelation = "materiales")
public class MaterialListaModel extends RepresentationModel<MaterialListaModel> {

	private Long id;
	private String nombre;
	private String dptoOfertaN;
	private String dptoAdquisicionN;
	private String categoriaN;
	private String grupoN;
	private String descripcion;
	private EstadoMaterial estado;
	private int milis;
	private int cantidad;
	private String imgReducida;
	private int numMateriales;
	private LocalDate fechaEntregaFisica;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDptoOfertaN() {
		return dptoOfertaN;
	}

	public void setDptoOfertaN(String dptoOferta) {
		this.dptoOfertaN = dptoOferta;
	}

	public String getDptoAdquisicionN() {
		return dptoAdquisicionN;
	}

	public void setDptoAdquisicionN(String dptoAdquisicion) {
		this.dptoAdquisicionN = dptoAdquisicion;
	}

	public String getCategoriaN() {
		return categoriaN;
	}

	public void setCategoriaN(String categoria) {
		this.categoriaN = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoMaterial getEstado() {
		return estado;
	}

	public void setEstado(EstadoMaterial estado) {
		this.estado = estado;
	}

	public int getMilis() {
		return milis;
	}

	public void setMilis(int milis) {
		this.milis = milis;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getImgReducida() {
		return imgReducida;
	}

	public void setImgReducida(String imgReducida) {
		this.imgReducida = imgReducida;
	}

	@Override
	public String toString() {
		return "MATERIAL [Nombre=" + getNombre() + "]";
	}

	public String getGrupoN() {
		return grupoN;
	}

	public void setGrupoN(String grupoN) {
		this.grupoN = grupoN;
	}

	public LocalDate getFechaEntregaFisica() {
		return fechaEntregaFisica;
	}

	public void setFechaEntregaFisica(LocalDate fechaEntregaFisica) {
		this.fechaEntregaFisica = fechaEntregaFisica;
	}
}
