package com.example.raif.repos;

import com.example.raif.domain.Socks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocksRepo extends CrudRepository<Socks, Integer> {
    List<Socks> findByColor(String color);
    List<Socks> findByCottonPart(Integer color);
    Socks findByColorAndCottonPart(String color, Integer cottonPart);

}

