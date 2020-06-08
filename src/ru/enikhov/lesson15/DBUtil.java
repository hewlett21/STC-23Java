package ru.enikhov.lesson15;

import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DBUtil {

    /** инициализация таблиц. DDL таблиц в файле datagen.sql
     *
     * @param conn
     * @throws IOException
     */
    public static void recreateTable(Connection conn) throws IOException {
        try (Statement statement = conn.createStatement()) {
            try (FileInputStream fis = new FileInputStream("datagen.sql");
                 InputStreamReader isr = new InputStreamReader(fis, Charset.forName("CP1251"));
                 BufferedReader reader = new BufferedReader(isr);
            ) {
                String ddl = reader.lines().collect(Collectors.joining("\n"));
                statement.executeUpdate(ddl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}