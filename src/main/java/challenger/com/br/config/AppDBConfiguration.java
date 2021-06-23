package challenger.com.br.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
public class AppDBConfiguration {

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
        populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        initializer.setDatabasePopulator(populator);

        return initializer;
    }


    @Bean
    public ApplicationRunner init(DatabaseClient client) {
        return args -> {
            client.sql(
                "DROP TABLE IF EXISTS operation;\n" +
                "CREATE TABLE operation (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    user_id Integer,\n" +
                "    operation_date date,\n" +
                "    result numeric(10,2),\n" +
                "    currency_from VARCHAR(255),\n" +
                "    currency_to VARCHAR(255),\n" +
                "    amount numeric(10,2)\n" +
                ");"
            ).fetch().first().subscribe();
        };
    }
}
