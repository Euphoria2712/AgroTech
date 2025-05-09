package com.MicroEntregas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
	org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration.class,
    org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration.class,
    org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration.class,
    org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class,
   
})
public class MicroServicioEntregasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicioEntregasApplication.class, args);
	}

}
