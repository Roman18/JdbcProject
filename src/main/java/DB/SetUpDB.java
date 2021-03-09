package DB;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;


public class SetUpDB {
    private final String dsn = "jdbc:postgresql://localhost:8000/humaninfo";
    private final String user = "postgres";
    private final String password = "postgres";
    private DataSource dataSource;
    public SetUpDB() {
        dataSource = new HikariDataSource(createPool());

    }
    private HikariConfig createPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dsn);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(4);
        hikariConfig.setMinimumIdle(4);
        return new HikariDataSource(hikariConfig);
    }

    public DataSource getDataSource(){
        return dataSource;
    }
}
