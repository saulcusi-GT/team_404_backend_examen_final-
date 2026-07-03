package com.turismo.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RenderDatabaseEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

	private static final String PROPERTY_SOURCE_NAME = "renderDatabaseEnvironment";

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		String dbUrl = environment.getProperty("DB_URL");
		String databaseUrl = environment.getProperty("DATABASE_URL");
		String selectedUrl = StringUtils.hasText(dbUrl) ? dbUrl : databaseUrl;

		if (!StringUtils.hasText(selectedUrl)) {
			return;
		}

		DatabaseConfig databaseConfig = toDatabaseConfig(selectedUrl);
		Map<String, Object> properties = new HashMap<>();
		properties.put("spring.datasource.url", databaseConfig.jdbcUrl());

		String username = environment.getProperty("DB_USERNAME");
		if (StringUtils.hasText(username)) {
			properties.put("spring.datasource.username", username);
		} else if (StringUtils.hasText(databaseConfig.username())) {
			properties.put("spring.datasource.username", databaseConfig.username());
		}

		String password = environment.getProperty("DB_PASSWORD");
		if (StringUtils.hasText(password)) {
			properties.put("spring.datasource.password", password);
		} else if (StringUtils.hasText(databaseConfig.password())) {
			properties.put("spring.datasource.password", databaseConfig.password());
		}

		environment.getPropertySources().addFirst(new MapPropertySource(PROPERTY_SOURCE_NAME, properties));
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

	private DatabaseConfig toDatabaseConfig(String rawUrl) {
		if (rawUrl.startsWith("jdbc:postgresql://")) {
			return new DatabaseConfig(rawUrl, null, null);
		}

		if (!rawUrl.startsWith("postgres://") && !rawUrl.startsWith("postgresql://")) {
			return new DatabaseConfig(rawUrl, null, null);
		}

		URI uri = URI.create(rawUrl);
		StringBuilder jdbcUrl = new StringBuilder("jdbc:postgresql://")
				.append(uri.getHost());

		if (uri.getPort() > 0) {
			jdbcUrl.append(":").append(uri.getPort());
		}

		jdbcUrl.append(uri.getPath());

		if (StringUtils.hasText(uri.getQuery())) {
			jdbcUrl.append("?").append(uri.getQuery());
		}

		String username = null;
		String password = null;
		String userInfo = uri.getUserInfo();

		if (StringUtils.hasText(userInfo)) {
			String[] parts = userInfo.split(":", 2);
			username = decode(parts[0]);
			if (parts.length > 1) {
				password = decode(parts[1]);
			}
		}

		return new DatabaseConfig(jdbcUrl.toString(), username, password);
	}

	private String decode(String value) {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException ex) {
			return value;
		}
	}

	private record DatabaseConfig(String jdbcUrl, String username, String password) {
	}
}
