package com.turismo.api.modules.hero.config;

import com.turismo.api.modules.hero.entity.HeroSeccion;
import com.turismo.api.modules.hero.repository.HeroSeccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HeroDataSeeder implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(HeroDataSeeder.class);

	private final HeroSeccionRepository heroSeccionRepository;

	public HeroDataSeeder(HeroSeccionRepository heroSeccionRepository) {
		this.heroSeccionRepository = heroSeccionRepository;
	}

	@Override
	public void run(String... args) {
		List<HeroSeccion> heroes = heroSeccionRepository.findAllByOrderByIdAsc();
		if (!heroes.isEmpty()) {
			eliminarDuplicados(heroes);
			return;
		}

		HeroSeccion hero = HeroSeccion.builder()
				.textoSuperior("GENERALMENTE CONOCIDA COMO LA VILLA IMPERIAL DE POTOSI")
				.titulo("Ciudad de Potosi centro del Patrimonio Mundial")
				.imagenUrl("/images/salar-uyuni-hero.jpeg")
				.build();

		heroSeccionRepository.save(hero);
	}

	private void eliminarDuplicados(List<HeroSeccion> heroes) {
		if (heroes.size() <= 1) {
			return;
		}

		List<HeroSeccion> duplicados = heroes.subList(1, heroes.size());
		log.warn("Se encontraron {} registros duplicados en hero_seccion. Se conserva id={} y se eliminan los restantes.",
				duplicados.size(), heroes.get(0).getId());
		heroSeccionRepository.deleteAll(duplicados);
	}
}
