package com.turismo.api.modules.departamento.config;

import com.turismo.api.modules.departamento.entity.Atractivo;
import com.turismo.api.modules.departamento.entity.DepartamentoInfo;
import com.turismo.api.modules.departamento.repository.DepartamentoInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartamentoDataSeeder implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DepartamentoDataSeeder.class);

	private final DepartamentoInfoRepository departamentoInfoRepository;

	public DepartamentoDataSeeder(DepartamentoInfoRepository departamentoInfoRepository) {
		this.departamentoInfoRepository = departamentoInfoRepository;
	}

	@Override
	public void run(String... args) {
		List<DepartamentoInfo> departamentos = departamentoInfoRepository.findAllByOrderByIdAsc();
		if (!departamentos.isEmpty()) {
			eliminarDuplicados(departamentos);
			return;
		}

		DepartamentoInfo departamento = DepartamentoInfo.builder()
				.titulo("DEPARTAMENTO DE POTOSI")
				.introduccion("Desde los pies del Sumaj Orcko, el desierto blanco, las lagunas de colores, "
						+ "la riqueza paleontologia y sus valles coloridos el departamento de Potosi se "
						+ "presenta entre misterios y colores. Podemos visitar destinos turisticos como:")
				.build();

		departamento.agregarAtractivo(Atractivo.builder()
				.nombre("Ciudad de Potosi")
				.descripcion("En la epoca de la colonia fue la ciudad mas poblada de todo el mundo "
						+ "superando a ciudades de Europa como Madrid, su riqueza mineral la hizo "
						+ "grande, visitar sus calles nos transporta en el tiempo.")
				.orden(1)
				.build());

		departamento.agregarAtractivo(Atractivo.builder()
				.nombre("Cerro Rico o Sumaj Orcko")
				.descripcion("Ingresar a los socavones donde los mineros desde hace muchos años "
						+ "extrajeron el mineral nos muestra el duro trabajo, vale la pena no solo "
						+ "verlo, sino visitarlo.")
				.orden(2)
				.build());

		departamento.agregarAtractivo(Atractivo.builder()
				.nombre("Desiertos Blancos (Salar de Uyuni)")
				.descripcion("Definitivamente es unico en el mundo, donde se conjuga el cielo con la "
						+ "tierra convirtiendose en uno solo, el destino turistico mas importante de "
						+ "nuestro pais, donde se encuentra tambien Incahuasi (La Isla del Pescado) el "
						+ "cual junto con otras son porciones de tierra con flora y fauna propias, como "
						+ "asi tambien Colchani donde elaboran todo tipo de material en base a la sal, "
						+ "sin dudas vale la pena estar en este hermoso destino turistico.")
				.orden(3)
				.build());

		departamento.agregarAtractivo(Atractivo.builder()
				.nombre("Cementerio de Trenes")
				.descripcion("Estos trenes que llegaron en 1899 son la muestra del progreso estancado "
						+ "en el tiempo, ningun viajero pasaria de largo, un museo al aire libre.")
				.orden(4)
				.build());

		departamento.agregarAtractivo(Atractivo.builder()
				.nombre("Laguna Colorada y Laguna Verde")
				.descripcion("Al sud oeste se encuentra la Reserva Eduardo Avaroa, lugar donde los "
						+ "flamencos rosados posan en sus aguas, es un sitio paradisiaco.")
				.orden(5)
				.build());

		departamento.agregarAtractivo(Atractivo.builder()
				.nombre("Toro Toro")
				.descripcion("Ubicado al norte del departamento de Potosi, se encuentra este sitio "
						+ "natural y llegando por Cochabamba se puede visitar y ver cañones, cavernas "
						+ "y caidas de agua espectaculares.")
				.orden(6)
				.build());

		departamentoInfoRepository.save(departamento);
	}

	private void eliminarDuplicados(List<DepartamentoInfo> departamentos) {
		if (departamentos.size() <= 1) {
			return;
		}

		List<DepartamentoInfo> duplicados = departamentos.subList(1, departamentos.size());
		log.warn("Se encontraron {} registros duplicados en departamento_info. Se conserva id={} y se eliminan los restantes.",
				duplicados.size(), departamentos.get(0).getId());
		departamentoInfoRepository.deleteAll(duplicados);
	}
}
