package challenger.com.br.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class AppDBConfiguration {

    @Bean
    public ApplicationRunner init(DatabaseClient client) throws IOException {

        final String sqlPath = "src/main/resources/schema.sql";
        String sql = Files.readString(Paths.get(sqlPath));

        return args ->
            client.sql(sql).fetch().first().subscribe();
    }

}
