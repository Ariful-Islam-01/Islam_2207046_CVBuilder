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

    public CVRepository getRepository() { return repo; }

    public CompletableFuture<CV> save(CV cv) {
        if (cv.getId() == null) {
            return repo.insert(cv).thenApply(saved -> {
                Platform.runLater(() -> observableList.add(0, saved));
                return saved;
            });
        } else {
            return repo.update(cv).thenApply(v -> {
                Platform.runLater(() -> {
                    int idx = -1;
                    for (int i = 0; i < observableList.size(); i++) {
                        CV c = observableList.get(i);
                        if (c.getId() != null && c.getId().equals(cv.getId())) { idx = i; break; }
                    }
                    if (idx >= 0) observableList.set(idx, cv);
                    else observableList.add(0, cv);
                });
                return cv;
            });
        }
    }

    public CompletableFuture<Void> delete(CV cv) {
        if (cv.getId() == null) return CompletableFuture.completedFuture(null);
        return repo.delete(cv.getId()).thenRun(() -> Platform.runLater(() -> observableList.remove(cv)));
    }

    public CompletableFuture<List<CV>> loadAll() {
        return repo.fetchAll().thenApply(list -> {
            Platform.runLater(() -> observableList.setAll(list));
            return list;
        });
    }

    public void shutdown() {
        repo.shutdown();
    }
}
