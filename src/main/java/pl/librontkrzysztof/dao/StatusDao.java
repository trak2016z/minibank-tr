package pl.librontkrzysztof.dao;

import pl.librontkrzysztof.model.Status;

import java.util.List;

public interface StatusDao {
    Status findById(int id);
    List<Status> findAll();
}
