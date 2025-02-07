package com.grazielle_anaia.agendatarefas.business;

import com.grazielle_anaia.agendatarefas.business.dto.TarefasDTO;
import com.grazielle_anaia.agendatarefas.business.mapper.TarefasConverter;
import com.grazielle_anaia.agendatarefas.business.mapper.TarefasUpdateConverter;
import com.grazielle_anaia.agendatarefas.infrastructure.entity.TarefasEntity;
import com.grazielle_anaia.agendatarefas.infrastructure.enums.StatusNotificacao;
import com.grazielle_anaia.agendatarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.grazielle_anaia.agendatarefas.infrastructure.repository.TarefasRepository;
import com.grazielle_anaia.agendatarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;
    private final TarefasUpdateConverter tarefasUpdateConverter;


    public TarefasDTO gravarTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacao(StatusNotificacao.PENDENTE);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);
        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);
        return tarefasConverter.paraListaTarefasDTO(listaTarefas);
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Id does not exist" + id, e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacao status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("tarefa nao encontrada" + id));
            entity.setStatusNotificacao(status);
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa", e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("tarefa nao encontrada" + id));
            tarefasUpdateConverter.updateTarefas(dto, entity); //usa-se caso o objeto seja nulo; nao precisa pegar o retorno
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("");

        }
    }


}


