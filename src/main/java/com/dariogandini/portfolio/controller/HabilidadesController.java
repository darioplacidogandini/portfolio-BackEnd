package com.dariogandini.portfolio.controller;

import java.util.List;

import com.dariogandini.portfolio.model.Habilidades;
import com.dariogandini.portfolio.repository.HabilidadesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"https://dariogandini-portfoliofrontend.firebaseapp.com","https://dariogandini-portfoliofrontend.web.app"})
@RestController
@RequestMapping("/api/habilidades")
public class HabilidadesController {

    @Autowired
    private HabilidadesRepository habilidadesRepository;

    @GetMapping("/listar")
    public List<Habilidades> listar() {
        return habilidadesRepository.findAll();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Habilidades> buscar(@PathVariable Long id) {
        Habilidades habilidades = habilidadesRepository.findById(id).orElse(null);
        return ResponseEntity.ok(habilidades);
    }

    @PostMapping("/agregar")
    public Habilidades agregar(@RequestBody Habilidades habilidades) {
        return habilidadesRepository.save(habilidades);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Habilidades> editar(@PathVariable Long id, @RequestBody Habilidades habilidad) {
        Habilidades habilidades = habilidadesRepository.findById(id).orElse(null);
        habilidades.setHabilidad(habilidad.getHabilidad());
        habilidades.setPorcentaje(habilidad.getPorcentaje());
        Habilidades habilidadModificada = habilidadesRepository.save(habilidades);
        return ResponseEntity.ok(habilidadModificada);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable(value = "id") Long habilidadesId) {
        Habilidades habilidades = habilidadesRepository.findById(habilidadesId).orElse(null);
        habilidadesRepository.delete(habilidades);
    }
    
}
