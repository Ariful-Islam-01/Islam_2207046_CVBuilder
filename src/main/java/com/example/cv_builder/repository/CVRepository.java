package com.example.cv_builder.repository;

import com.example.cv_builder.db.DatabaseManager;
import com.example.cv_builder.model.CV;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CVRepository {

    private final ExecutorService dbExecutor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "cv-db-executor");
        t.setDaemon(true);
        return t;
    });

    // ---------------- INSERT ----------------
    public CompletableFuture<CV> insert(CV cv) {
        return CompletableFuture.supplyAsync(() -> {
            String sql = """
                    INSERT INTO cvs(name,email,phone,address,education,skills,projects,experience)
                    VALUES (?,?,?,?,?,?,?,?)
                    """;

            try (Connection c = DatabaseManager.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, cv.getName());
                ps.setString(2, cv.getEmail());
                ps.setString(3, cv.getPhone());
                ps.setString(4, cv.getAddress());
                ps.setString(5, cv.getEducation());
                ps.setString(6, cv.getSkills());
                ps.setString(7, cv.getProjects());
                ps.setString(8, cv.getExperience());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) cv.setId(rs.getInt(1));
                }

                return cv;

            } catch (SQLException e) {
                throw new CompletionException(e);
            }
        }, dbExecutor);
    }

    // ---------------- UPDATE ----------------
    public CompletableFuture<Void> update(CV cv) {
        return CompletableFuture.runAsync(() -> {

            if (cv.getId() == null)
                throw new IllegalArgumentException("CV ID is null; cannot update.");

            String sql = """
                    UPDATE cvs
                    SET name=?, email=?, phone=?, address=?, education=?, skills=?, projects=?, experience=?
                    WHERE id=?
                    """;

            try (Connection c = DatabaseManager.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {

                ps.setString(1, cv.getName());
                ps.setString(2, cv.getEmail());
                ps.setString(3, cv.getPhone());
                ps.setString(4, cv.getAddress());
                ps.setString(5, cv.getEducation());
                ps.setString(6, cv.getSkills());
                ps.setString(7, cv.getProjects());
                ps.setString(8, cv.getExperience());
                ps.setInt(9, cv.getId());

                ps.executeUpdate();

            } catch (SQLException e) {
                throw new CompletionException(e);
            }

        }, dbExecutor);
    }

    // ---------------- DELETE ----------------
    public CompletableFuture<Void> delete(int id) {
        return CompletableFuture.runAsync(() -> {
            String sql = "DELETE FROM cvs WHERE id=?";
            try (Connection c = DatabaseManager.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new CompletionException(e);
            }
        }, dbExecutor);
    }

    // ---------------- FETCH ALL ----------------
    public CompletableFuture<List<CV>> fetchAll() {
        return CompletableFuture.supplyAsync(() -> {

            String sql = """
                    SELECT id, name, email, phone, address, education, skills, projects, experience
                    FROM cvs
                    ORDER BY id DESC
                    """;

            List<CV> list = new ArrayList<>();

            try (Connection c = DatabaseManager.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    CV cv = new CV(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address"),
                            rs.getString("education"),
                            rs.getString("skills"),
                            rs.getString("projects"),
                            rs.getString("experience")
                    );
                    list.add(cv);
                }

            } catch (SQLException e) {
                throw new CompletionException(e);
            }

            return list;

        }, dbExecutor);
    }

    // ---------------- SHUTDOWN ----------------
    public void shutdown() {
        dbExecutor.shutdown();
    }
}
