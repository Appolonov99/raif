package com.example.raif.controller;

import com.example.raif.dto.RequestDto;
import com.example.raif.service.SocksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final SocksService socksService;

    public MainController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/api/socks/income")
    public @ResponseBody ResponseEntity<Object> addNewSocks (@RequestBody RequestDto request) {

        if (!checkRequest(request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return socksService.addSocks(request)?
                ResponseEntity.ok().build():
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        
    }

    @PostMapping("/api/socks/outcome")
    public @ResponseBody ResponseEntity<Object> deleteSocks (@RequestBody RequestDto request) {

        if (!checkRequest(request))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return socksService.deleteSocks(request)?
                ResponseEntity.ok().build():
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @GetMapping("api/socks")
    public @ResponseBody ResponseEntity<Integer> getSocks(@RequestParam String color ,
                                                          @RequestParam String operation,
                                                          @RequestParam Integer cottonPart) {


        if (!checkRequest(color , operation, cottonPart))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        Integer result = socksService.getQuantity(color, operation, cottonPart);
        if (result == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok(result);

    }


    private boolean checkRequest (RequestDto request) {
        return request != null && request.getColor() != null && request.getCottonPart() != null && request.getQuantity() != null && request.getCottonPart() >= 0 && request.getCottonPart() <= 100 && request.getQuantity() > 0;
    }

    private boolean checkRequest (String color , String operation, Integer cottonPart) {
        return color != null && cottonPart != null && operation != null && cottonPart >= 0 && cottonPart <= 100;
    }

}
