package ru.enikhov.lesson16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DBUtil {
    private static final Logger logger = LogManager.getLogger(DBUtil.class);

    /**
     * инициализация таблиц. DDL таблиц в файле datagen.sql
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
                logger.debug(ddl);
                statement.executeUpdate(ddl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}