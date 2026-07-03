package com.turismo.api.modules.hero.config;

import com.turismo.api.modules.hero.entity.HeroSeccion;
import com.turismo.api.modules.hero.repository.HeroSeccionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HeroDataSeeder implements CommandLineRunner {

	private final HeroSeccionRepository heroSeccionRepository;

	public HeroDataSeeder(HeroSeccionRepository heroSeccionRepository) {
		this.heroSeccionRepository = heroSeccionRepository;
	}

	@Override
	public void run(String... args) {
		if (heroSeccionRepository.count() > 0) {
			return;
		}

		HeroSeccion hero = HeroSeccion.builder()
				.textoSuperior("GENERALMENTE CONOCIDA COMO LA VILLA IMPERIAL DE POTOSI")
				.titulo("Ciudad de Potosi centro del Patrimonio Mundial")
				.imagenUrl("/images/salar-uyuni-hero.jpeg")
				.build();

		heroSeccionRepository.save(hero);
	}
}
