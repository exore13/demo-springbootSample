package tanase.demo.springbootAIO.Repository;

import org.springframework.data.repository.CrudRepository;
import tanase.demo.springbootAIO.Model.Spaceship;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SpaceshipRepository extends CrudRepository<Spaceship, Integer> { }