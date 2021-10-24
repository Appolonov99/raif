package com.example.raif.repos;

import com.example.raif.domain.Socks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocksRepo extends CrudRepository<Socks, Integer> {
    Socks findByColorAndCottonPart(String color, Integer cottonPart);
    Socks findByCottonPartEquals(Integer cottonPart);
    List<Socks> findByCottonPartLessThan(Integer cottonPart);
    List<Socks> findByCottonPartGreaterThan(Integer cottonPart);
}

