package com.example.cv_builder.service;

import com.example.cv_builder.model.CV;
import com.example.cv_builder.repository.CVRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CVService {

    private final CVRepository repo = new CVRepository();
    private final ObservableList<CV> observableList = FXCollections.observableArrayList();

    public ObservableList<CV> getObservableList() {
        return observableList;
    }

    public CVRepository getRepository() {
        return repo;
    }

    // ---------------- SAVE ----------------
    public CompletableFuture<CV> save(CV cv) {

        boolean isNew = (cv.getId() == null);

        if (isNew) {
            return repo.insert(cv).thenApply(saved -> {
                Platform.runLater(() -> observableList.add(0, saved));
                return saved;
            });
        } else {
            return repo.update(cv).thenApply(v -> {
                Platform.runLater(() -> {
                    int index = -1;
                    for (int i = 0; i < observableList.size(); i++) {
                        CV item = observableList.get(i);
                        if (item.getId().equals(cv.getId())) { index = i; break; }
                    }
                    if (index >= 0) observableList.set(index, cv);
                    else observableList.add(0, cv);
                });
                return cv;
            });
        }
    }

    // ---------------- DELETE ----------------
    public CompletableFuture<Void> delete(CV cv) {
        if (cv.getId() == null)
            return CompletableFuture.completedFuture(null);

        return repo.delete(cv.getId())
                .thenRun(() -> Platform.runLater(() -> observableList.remove(cv)));
    }

    // ---------------- LOAD ALL ----------------
    public CompletableFuture<List<CV>> loadAll() {
        return repo.fetchAll().thenApply(list -> {
            Platform.runLater(() -> observableList.setAll(list));
            return list;
        });
    }

    // ---------------- SHUTDOWN ----------------
    public void shutdown() {
        repo.shutdown();
    }
}
