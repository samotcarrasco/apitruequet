package es.mdef.apitruequet.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.apitruequet.entidades.DepartamentoConId;


@Component
public class DepartamentoListaAssembler  implements RepresentationModelAssembler<DepartamentoConId, DepartamentoListaModel>{

	
	@Override
	public DepartamentoListaModel toModel(DepartamentoConId entity) {
		DepartamentoListaModel model = new DepartamentoListaModel();
		model.setId(entity.getId());
		model.setAbreviatura(entity.getAbreviatura());
		model.setNombre(entity.getNombre());
		model.setAcuartelamiento(entity.getAcuartelamiento());
		model.setCredito(entity.getCredito());
		model.setEmail(entity.getEmail());
		model.setTelefono(entity.getTelefono());
		model.setResponsableNombre(entity.getResponsableNombre());
		model.setResponsableEmpleo(entity.getResponsableEmpleo());
		model.setDireccion(entity.getDireccion());
		model.setLongitud(entity.getLongitud());
		model.setLatitud(entity.getLatitud());
		
				
		model.add(linkTo(methodOn(DepartamentoController.class).one(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(DepartamentoController.class).materialesOfertados(entity.getId())).withRel("materialesOfertados"));
     	model.add(linkTo(methodOn(DepartamentoController.class).materialesAdquiridos(entity.getId())).withRel("materialesAdquiridos"));

		return model;
	}
	
	
	
	public CollectionModel<DepartamentoListaModel> toCollection(List<DepartamentoConId> lista) {
		CollectionModel<DepartamentoListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);		
		return collection;
	}
}
