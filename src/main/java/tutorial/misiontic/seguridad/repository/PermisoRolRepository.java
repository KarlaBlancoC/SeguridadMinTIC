package tutorial.misiontic.seguridad.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tutorial.misiontic.seguridad.model.PermisoRol;

public interface PermisoRolRepository extends MongoRepository<PermisoRol,String> {
}
