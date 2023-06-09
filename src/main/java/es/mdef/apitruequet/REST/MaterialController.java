package es.mdef.apitruequet.REST;

import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mde.acing.utils.MaterialImpl.EstadoMaterial;
import es.mde.acing.utils.MaterialImpl.TipoMaterial;
import es.mdef.apitruequet.entidades.NoInventariable;
import es.mdef.apitruequet.entidades.Inventariable;
import es.mdef.apitruequet.ApiTruequetApp;
import es.mdef.apitruequet.entidades.CategoriaConId;
import es.mdef.apitruequet.entidades.DepartamentoConId;
import es.mdef.apitruequet.entidades.MaterialConId;
import es.mdef.apitruequet.repositorios.CategoriaRepositorio;
import es.mdef.apitruequet.repositorios.DepartamentoRepositorio;
import es.mdef.apitruequet.repositorios.MaterialRepositorio;
import es.mdef.apitruequet.validation.RegisterNotFoundException;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/materiales")
public class MaterialController {
	private final MaterialRepositorio repositorio;
	private final DepartamentoRepositorio repDepartamento;
	private final CategoriaRepositorio repCategoria;
	private final MaterialAssembler assembler;
	private final MaterialListaAssembler listaAssembler;

	private final Logger log;

	MaterialController(MaterialRepositorio repositorio, MaterialAssembler assembler,
			MaterialListaAssembler listaAssembler, DepartamentoRepositorio repDepartamento, 
			CategoriaRepositorio repCategoria) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.repDepartamento = repDepartamento;
		this.repCategoria = repCategoria;
		log = ApiTruequetApp.log;
	}

	@GetMapping("{id}")
	public MaterialModel one(@PathVariable Long id) {
		MaterialConId material = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "material"));
		log.info("Recuperada " + material);
		return assembler.toModel(material);
	}

	@GetMapping
	public CollectionModel<MaterialListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}

	@PostMapping
	public MaterialModel add(@Valid @RequestBody MaterialPostModel model) {
		MaterialConId material = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + material);

		// actualizamos los milis del dpto
		DepartamentoConId departamento = repDepartamento.findById(model.getDptoOferta().getId())
				.orElseThrow(() -> new RegisterNotFoundException(model.getDptoOferta().getId(), "Departamento"));

		int bonificacion = 0;
		if (model.getTipoMaterial() == TipoMaterial.noInventariable) {
			bonificacion = model.getBonificacion() > 0 ? model.getBonificacion() : 0;
		}

		log.info("AUMENTANDO CREDITO (BONIFICACION EN  ", bonificacion, "MILIS");
		departamento.incremaentarMateriales();
		departamento.aumentarCredito(bonificacion);
		repDepartamento.save(departamento);
		log.info("Credito aumentado en " + bonificacion + " para " + departamento);

		// actualizamos los milis del dpto
		CategoriaConId cat = repCategoria.findById(model.getCategoria().getId())
				.orElseThrow(() -> new RegisterNotFoundException(model.getDptoOferta().getId(), "Categoria"));
		
		cat.incremaentarMateriales();
		repCategoria.save(cat);
		return assembler.toModel(material);
	}

	@PutMapping("{id}")
	public MaterialModel edit(@Valid @PathVariable Long id, @RequestBody MaterialPostModel model) {
		MaterialConId material = repositorio.findById(id).map(mat -> {

			if (model.getTipoMaterial() == TipoMaterial.Inventariable) {
				Inventariable inv = new Inventariable();
				repositorio.actualizarInventariable(model.getNumeroSerie(), model.getNoc(), id);
				inv.setNumeroSerie(model.getNumeroSerie());
				inv.setNoc(model.getNoc());
			} else if (model.getTipoMaterial() == TipoMaterial.noInventariable) {
				NoInventariable noInv = new NoInventariable();
				repositorio.actualizarNoInventariable(model.getBonificacion(), id);
				noInv.setBonificacion(model.getBonificacion());
			}

			mat.setNombre(model.getNombre());
			mat.setDescripcion(model.getDescripcion());
			mat.setCantidad(model.getCantidad());
			mat.setDimensiones(model.getDimensiones());
			mat.setPeso(model.getPeso());
			mat.setFechaAdquisicion(model.getFechaAdquisicion());
			mat.setFechaOferta(model.getFechaOferta());
			mat.setCantidad(model.getCantidad());
			mat.setMilis(model.getMilis());
			mat.setEstado(model.getEstado());
			mat.setImagen(model.getImagen());
			mat.setImgReducida(model.getImgReducida());

			mat.setDeptoOferta(model.getDptoOferta());
			mat.setCategoria(model.getCategoria());
			mat.setDptoAdquisicion(model.getDptoAdquisicion());

			return repositorio.save(mat);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Material"));
		log.info("Actualizado " + material);
		return assembler.toModel(material);
	}

	@PatchMapping("/{id}/fechaentrega")
	public MaterialModel patchFechaentrega(@PathVariable Long id, @RequestBody FechaEntregaModel model) {
		MaterialConId material = repositorio.findById(id).map(mat -> {
			mat.setFechaEngregaFisica(model.getFechaEntrega());
			mat.setEstado(EstadoMaterial.entregado);
			return repositorio.save(mat);
		}).orElseThrow(() -> new RegisterNotFoundException(id, "Material"));
		log.info("Actualizado " + material);
		return assembler.toModel(material);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado Material " + id);
		MaterialConId material = repositorio.findById(id).map(mat -> {
			if (mat.getTipoMaterial() == TipoMaterial.noInventariable) {

				DepartamentoConId departamento = repDepartamento
						.findById(((DepartamentoConId) mat.getDeptoOferta()).getId()).orElseThrow(
								() -> new RegisterNotFoundException(((DepartamentoConId) mat.getDeptoOferta()).getId(),
										"Departamento"));

				int bonificacion = 0;
				NoInventariable noinv = (NoInventariable) mat;
				bonificacion = noinv.getBonificacion();
				log.info("DISMINUYENDO CREDITO (BONIFICACION EN  ", bonificacion, "MILIS");
				// si se borra el material, en caso de haber tenido bonificación, se borra
				departamento.aumentarCredito(-bonificacion);
				departamento.decrementarMateriales();
			}
			CategoriaConId cat = repCategoria.findById(((CategoriaConId) mat.getCategoria()).getId()).orElseThrow(
					() -> new RegisterNotFoundException(((CategoriaConId) mat.getCategoria()).getId(),	"Categoria"));
			
			cat.decrementarMateriales();
			repCategoria.save(cat);

			repositorio.deleteById(id);
			return mat;
		}).orElseThrow(() -> new RegisterNotFoundException(id, "material"));
	}

}