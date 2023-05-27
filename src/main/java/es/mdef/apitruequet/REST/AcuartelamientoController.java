package es.mdef.apitruequet.REST;


import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.mdef.apitruequet.ApiTruequetApp;
import es.mdef.apitruequet.entidades.AcuartelamientoConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;
import es.mdef.apitruequet.repositorios.AcuartelamientoRepositorio;
import es.mdef.apitruequet.repositorios.DepartamentoRepositorio;
import es.mdef.apitruequet.validation.RegisterNotFoundException;
import jakarta.validation.Valid;
import es.mde.acing.utils.UnidadImpl.TipoEmpleo;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/acuartelamientos")
public class AcuartelamientoController {
	private final AcuartelamientoRepositorio repositorio;
	private final DepartamentoRepositorio depRepositorio;
	private final AcuartelamientoAssembler assembler;
	private final AcuartelamientoListaAssembler listaAssembler;
    private final DepartamentoListaAssembler depListaAssembler;

	private final Logger log;
		
	AcuartelamientoController(AcuartelamientoRepositorio repositorio, AcuartelamientoAssembler assembler, 
			DepartamentoRepositorio depRepositorio, DepartamentoListaAssembler depListaAssembler, AcuartelamientoListaAssembler listaAssembler) {
			this.repositorio = repositorio;
			this.depRepositorio = depRepositorio;
			this.assembler = assembler;
			this.depListaAssembler = depListaAssembler;
			this.listaAssembler = listaAssembler; 
			log = ApiTruequetApp.log;
		}
		
	
		@GetMapping("{id}")
			public AcuartelamientoModel one(@PathVariable Long id) {
			AcuartelamientoConId acuartelamiento = (AcuartelamientoConId) repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Acuartelamiento"));
			log.info("Recuperada " + acuartelamiento);
			return assembler.toModel(acuartelamiento);
		}
		
		@GetMapping("{id}/departamentos")
		public CollectionModel<DepartamentoModel> departamentos(@PathVariable Long id) {
			AcuartelamientoConId acuartelamiento = (AcuartelamientoConId) repositorio.findById(id)
					.orElseThrow(() -> new RegisterNotFoundException(id, "Acuartelamiento"));
		    return depListaAssembler.toCollection(acuartelamiento.getDepartamentos());
		}
		
		
		
		
		@GetMapping("/siglas/{abreviatura}")
		public AcuartelamientoModel AcuartelamientoPorSiglas(@PathVariable String abreviatura) {
		    AcuartelamientoConId Acuartelamiento = (AcuartelamientoConId) repositorio.findByAbreviatura(abreviatura)
		    		.orElseThrow(() -> new RegisterNotFoundException(abreviatura, "Acuartelamiento"));
		    return assembler.toModel(Acuartelamiento);
		}
		
		
		@GetMapping
		public CollectionModel<AcuartelamientoModel> all() {
			return listaAssembler.toCollection(repositorio.findAll());
		}

		@PostMapping
		public AcuartelamientoModel add(@Valid @RequestBody AcuartelamientoPostModel model) {
			AcuartelamientoConId acuartelamiento = repositorio.save(assembler.toEntity(model));
			log.info("Añadido " + acuartelamiento);
			return assembler.toModel(acuartelamiento);
		}
		
		
		@PutMapping("{id}")
		public AcuartelamientoModel edit(@Valid @PathVariable Long id, @RequestBody AcuartelamientoModel model) {
			  
			AcuartelamientoConId departamento = repositorio.findById(id).map(dep -> {
				
				dep.setNombre(model.getNombre());
				dep.setAbreviatura(model.getAbreviatura());
				dep.setEmail(model.getEmail());
				dep.setResponsableEmpleo(model.getResponsableEmpleo());
				dep.setResponsableNombre(model.getResponsableNombre());
				dep.setTelefono(model.getTelefono());
				
			return repositorio.save(dep);
			})
			.orElseThrow(() -> new RegisterNotFoundException(id, "Acuartelamiento"));
			log.info("Actualizado " + departamento);
			return assembler.toModel(departamento);
	}
		
		@DeleteMapping("{id}")
		public void delete(@PathVariable Long id) {
		    log.info("Borrado Acuartelamiento " + id);
		    AcuartelamientoConId acuartelamiento = (AcuartelamientoConId) repositorio.findById(id).map(ac -> {
					repositorio.deleteById(id);	
					return ac;
				})
				.orElseThrow(() -> new RegisterNotFoundException(id, "Acuartelamiento"));
		}
		
	
}
