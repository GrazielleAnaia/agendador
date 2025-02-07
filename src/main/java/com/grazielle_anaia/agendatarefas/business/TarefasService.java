package com.grazielle_anaia.agendatarefas.business;

import com.grazielle_anaia.agendatarefas.business.dto.TarefasDTO;
import com.grazielle_anaia.agendatarefas.business.mapper.TarefasConverter;
import com.grazielle_anaia.agendatarefas.infrastructure.entity.TarefasEntity;
import com.grazielle_anaia.agendatarefas.infrastructure.enums.StatusNotificacao;
import com.grazielle_anaia.agendatarefas.infrastructure.repository.TarefasRepository;
import com.grazielle_anaia.agendatarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;


    public TarefasDTO gravarTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);
        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }

}


