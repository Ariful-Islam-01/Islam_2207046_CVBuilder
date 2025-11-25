package com.example.cv_builder.db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class DatabaseManager {
    private static final String DB_DIR = "db";
    private static final String DB_FILE = "db/cvs.db";
    private static final String URL = "jdbc:sqlite:" + DB_FILE;

    static {
        try {
            initDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    private static void initDatabase() throws Exception {
        Path dbDir = Path.of(DB_DIR);
        if (!Files.exists(dbDir)) Files.createDirectories(dbDir);

        // create DB file if not exists by connecting
        try (Connection c = getConnection()) {
            // create cvs table
            String create = """
                    CREATE TABLE IF NOT EXISTS cvs (
                      id INTEGER PRIMARY KEY AUTOINCREMENT,
                      name TEXT NOT NULL,
                      email TEXT NOT NULL,
                      phone TEXT NOT NULL,
                      address TEXT NOT NULL,
                      education TEXT,
                      skills TEXT,
                      projects TEXT,
                      experience TEXT
                    );
                    """;
            try (Statement s = c.createStatement()) {
                s.execute(create);
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
