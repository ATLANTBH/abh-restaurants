package com.atlantbh.devdays.demo.abh.restaurants.configuration.datasource;

import com.atlantbh.devdays.demo.abh.restaurants.configuration.datasource.config.EmbeddedPgConfig;

import java.io.IOException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;
import ru.yandex.qatools.embed.postgresql.distribution.Version;

/**
 * Embedded postgres datasource configuration. Embedded postgres is only used on integration.
 *
 * @author Kenan Klisura
 */
@Configuration
@Profile({"integration"})
public class EmbeddedPgDatasourceConfiguration {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(EmbeddedPgDatasourceConfiguration.class);

  @Bean
  public DataSource dataSource(EmbeddedPgConfig config, EmbeddedPostgres embeddedPostgres) {
    LOGGER.info("Starting embedded postgres database...");

    try {
      String url = embeddedPostgres.start(config.getHost(), config.getPort(), config.getName());
      return new DriverManagerDataSource(url);
    } catch (IOException e) {
      LOGGER.error("Failed starting embedded postgres database.", e);
      throw new RuntimeException(e);
    }
  }

  @Bean(destroyMethod = "stop")
  public EmbeddedPostgres embeddedPostgres() {
    return new EmbeddedPostgres(Version.V9_6_8);
  }
}
