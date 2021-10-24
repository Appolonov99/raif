package com.example.raif.controller;

import com.example.raif.domain.Socks;
import com.example.raif.repos.SocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
//@RequestMapping(path="/api")
public class MainController {

    @Autowired
    private SocksRepo socksRepo;

    @PostMapping("/socks/income")
    public @ResponseBody String addNewSocks (@RequestParam String color ,
                                             @RequestParam Integer cottonPart ,
                                             @RequestParam Integer quantity) {


        Socks n1 = new Socks(color, cottonPart, quantity);
        if (socksRepo.findByColorAndCottonPart(color, cottonPart) != null) {
            Socks n2 = socksRepo.findByColorAndCottonPart(color, cottonPart);
            Socks n3 = new Socks(color, cottonPart, n1.getQuantity()+n2.getQuantity());
            socksRepo.save(n3);
            socksRepo.delete(n2);
        }
        else {
            socksRepo.save(n1); }
        return "Added";
    }

    @PostMapping("/socks/outcome")
    public @ResponseBody String deleteSocks (@RequestParam String color ,
                                             @RequestParam Integer cottonPart ,
                                             @RequestParam Integer quantity) {


        Socks n1 = new Socks(color, cottonPart, quantity);
        if (socksRepo.findByColorAndCottonPart(color, cottonPart) != null) {
            Socks n2 = socksRepo.findByColorAndCottonPart(color, cottonPart);
            Socks n3 = new Socks(color, cottonPart, n2.getQuantity()-n1.getQuantity());
            socksRepo.save(n3);
            socksRepo.delete(n2);
        }
        return "Deleted";
    }

    @GetMapping("/socks/test")
    public @ResponseBody Integer getAllSocks(@RequestParam String color , @RequestParam String operation,
                                             @RequestParam Integer cottonPart) {
        if (operation.equals("moreThan")) {
            Integer count = new Integer(0);
            List<Socks> socks =socksRepo.findByCottonPartGreaterThan(cottonPart);
            for (Socks s : socks) { count = count +s.getQuantity();}
            return count;
        }
        else if (operation.equals("lessThan")) {
            Integer count = new Integer(0);
            List<Socks> socks =socksRepo.findByCottonPartLessThan(cottonPart);
            for (Socks s : socks) { count = count +s.getQuantity();}
            return count;
        }
        else if(operation.equals("equal")) {
            Socks n2 = socksRepo.findByColorAndCottonPart(color, cottonPart);
            return n2.getQuantity();
        }
        return 10;
    }
    @GetMapping("/socks")
    public @ResponseBody Iterable<Socks> getAllSocks() {
        return socksRepo.findAll();
    }

}
