package pl.librontkrzysztof.dao;

import pl.librontkrzysztof.model.ImageToken;

import java.util.List;

public interface ImageTokenDao {
    void save(ImageToken data);
    ImageToken findById(int id);
    ImageToken findRandom();
    List<ImageToken> findAll();
    void deleteById(int id);
}
