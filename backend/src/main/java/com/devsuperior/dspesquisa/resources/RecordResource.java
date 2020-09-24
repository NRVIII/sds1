package com.devsuperior.dspesquisa.resources;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.services.RecordService;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
public class RecordResource {

    private final RecordService service;

    public RecordResource(RecordService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<Page<RecordDTO>> findAll(
        @RequestParam(defaultValue = "") String min,
        @RequestParam(defaultValue = "") String max,
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "12") Integer linesPerPage,
        @RequestParam(defaultValue = "moment") String orderBy,
        @RequestParam(defaultValue = "DESC") String direction) {

        Instant minDate = "".equals(min) ? null : Instant.parse(min);
        Instant maxDate = "".equals(max) ? null : Instant.parse(max);

        if (linesPerPage == 0) {
            linesPerPage = Integer.MAX_VALUE;
        }

        PageRequest pageRequest = PageRequest
            .of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        return ResponseEntity.ok()  
            .body(service.findByMoments(minDate, maxDate, pageRequest));
    }

    @PostMapping
    public ResponseEntity<Integer> insert(@RequestBody RecordInsertDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }
}
