package br.com.mesttra.project.rest;

import br.com.mesttra.project.model.Secretariat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@FeignClient(name="secretariats", url="http://localhost:8082/" )
public interface SecretariatClient {

    @RequestMapping("/secretariats/{id}")
    public Optional<Secretariat> getSecretariat(@PathVariable Long id);

}
