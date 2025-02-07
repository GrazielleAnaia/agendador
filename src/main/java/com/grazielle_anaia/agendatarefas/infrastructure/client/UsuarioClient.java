package com.grazielle_anaia.agendatarefas.infrastructure.client;

import com.grazielle_anaia.agendatarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="usuario", url="${usuario.url}")

public interface UsuarioClient {
    //apontar pra API o endpoint que vai trazer os dados do usuarios que usa o metodo findByEmail()

    @GetMapping("/usuario")
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);
}
