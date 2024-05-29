package com.example.ejerciciospring.presentation.rest;
import com.example.ejerciciospring.business.services.IInstrumentoService;
import com.example.ejerciciospring.business.services.IUsuarioService;
import com.example.ejerciciospring.domain.entities.Instrumento;
import com.example.ejerciciospring.domain.entities.Pedido;
import com.example.ejerciciospring.domain.entities.Usuario;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(value = "*",allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll() {
        try {
            var items = usuarioService.getAll();
            var filteredItems = items.stream()
                    .filter(Usuario::getActivo)
                    .toList();
            return ResponseEntity.ok().body(filteredItems);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/getUsuario")
    public ResponseEntity<Usuario> getUsuario(@RequestBody Usuario usuario) {
        try {
            var items = usuarioService.getAll();
            for (Usuario u : items) {
                // Encriptar la clave ingresada usando SHA3
                SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
                byte[] digest = digestSHA3.digest(usuario.getClave().getBytes());

                // Convertir el hash a hexadecimal
                String claveEncriptada = Hex.toHexString(digest);

                if (u.getNombreUsuario().equals(usuario.getNombreUsuario()) && u.getClave().equals(claveEncriptada)) {
                    return ResponseEntity.ok().body(u);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> crear(@RequestBody Usuario usuario){

        var usuarios = usuarioService.getAll();

        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuario.getNombreUsuario())) {
                return new ResponseEntity<>("Ya existe un usuario con ese nombre", HttpStatus.BAD_REQUEST);
            }
        }

        // Encriptar la clave usando SHA3
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] digest = digestSHA3.digest(usuario.getClave().getBytes());

        // Convertir el hash a hexadecimal
        String claveEncriptada = Hex.toHexString(digest);

        // Establecer la clave encriptada
        usuario.setClave(claveEncriptada);

        return ResponseEntity.ok().body(usuarioService.crear(usuario));
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario) {
        try {
            var updatedUsuario = usuarioService.actualizar(usuario);
            return ResponseEntity.ok().body(updatedUsuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<Usuario> eliminar(@RequestBody Usuario usuario) {
        try {
            var updatedUsuario = usuarioService.eliminar(usuario);
            return ResponseEntity.ok().body(updatedUsuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
