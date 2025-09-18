package main.bancaria.persistence.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static main.bancaria.persistence.config.ConnectionConfig.getConnection;


public class MigrationConfig {
    private static final String createContaTableMigration = "src/main/resources/migrations/202508262024_create_table_contas.sql";
    public static void executeMigration(){
        Path tableMigration = Paths.get(createContaTableMigration);
        String tableScript = null;
        try {
            tableScript = new String(Files.readAllBytes(tableMigration));
            String[] scripts = tableScript.split(";");
            try(Connection connection = getConnection();
                Statement stmt = connection.createStatement()){
                for(String s: scripts){
                    stmt.executeUpdate(s);
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }
    }
}
