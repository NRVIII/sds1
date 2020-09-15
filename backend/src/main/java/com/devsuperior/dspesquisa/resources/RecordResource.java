package com.devsuperior.dspesquisa.resources;

import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.services.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
public class RecordResource {

    private final RecordService service;

    public RecordResource(RecordService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Integer> insert(@RequestBody RecordInsertDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }
}
