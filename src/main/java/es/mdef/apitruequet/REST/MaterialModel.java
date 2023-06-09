package es.mdef.apitruequet.REST;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mde.acing.utils.MaterialImpl.EstadoMaterial;
import es.mde.acing.utils.MaterialImpl.TipoMaterial;


@Relation(itemRelation ="material")
public class MaterialModel extends RepresentationModel<MaterialModel>{
	
	private Long id;
	private String nombre;
	private String dptoOfertaN;
	private String dptoAdquisicionN;
	private String categoriaN;
	private String grupoN;
	private String descripcion;
	private LocalDate fechaAdquisicion;
	private LocalDate fechaOferta;
	private String imagen;
	private String imgReducida;
	private EstadoMaterial estado;
	private int milis;
	private int cantidad;
	private String dimensiones;
	private String peso;
	private TipoMaterial tipoMaterial;
	private String noc;
	private String numeroSerie;
	private int bonificacion;
	private int numMateriales;
	private LocalDate fechaEntregaFisica;
 

	public String getImgReducida() {
		return imgReducida;
	}
	public void setImgReducida(String imgReducida) {
		this.imgReducida = imgReducida;
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

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public String getDptoOfertaN() {
		return dptoOfertaN;
	}
	public void setDptoOfertaN(String dptoOfertaN) {
		this.dptoOfertaN = dptoOfertaN;
	}
	public String getDptoAdquisicionN() {
		return dptoAdquisicionN;
	}
	public void setDptoAdquisicionN(String dptoAdquisicionN) {
		this.dptoAdquisicionN = dptoAdquisicionN;
	}
	public String getCategoriaN() {
		return categoriaN;
	}
	public void setCategoriaN(String categoriaN) {
		this.categoriaN = categoriaN;
	}
	
	public LocalDate getFechaAdquisicion() {
		return fechaAdquisicion;
	}
	public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}
	public LocalDate getFechaOferta() {
		return fechaOferta;
	}
	public void setFechaOferta(LocalDate fechaOferta) {
		this.fechaOferta = fechaOferta;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDimensiones() {
		return dimensiones;
	}
	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
		
	public String getNoc() {
		return noc;
	}
	public void setNoc(String noc) {
		this.noc = noc;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public int getBonificacion() {
		return bonificacion;
	}
	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}
	
	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}
	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}
	@Override
	public String toString() {
		return "MATERIAL [Nombre=" + getNombre() +"]";
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
