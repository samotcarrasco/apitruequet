package es.mdef.apitruequet.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mde.acing.utils.Departamento;
import es.mde.acing.utils.Unidad;
import es.mde.acing.utils.UnidadImpl;
import es.mdef.apitruequet.entidades.AcuartelamientoConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;



@Component
public class DepartamentoAssembler implements RepresentationModelAssembler<DepartamentoConId, DepartamentoModel>{
	
	@Override
	public DepartamentoModel toModel(DepartamentoConId entity) {
		DepartamentoModel model = new DepartamentoModel();
		model.setId(entity.getId());
		model.setAbreviatura(entity.getAbreviatura());
		model.setNombre(entity.getNombre());
		//model.setAcuartelamiento((AcuartelamientoConId) entity.getAcuartelamiento());
		model.setAcuartelamientoN(((AcuartelamientoConId) entity.getAcuartelamiento()).getAbreviatura());
		model.setCredito(entity.getCredito());
		model.setEmail(entity.getEmail());
		model.setTelefono(entity.getTelefono());
		model.setResponsableNombre(entity.getResponsableNombre());
		model.setResponsableEmpleo(entity.getResponsableEmpleo());
		model.setDireccion(entity.getDireccion());
		model.setLongitud(entity.getLongitud());
		model.setLatitud(entity.getLatitud());
		int numMateriales = entity.getMaterialesOfertados() != null ||  entity.getMaterialesAdquiridos() != null 
				? entity.getMaterialesOfertados().size() + entity.getMaterialesAdquiridos().size()
			    : 0;
		model.setNumMateriales(numMateriales);
				
		model.add(linkTo(methodOn(DepartamentoController.class).one(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(AcuartelamientoController.class).one(((AcuartelamientoConId) entity.getAcuartelamiento()).getId())).withRel("acuartelamiento"));
		model.add(linkTo(methodOn(DepartamentoController.class).materialesOfertados(entity.getId())).withRel("materialesOfertados"));
		model.add(linkTo(methodOn(DepartamentoController.class).materialesAdquiridos(entity.getId())).withRel("materialesAdquiridos"));
		return model;
	}
	
	
	
	
	public DepartamentoConId toEntity(DepartamentoPostModel model) {
		DepartamentoConId departamento = new DepartamentoConId();
		departamento.setAbreviatura(model.getAbreviatura());
		departamento.setAcuartelamiento(model.getAcuartelamiento());
		departamento.setNombre(model.getNombre());
		//inicialemnte se asignan 1000 milis a los departamentos
		departamento.setCredito(1000);
		//departamento.setCredito(model.getCredito());
		departamento.setEmail(model.getEmail());
		departamento.setTelefono(model.getTelefono());
		departamento.setResponsableNombre(model.getResponsableNombre());
		departamento.setResponsableEmpleo(model.getResponsableEmpleo());
		departamento.setDireccion(model.getDireccion());
		departamento.setLongitud(model.getLongitud());
		departamento.setLatitud(model.getLatitud());
		
		//las entidades ocn las que esta relacionada
		return departamento;
	}
}
