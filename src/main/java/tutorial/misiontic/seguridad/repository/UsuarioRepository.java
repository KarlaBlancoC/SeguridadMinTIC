package tutorial.misiontic.seguridad.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tutorial.misiontic.seguridad.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}
