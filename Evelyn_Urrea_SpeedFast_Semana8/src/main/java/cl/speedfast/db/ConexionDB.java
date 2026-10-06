package cl.speedfast.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Centraliza la conexión JDBC y mantiene las credenciales fuera del repositorio. */
public final class ConexionDB {
    private static final Properties CONFIG = cargarConfig();
    private ConexionDB() {}

    private static Properties cargarConfig() {
        Properties p = new Properties();
        Path archivo = Path.of("config.properties");
        if (Files.exists(archivo)) {
            try (var in = Files.newInputStream(archivo)) { p.load(in); }
            catch (IOException e) { throw new ExceptionInInitializerError(e); }
        }
        return p;
    }

    public static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault("SPEEDFAST_DB_URL", CONFIG.getProperty("db.url", "jdbc:mysql://localhost:3306/speedfast_db?serverTimezone=America/Santiago&useSSL=false&allowPublicKeyRetrieval=true"));
        String user = System.getenv().getOrDefault("SPEEDFAST_DB_USER", CONFIG.getProperty("db.user", "root"));
        String password = System.getenv().getOrDefault("SPEEDFAST_DB_PASSWORD", CONFIG.getProperty("db.password", ""));
        return DriverManager.getConnection(url, user, password);
    }
}
