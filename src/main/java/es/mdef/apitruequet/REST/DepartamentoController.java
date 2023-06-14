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
import es.mdef.apitruequet.entidades.DepartamentoConId;
import es.mdef.apitruequet.repositorios.DepartamentoRepositorio;
import es.mdef.apitruequet.repositorios.MaterialRepositorio;
import es.mdef.apitruequet.validation.RegisterNotFoundException;
import jakarta.validation.Valid;
import es.mde.acing.utils.UnidadImpl.TipoEmpleo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
	private final DepartamentoRepositorio repositorio;
	private final MaterialRepositorio matRepositorio;
	private final DepartamentoAssembler assembler;
	private final DepartamentoListaAssembler listaAssembler;
	private final MaterialListaAssembler matListaAssembler;

	private final Logger log;

	DepartamentoController(DepartamentoRepositorio repositorio, DepartamentoAssembler assembler,
			DepartamentoListaAssembler listaAssembler, MaterialListaAssembler matListaAssembler,
			MaterialRepositorio matRepositorio) {
		this.repositorio = repositorio;
		this.matRepositorio = matRepositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.matListaAssembler = matListaAssembler;
		log = ApiTruequetApp.log;
	}

	@GetMapping("{id}")
	public DepartamentoModel one(@PathVariable Long id) {
		DepartamentoConId departamento = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "departamento"));
		log.info("Recuperada " + departamento);
		return assembler.toModel(departamento);
	}

	@GetMapping("{id}/materialesOfertados")
	public CollectionModel<MaterialListaModel> materialesOfertados(@PathVariable Long id) {
		DepartamentoConId departamento = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "departamento"));
		return matListaAssembler.toCollection(departamento.getMaterialesOfertados());
	}

	@GetMapping("/empleos")
	public TipoEmpleo[] getTiposEmpleo() {
		return TipoEmpleo.values();
	}

	@GetMapping("{id}/materialesAdquiridos")
	public CollectionModel<MaterialListaModel> materialesAdquiridos(@PathVariable Long id) {
		DepartamentoConId departamento = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "departamento"));
		return matListaAssembler.toCollection(departamento.getMaterialesAdquiridos());
	}

	@GetMapping("/siglas/{abreviatura}")
	public DepartamentoModel departamentoPorSiglas(@PathVariable String abreviatura) {
		DepartamentoConId departamento = repositorio.findByAbreviatura(abreviatura)
				.orElseThrow(() -> new RegisterNotFoundException(abreviatura, "departamento"));
		return assembler.toModel(departamento);
	}

	@GetMapping
	public CollectionModel<DepartamentoListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}

	@PostMapping
	public DepartamentoModel add(@Valid @RequestBody DepartamentoPostModel model) {
		DepartamentoConId departamento = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + departamento);
		return assembler.toModel(departamento);
	}

	@PutMapping("{id}")
	public DepartamentoModel edit(@Valid @PathVariable Long id, @RequestBody DepartamentoPostModel model) {

		DepartamentoConId departamento = repositorio.findById(id).map(dep -> {

			dep.setNombre(model.getNombre());
			dep.setAbreviatura(model.getAbreviatura() + "-" + model.getAcuartelamiento().getAbreviatura());
			dep.setAcuartelamiento(model.getAcuartelamiento());
			dep.setEmail(model.getEmail());
			dep.setResponsableEmpleo(model.getResponsableEmpleo());
			dep.setResponsableNombre(model.getResponsableNombre());
			dep.setTelefono(model.getTelefono());
			dep.setLongitud(model.getLongitud());
			dep.setLatitud(model.getLatitud());
			dep.setDireccion(model.getDireccion());

			return repositorio.save(dep);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Departamento"));
		log.info("Actualizado " + departamento);
		return assembler.toModel(departamento);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado Departamento " + id);
		DepartamentoConId departamento = repositorio.findById(id).map(fam -> {
			repositorio.deleteById(id);
			return fam;
		}).orElseThrow(() -> new RegisterNotFoundException(id, "departamento"));
	}

	@PutMapping("{id}/aumentarCredito")
	public DepartamentoModel aumentarCredito(@PathVariable Long id, @RequestParam int cantidad) {
		DepartamentoConId departamento = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "Departamento"));

		departamento.aumentarCredito(cantidad);
		repositorio.save(departamento);
		log.info("Credito aumentado en " + cantidad + " para " + departamento);
		return assembler.toModel(departamento);
	}

	// metodo personalizado
	@GetMapping("{id}/calcularBonificacion")
	public BonificacionModel calcularBonificacion(@PathVariable long id) {

		// int bonificacion = 0;
		// no es necesario inicializar, utilizamos COALESCE en la query
		int bonificacion = matRepositorio.calcularBonificacion((int) id);
		BonificacionModel bonificacionModel = new BonificacionModel();
		bonificacionModel.setBonificacion(bonificacion);

		return bonificacionModel;
	}

}
