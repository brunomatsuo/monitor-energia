package com.fiap.challenge.monitorenergia.dominio.controller;

import com.fiap.challenge.monitorenergia.dominio.dto.EletrodomesticoDTO;
import com.fiap.challenge.monitorenergia.dominio.services.EletrodomesticoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/eletrodomesticos")
public class EletrodomesticoController {

    @Autowired
    private EletrodomesticoService service;

    @GetMapping
    public ResponseEntity<Page<EletrodomesticoDTO>> getEletrodomesticos(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "quatidade", defaultValue = "10") Integer quantidade,
            @RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
            @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao
    ){
        PageRequest pageRequest = PageRequest.of(pagina, quantidade, Sort.Direction.valueOf(direcao), ordenacao);
        var list = service.findAll(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EletrodomesticoDTO> findById(
            @PathVariable Long id
    ){
        EletrodomesticoDTO eletrodomesticoDTO = service.findById(id);
        return ResponseEntity.ok(eletrodomesticoDTO);
    }

    @PostMapping
    public ResponseEntity<EletrodomesticoDTO> novoEletrodomestico(@RequestBody EletrodomesticoDTO dto){
        EletrodomesticoDTO eletrodomesticoDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((eletrodomesticoDTO.getId())).toUri();

        return ResponseEntity.created(uri).body(eletrodomesticoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EletrodomesticoDTO> updateEletrodomestico(
            @PathVariable Long id,
            @RequestBody EletrodomesticoDTO dto
    ){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEletrodomestico(
            @PathVariable Long id
    ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
