package tutorial.misiontic.seguridad.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tutorial.misiontic.seguridad.model.Rol;

public interface RolRepository extends MongoRepository<Rol,String> {
}
