package tanase.demo.springbootAIO.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tanase.demo.springbootAIO.Repository.SpaceshipRepository;
import tanase.demo.springbootAIO.Model.Spaceship;



import java.util.List;

@Controller // This means that this class is a Controller
@RequestMapping(path="/spaceship") // This means URL's start with /demo (after Application path)
public class SpaceshipController {

    @Autowired
    private SpaceshipRepository spaceshipRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam Integer weight) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Spaceship n = new Spaceship();
        n.setName(name);
        n.setWeight(weight);
        spaceshipRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Spaceship> getAllSpaceships() {
        // This returns a JSON or XML with the Spaceships
        return spaceshipRepository.findAll();
    }

    @GetMapping(path="/weight")
    public @ResponseBody Iterable<Spaceship> findSpaceshipByWeightDescending(@RequestParam Integer weight) {
        return spaceshipRepository.findAllByWeightOrderDesc(weight);
    }


}
