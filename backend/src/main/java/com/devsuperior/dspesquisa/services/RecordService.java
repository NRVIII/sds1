package com.devsuperior.dspesquisa.services;

import com.devsuperior.dspesquisa.dto.RecordDTO;
import com.devsuperior.dspesquisa.dto.RecordInsertDTO;
import com.devsuperior.dspesquisa.entities.Game;
import com.devsuperior.dspesquisa.entities.Record;
import com.devsuperior.dspesquisa.repositories.GameRepository;
import com.devsuperior.dspesquisa.repositories.RecordRepository;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecordService {

    private RecordRepository repository;
    private GameRepository gameRepository;

    public RecordService(RecordRepository repository,
        GameRepository gameRepository) {
        this.repository = repository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public int insert(RecordInsertDTO dto) {
        Game game = gameRepository.getOne(dto.getGameId());

        final Record entity = new Record(
            null,
            dto.getName(),
            dto.getAge(),
            Instant.now(),
            game);
        repository.save(entity);

        return 1;
    }


    public Page<RecordDTO> findByMoments(
        Instant min,
        Instant max,
        PageRequest pageRequest) {

        return repository.findByMoments(
            min,
            max,
            pageRequest).map(RecordDTO::new);
    }
}
