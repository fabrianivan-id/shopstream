package com.shopstream.catalog.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.shopstream.catalog")
public class EsConfig {}
