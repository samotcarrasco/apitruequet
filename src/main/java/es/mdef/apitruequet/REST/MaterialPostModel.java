package es.mdef.apitruequet.REST;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import es.mdef.apitruequet.entidades.Categoria;
import es.mdef.apitruequet.entidades.Departamento;
import es.mde.acing.utils.MaterialL.EstadoMaterial;
import es.mde.acing.utils.MaterialL.TipoMaterial;


@Relation(itemRelation = "material")
public class MaterialPostModel extends RepresentationModel<MaterialPostModel>{
	
	
	private Long id;
	private String nombre;

	private Departamento dptoOferta;
	//dejamos el dpto adquisicion por compatibilidad con put
	private Departamento dptoAdquisicion;
	private Categoria categoria;
	
	private String descripcion;
	private LocalDate fechaAdquisicion;
	private LocalDate fechaOferta;
	private String imagen;
	//dejamos el estado por compatibilidad con put
	private EstadoMaterial estado;
	private int milis;
	private int cantidad;
	private String dimensiones;
	private String peso;

	private TipoMaterial tipoMaterial;
	private String noc;
	private String numeroSerie;
	private int bonificacion;
	private String imgReducida;
     	 
    
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Departamento getDptoOferta() {
		return dptoOferta;
	}

	public Departamento getDptoAdquisicion() {
		return dptoAdquisicion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public LocalDate getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public LocalDate getFechaOferta() {
		return fechaOferta;
	}

	public String getImagen() {
		return imagen;
	}

	public EstadoMaterial getEstado() {
		return estado;
	}

	public int getMilis() {
		return milis;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public String getPeso() {
		return peso;
	}

	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}
	
	public String getNoc() {
		return noc;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public int getBonificacion() {
		return bonificacion;
	}

	
	
	public String getImgReducida() {
		return imgReducida;
	}

	@Override
	public String toString() {
		return "MATERIAL [Nombre=" + getNombre() +"]";
	}
	
	
}
