package com.grazielle_anaia.agendatarefas.business.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.grazielle_anaia.agendatarefas.infrastructure.enums.StatusNotificacao;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class TarefasDTO {

    private String id;

    private String nomeTarefa;

    private String descricao;

    private LocalDateTime dataCriacao;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern= "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;

    private String emailUsuario;
    private LocalDateTime dataAlteracao;

    private StatusNotificacao statusNotificacao;
}
