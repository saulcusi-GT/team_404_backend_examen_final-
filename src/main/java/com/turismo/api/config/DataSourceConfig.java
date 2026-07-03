package com.turismo.api.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource dataSource() {
		DatabaseConfig databaseConfig = resolveDatabaseConfig();

		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setJdbcUrl(databaseConfig.jdbcUrl());
		dataSource.setUsername(databaseConfig.username());
		dataSource.setPassword(databaseConfig.password());

		System.out.println("Datasource configured from " + databaseConfig.source());
		return dataSource;
	}

	private DatabaseConfig resolveDatabaseConfig() {
		String databaseUrl = getEnv("DATABASE_URL");
		if (StringUtils.hasText(databaseUrl)) {
			return fromDatabaseUrl(databaseUrl);
		}

		String dbUrl = getEnv("DB_URL");
		String username = getEnv("DB_USERNAME");
		String password = getEnv("DB_PASSWORD");

		if (StringUtils.hasText(dbUrl) && StringUtils.hasText(username) && StringUtils.hasText(password)) {
			return new DatabaseConfig(toJdbcUrl(dbUrl), username, password, "DB_URL");
		}

		throw new IllegalStateException(
				"Database environment variables are required. Use DATABASE_URL or DB_URL, DB_USERNAME and DB_PASSWORD.");
	}

	private DatabaseConfig fromDatabaseUrl(String rawUrl) {
		if (rawUrl.startsWith("jdbc:postgresql://")) {
			String username = getEnv("DB_USERNAME");
			String password = getEnv("DB_PASSWORD");

			if (!StringUtils.hasText(username) || !StringUtils.hasText(password)) {
				throw new IllegalStateException(
						"DB_USERNAME and DB_PASSWORD are required when DATABASE_URL uses jdbc:postgresql format.");
			}

			return new DatabaseConfig(rawUrl, username, password, "DATABASE_URL");
		}

		if (!rawUrl.startsWith("postgres://") && !rawUrl.startsWith("postgresql://")) {
			throw new IllegalStateException("DATABASE_URL must start with postgres://, postgresql:// or jdbc:postgresql://.");
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

		String userInfo = uri.getUserInfo();
		if (!StringUtils.hasText(userInfo)) {
			throw new IllegalStateException("DATABASE_URL must include username and password.");
		}

		String[] parts = userInfo.split(":", 2);
		if (parts.length < 2) {
			throw new IllegalStateException("DATABASE_URL must include username and password.");
		}

		return new DatabaseConfig(jdbcUrl.toString(), decode(parts[0]), decode(parts[1]), "DATABASE_URL");
	}

	private String toJdbcUrl(String rawUrl) {
		if (rawUrl.startsWith("jdbc:postgresql://")) {
			return rawUrl;
		}

		if (rawUrl.startsWith("postgres://") || rawUrl.startsWith("postgresql://")) {
			return fromDatabaseUrl(rawUrl).jdbcUrl();
		}

		throw new IllegalStateException("DB_URL must start with jdbc:postgresql://, postgres:// or postgresql://.");
	}

	private String getEnv(String key) {
		String value = System.getenv(key);
		if (!StringUtils.hasText(value) || value.equals("${" + key + "}")) {
			return null;
		}

		return value;
	}

	private String decode(String value) {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException ex) {
			return value;
		}
	}

	private record DatabaseConfig(String jdbcUrl, String username, String password, String source) {
	}
}
