package com.example.raif.repos;

import com.example.raif.domain.Socks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocksRepo extends CrudRepository<Socks, Integer> {
    Socks findByColorAndCottonPart(String color, Integer cottonPart);
    Socks findByCottonPartEquals(Integer cottonPart);
    List<Socks> findByColorAndCottonPartLessThan(String color, Integer cottonPart);
    List<Socks> findByColorAndCottonPartGreaterThan(String color, Integer cottonPart);
}

