package com.grazielle_anaia.agendatarefas.business.mapper;


import com.grazielle_anaia.agendatarefas.business.dto.TarefasDTO;
import com.grazielle_anaia.agendatarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefasEntity(TarefasDTO dto);

    TarefasDTO paraTarefasDTO(TarefasEntity entity);
}
