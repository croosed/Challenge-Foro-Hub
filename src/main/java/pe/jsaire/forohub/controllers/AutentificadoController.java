package pe.jsaire.forohub.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.jsaire.forohub.securities.DatoTokenJWT;
import pe.jsaire.forohub.securities.TockenService;
import pe.jsaire.forohub.models.Usuario;
import pe.jsaire.forohub.models.dto.UsuarioDTO;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutentificadoController {

    private final TockenService service;

    private final AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<?> inicarSesion(@Valid @RequestBody UsuarioDTO user) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        var autenticacion = manager.authenticate(authenticationToken);
        var tokenJWT = service.generateToken((Usuario) autenticacion.getPrincipal());
        return ResponseEntity.ok(new DatoTokenJWT(tokenJWT));

    }

}
