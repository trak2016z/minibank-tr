package pl.librontkrzysztof.dao;

import pl.librontkrzysztof.model.Status;

public interface StatusDao {
    Status findById(int id);
}
