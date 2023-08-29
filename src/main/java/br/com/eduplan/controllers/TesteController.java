package br.com.eduplan.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teste")
public class TesteController {


    @PostMapping
    public ResponseEntity postTeste(@RequestBody @Valid String teste){

        return ResponseEntity.ok("Validou Admin");
    }

    @GetMapping
    public ResponseEntity getTeste(){

        return ResponseEntity.ok("Validou User");
    }

    @GetMapping(value = "/vai")
    public ResponseEntity getTeste2(){

        return ResponseEntity.ok("Validou User");
    }
}
