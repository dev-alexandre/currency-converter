package challenger.com.br.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
public class AppDBConfiguration {

    @Bean
    public ApplicationRunner init(DatabaseClient client) {
        return args ->
            client.sql(
                "DROP TABLE IF EXISTS operation;\n" +
                "CREATE TABLE operation (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    user_id Integer,\n" +
                "    operation_date date,\n" +
                "    currency_from VARCHAR(255),\n" +
                "    currency_to VARCHAR(255),\n" +
                "    amount_from numeric(10,2),\n" +
                "    amount_to numeric(10,2)\n" +
                ");"
            ).fetch().first().subscribe();
    }

}
