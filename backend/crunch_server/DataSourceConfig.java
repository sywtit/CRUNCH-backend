import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(){
        String username = "root";
        String password = "orangemango";
        String jdbcUrl = "jdbc:mysql://cbyzchmu8dpf:3306/DB";
        String driverClass = "com.mysql.jdbc.Driver";

        DataSourceBuilder<DataSource> dataSourceBuilder = DataSourceBuilder.create();
        dataScourceBuilder.username(username);
        dataSourceBuilder.password(password);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.driverClassName(driverClass);
        return dataSourceBuilder.build();
    }
}
