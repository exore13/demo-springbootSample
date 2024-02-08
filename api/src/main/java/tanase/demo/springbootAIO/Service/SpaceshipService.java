package tanase.demo.springbootAIO.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tanase.demo.springbootAIO.Model.Spaceship;
import tanase.demo.springbootAIO.Repository.SpaceshipRepository;

@Service
public class SpaceshipService {

    @Autowired
    private SpaceshipRepository spaceshipRepository;

    public Iterable<Spaceship> findSpaceshipByWeightDescending(Integer weight) {
        Sort sort = Sort.by(Sort.Direction.DESC, "weight");
        return spaceshipRepository.findAllByWeightOrderDesc(weight);
    }
}
