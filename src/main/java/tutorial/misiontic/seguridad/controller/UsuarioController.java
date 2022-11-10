package tutorial.misiontic.seguridad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tutorial.misiontic.seguridad.model.Usuario;
import tutorial.misiontic.seguridad.repository.UsuarioRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping("")
    public List<Usuario> index() {
        return this.usuarioRepo.findAll();
    }

    @GetMapping("/{id}")
    public Usuario show(@PathVariable String id){
        Optional<Usuario> opt = this.usuarioRepo.findById(id);
        return opt.orElse(null);
    }

    @PostMapping("")
    public Usuario create(@RequestBody Usuario infoUsuario){
        String nuevaContrasena = tecnicaHash(infoUsuario.getContrasena());
        infoUsuario.setContrasena(nuevaContrasena);
        return this.usuarioRepo.save(infoUsuario);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario infoUsuario){
        Optional<Usuario> opt = this.usuarioRepo.findById(id);
        if(opt.isPresent()){
            Usuario actual = opt.get();
            if(infoUsuario.getContrasena() != null && !infoUsuario.getContrasena().isBlank())
                actual.setContrasena(infoUsuario.getContrasena());
            if(infoUsuario.getSeudonimo() != null && !infoUsuario.getSeudonimo().isBlank())
                actual.setSeudonimo(infoUsuario.getSeudonimo());
            if(infoUsuario.getCorreo() != null && !infoUsuario.getCorreo().isBlank())
                actual.setCorreo(infoUsuario.getCorreo());
            return this.usuarioRepo.save(actual);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){

        Optional<Usuario> opt = this.usuarioRepo.findById(id);
        if(opt.isPresent()) {
            this.usuarioRepo.deleteById(id);
        }
    }

    public String tecnicaHash(String password){

        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : hash){
            sb.append(String.format("%02x",b));
        }
        return sb.toString();

    }
}
