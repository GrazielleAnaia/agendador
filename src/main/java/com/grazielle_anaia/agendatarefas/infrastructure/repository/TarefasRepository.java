package com.grazielle_anaia.agendatarefas.infrastructure.repository;

import com.grazielle_anaia.agendatarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {
}
