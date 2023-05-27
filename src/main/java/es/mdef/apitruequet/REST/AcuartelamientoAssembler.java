package es.mdef.apitruequet.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mde.acing.utils.Acuartelamiento;
import es.mde.acing.utils.Departamento;
import es.mde.acing.utils.Unidad;
import es.mde.acing.utils.UnidadImpl;
import es.mdef.apitruequet.entidades.AcuartelamientoConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;



@Component
public class AcuartelamientoAssembler implements RepresentationModelAssembler<AcuartelamientoConId, AcuartelamientoModel>{

	
	@Override
	public AcuartelamientoModel toModel(AcuartelamientoConId entity) {
		AcuartelamientoModel model = new AcuartelamientoModel();
		model.setId( entity.getId());
		model.setAbreviatura( entity.getAbreviatura());
		model.setNombre( entity.getNombre());
		model.setEmail(entity.getEmail());
		model.setTelefono(entity.getTelefono());
		model.setResponsableNombre(entity.getResponsableNombre());
		model.setResponsableEmpleo(entity.getResponsableEmpleo());
		model.setDireccion(entity.getDireccion());
		model.setLongitud(entity.getLongitud());
		model.setLatitud(entity.getLatitud());
		int numMateriales = 0;
		
		if (entity.getDepartamentos() != null) {
			for (Departamento departamento : entity.getDepartamentos()) {
		    numMateriales += departamento.getMaterialesOfertados().size();
			}
		}
		
		int numDptos = entity.getDepartamentos() != null  ? entity.getDepartamentos().size() : 0;

		model.setNumDepartamentos(numDptos);
		model.setNumMateriales(numMateriales);
		model.add(linkTo(methodOn(AcuartelamientoController.class).one(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(AcuartelamientoController.class).departamentos(entity.getId())).withRel("departamentos"));
				
		return model;
	}
	
	
	
	
	public AcuartelamientoConId toEntity(AcuartelamientoPostModel model) {
		AcuartelamientoConId acuartelamiento = new AcuartelamientoConId();
		acuartelamiento.setAbreviatura(model.getAbreviatura());
		acuartelamiento.setNombre(model.getNombre());
		acuartelamiento.setEmail(model.getEmail());
		acuartelamiento.setTelefono(model.getTelefono());
		acuartelamiento.setResponsableNombre(model.getResponsableNombre());
		acuartelamiento.setResponsableEmpleo(model.getResponsableEmpleo());
		acuartelamiento.setDireccion(model.getDireccion());
		acuartelamiento.setLongitud(model.getLongitud());
		acuartelamiento.setLatitud(model.getLatitud());
		
		//las entidades ocn las que esta relacionada
		return acuartelamiento;
	}
	
	

	public CollectionModel<AcuartelamientoModel> toCollection(List<AcuartelamientoConId> lista) {
		CollectionModel<AcuartelamientoModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);		
		return collection;
	}
}
