package es.mdef.apitruequet.REST;

import java.util.List;
import java.util.stream.Collectors;
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
import es.mdef.apitruequet.entidades.CategoriaConId;
import es.mdef.apitruequet.repositorios.CategoriaRepositorio;
import es.mdef.apitruequet.validation.RegisterNotFoundException;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	private final CategoriaRepositorio repositorio;
	private final CategoriaAssembler assembler;
	private final CategoriaListaAssembler listaAssembler;
	private final MaterialListaAssembler matListaAssembler;

	private final Logger log;

	CategoriaController(CategoriaRepositorio repositorio, CategoriaAssembler assembler,
			CategoriaListaAssembler listaAssembler, MaterialListaAssembler matListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.matListaAssembler = matListaAssembler;
		log = ApiTruequetApp.log;
	}

	@GetMapping("{id}")
	public CategoriaModel one(@PathVariable Long id) {
		CategoriaConId categoria = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "categoria"));
		log.info("Recuperada " + categoria);
		return assembler.toModel(categoria);
	}

	@GetMapping("{id}/materiales")
	public CollectionModel<MaterialListaModel> materialesDeCategoria(@PathVariable Long id) {
		CategoriaConId categoria = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "categoria"));
		return matListaAssembler.toCollection(categoria.getMateriales());
	}

	@GetMapping("/buscarcategoria")
	public CategoriaModel categoriaPorNombre(@RequestParam(value = "categoria") String categoria) {
		CategoriaConId categoriaBuscada = repositorio.findByCategoria(categoria)
				.orElseThrow(() -> new RegisterNotFoundException(categoria, "categoria"));

		return assembler.toModel(categoriaBuscada);
	}

	@GetMapping("/grupos")
	public String[] grupos() {
		return repositorio.grupos();
	}

	@GetMapping("/categoriasNormales")
	public CollectionModel<CategoriaListaModel> categoriasNormales() {
		List<CategoriaConId> categorias = repositorio.findAll().stream()
				.filter(categoria -> categoria.getCategoriaPadre() != null).collect(Collectors.toList());

		return listaAssembler.toCollection(categorias);
	}

	@GetMapping("/categoriasPrincipales")
	public CollectionModel<CategoriaListaModel> categoriasPrincipales() {
		List<CategoriaConId> categorias = repositorio.findAll().stream()
				.filter(categoria -> categoria.getCategoriaPadre() == null).collect(Collectors.toList());
		return listaAssembler.toCollection(categorias);
	}

	@GetMapping
	public CollectionModel<CategoriaListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}

	@PostMapping
	public CategoriaModel add(@Valid @RequestBody CategoriaPostModel model) {
		CategoriaConId categoria = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + categoria);
		return assembler.toModel(categoria);
	}

	@PutMapping("{id}")
	public CategoriaModel edit(@Valid @PathVariable Long id, @RequestBody CategoriaPostModel model) {

		CategoriaConId categoria = repositorio.findById(id).map(cat -> {
			cat.setCategoria(model.getCategoria());
			cat.setDescripcion(model.getDescripcion());
			cat.setCategoriaPadre(model.getCategoriaPadre());
			cat.setMinMilis(model.getMinMilis());
			cat.setMaxMilis(model.getMaxMilis());

			return repositorio.save(cat);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Categoria"));
		log.info("Actualizado " + categoria);
		return assembler.toModel(categoria);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado Categoria " + id);
		CategoriaConId categoria = repositorio.findById(id).map(fam -> {
			repositorio.deleteById(id);
			return fam;
		}).orElseThrow(() -> new RegisterNotFoundException(id, "categoria"));
	}

}
