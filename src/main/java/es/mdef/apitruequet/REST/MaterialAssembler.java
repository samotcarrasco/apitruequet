package es.mdef.apitruequet.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.apitruequet.entidades.CategoriaConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;
import es.mdef.apitruequet.entidades.Inventariable;
import es.mdef.apitruequet.entidades.MaterialConId;
import es.mdef.apitruequet.entidades.NoInventariable;
import es.mde.acing.utils.Material;
import es.mde.acing.utils.MaterialImpl.TipoMaterial;
import es.mde.acing.utils.UnidadImpl;



@Component
public class MaterialAssembler implements RepresentationModelAssembler<MaterialConId, MaterialModel>{

	
	@Override
	public MaterialModel toModel(MaterialConId entity) {
		MaterialModel model = new MaterialModel();
		
		model.setId( entity.getId());
		model.setEstado(entity.getEstado());
		model.setNombre(entity.getNombre());
		model.setDescripcion(entity.getDescripcion());
		model.setCantidad(entity.getCantidad());
		model.setMilis(entity.getMilis());
		model.setDimensiones(entity.getDimensiones());
		model.setPeso(entity.getPeso());
		model.setImagen(entity.getImagen());
				
		//por compodidad para el front, estos tres los devolvemos como String
		model.setCategoriaN(entity.getCategoria().getCategoria());
		model.setDptoOfertaN(((DepartamentoConId) entity.getDeptoOferta()).getAbreviatura());
		model.setGrupoN(entity.getCategoria().getGrupo().toString());
		
		model.setFechaEntregaFisica(entity.getFechaEngregaFisica());
		
		String nombreUnidadOferta = entity.getDptoAdquisicion() != null ? ((DepartamentoConId) entity.getDptoAdquisicion()).getAbreviatura() : "-";
		model.setDptoAdquisicionN(nombreUnidadOferta);
		
				
		if (entity.getTipoMaterial() == TipoMaterial.Inventariable) {
			model.setNoc(((Inventariable) entity).getNoc());
			model.setNumeroSerie(((Inventariable) entity).getNumeroSerie());
			model.setTipoMaterial(TipoMaterial.Inventariable);
			model.setBonificacion(0);
		} else if (entity.getTipoMaterial() == TipoMaterial.noInventariable) {
			model.setBonificacion(((NoInventariable) entity).getBonificacion());
			model.setTipoMaterial(TipoMaterial.noInventariable);
			model.setNoc(null);
			model.setNumeroSerie(null);
		}
	
		model.add(linkTo(methodOn(CategoriaController.class).one(((CategoriaConId) entity.getCategoria()).getId())).withRel("categoria"));
    	model.add(linkTo(methodOn(DepartamentoController.class).one(((DepartamentoConId) entity.getDeptoOferta()).getId())).withRel("dptoOferta"));
		if (entity.getDptoAdquisicion() != null) {
			model.add(linkTo(methodOn(DepartamentoController.class).one(((DepartamentoConId) entity.getDptoAdquisicion()).getId())).withRel("dptoAdquisicion"));
		}
		
		return model;
	}
	
	
	
	
	public MaterialConId toEntity(MaterialPostModel model) {
		MaterialConId material = new MaterialConId();
		
		
		switch (model.getTipoMaterial()) {
		case Inventariable:
			Inventariable inv = new Inventariable();
			inv.setNoc(model.getNoc());
			inv.setNumeroSerie(model.getNumeroSerie());
			material = inv;
			break;
		case noInventariable: 
			NoInventariable noinv = new NoInventariable();
			noinv.setBonificacion(model.getBonificacion());
			material = noinv;
			break;				
		
		}
		
		material.setNombre(model.getNombre());
		material.setDescripcion(model.getDescripcion());
		material.setCantidad(model.getCantidad());
		material.setDimensiones(model.getDimensiones());
		material.setFechaAdquisicion(model.getFechaAdquisicion());
		material.setFechaOferta(model.getFechaOferta());
		material.setCantidad(model.getCantidad());
		material.setMilis(model.getMilis());
		material.setEstado(model.getEstado());
		material.setImagen(model.getImagen());
		material.setImgReducida(model.getImgReducida());
		material.setPeso(model.getPeso());
		
		//las entidades con las que esta relacionada
		material.setDeptoOferta(model.getDptoOferta());
		material.setCategoria(model.getCategoria());
		material.setDptoAdquisicion(model.getDptoAdquisicion());
		
		return material;
	}
}
