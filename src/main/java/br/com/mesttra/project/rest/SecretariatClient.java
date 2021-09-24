package br.com.mesttra.project.rest;

import br.com.mesttra.project.model.Secretariat;
import br.com.mesttra.project.request.AuthRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name="secretariats", url="http://localhost:8082/" )
public interface SecretariatClient {

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<String> auth(@RequestBody AuthRequest authRequest);

    @RequestMapping("/secretariats/{id}")
    public Optional<Secretariat> getSecretariat(@RequestHeader("Authorization") String bearerToken, @PathVariable Long id);

}
