package com.grazielle_anaia.agendatarefas.infrastructure.entity;

import com.grazielle_anaia.agendatarefas.infrastructure.enums.StatusNotificacao;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document("tarefas")

public class TarefasEntity  {
    @Id
    private String id;

    private String nomeTarefa;

    private String descricao;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;

    private StatusNotificacao statusNotificacao;



}
