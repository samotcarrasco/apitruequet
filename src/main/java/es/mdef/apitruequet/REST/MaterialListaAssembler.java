package es.mdef.apitruequet.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mde.acing.utils.Material;
import es.mdef.apitruequet.entidades.CategoriaConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;
import es.mdef.apitruequet.entidades.MaterialConId;


@Component
public class MaterialListaAssembler<T extends Material>  implements RepresentationModelAssembler<T, MaterialListaModel>{

	@Override
	public MaterialListaModel toModel(T entity) {
		MaterialListaModel model = new MaterialListaModel();
		
		model.setId(((MaterialConId) entity).getId());
		model.setEstado(entity.getEstado());
		model.setNombre(entity.getNombre());
		model.setDescripcion(entity.getDescripcion());
		model.setCantidad(entity.getCantidad());
		model.setMilis(entity.getMilis());
		model.setImgReducida(((MaterialConId) entity).getImgReducida());
		
		//por compodidad para el front, estos tres los devolvemoscomo String
		model.setCategoriaN(entity.getCategoria().getCategoria());
		model.setDptoOfertaN(entity.getDeptoOferta().getAbreviatura());
		model.setGrupoN(entity.getCategoria().getGrupo().toString());
		model.setFechaEntregaFisica(((MaterialConId) entity).getFechaEngregaFisica());

		
		String nombreUnidadOferta = entity.getDptoAdquisicion() != null ? entity.getDptoAdquisicion().getAbreviatura(): "-";
		model.setDptoAdquisicionN(nombreUnidadOferta);
			
		//model.add(linkTo(methodOn(CategoriaController.class).one(((CategoriaConId) entity.getCategoria()).getId())).withRel("categoria"));
		model.add(linkTo(methodOn(CategoriaController.class).one(((CategoriaConId) entity.getCategoria()).getId())).withRel("categoria"));		
		model.add(linkTo(methodOn(DepartamentoController.class).one(((DepartamentoConId) entity.getDeptoOferta()).getId())).withRel("dptoOferta"));
		if (entity.getDptoAdquisicion() != null) {
			model.add(linkTo(methodOn(DepartamentoController.class).one(((DepartamentoConId) entity.getDptoAdquisicion()).getId())).withRel("dptoAdquisicion"));
		}	
		return model;	
	}
	
	public CollectionModel<MaterialListaModel> toCollection(List<T> list) {
		CollectionModel<MaterialListaModel> collection = CollectionModel.of(
				list.stream().map(this::toModel).collect(Collectors.toList())
				);
//		collection.add(
//				linkTo(methodOn(MaterialControler.class).all()).withRel("materiales")
//				);		
		return collection;
	}

}






 
