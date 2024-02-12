package tanase.demo.springbootAIO.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tanase.demo.springbootAIO.Repository.SpaceshipRepository;
import tanase.demo.springbootAIO.Model.Spaceship;
import tanase.demo.springbootAIO.Service.SpaceshipService;
import java.util.List;



@Controller // This means that this class is a Controller
@RequestMapping(path="/spaceship") // This means URL's start with /demo (after Application path)
public class SpaceshipController {

    private final SpaceshipService spaceshipService;

    @Autowired
    private SpaceshipRepository spaceshipRepository;
    public SpaceshipController(SpaceshipService spaceshipService) {
        this.spaceshipService = spaceshipService;
    }


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

    @GetMapping(path="/weight")  //esto encuentra todas naves por peso, la mas pesada sera primera
    public @ResponseBody Iterable<Spaceship> findALLSpaceshipByWeightDescending() {
        return spaceshipService.findAllSpaceshipsOrderedByWeight();
    }

    @GetMapping(path="/filterWeightLessThan/{weight}") //esto encuentra naves mas ligeras del peso indicado
    public @ResponseBody Iterable<Spaceship> getSpaceshipLighterThan(@PathVariable int weight) {
        return spaceshipRepository.findByWeightLessThan(weight);
    }

    @GetMapping(path="/filterWeight/{weight}") //esto encuentra naves por peso
    public @ResponseBody Iterable<Spaceship> getSpaceshipByWeight(@PathVariable int weight) {
        return spaceshipRepository.findByWeightEquals(weight);
    }

    @GetMapping(path="/filterByAlphabeth") //esto pondra naves por orden alfabetico
    public @ResponseBody Iterable<Spaceship> getSpaceshipByAlph() {
        return  spaceshipRepository.findALLByOrderByName();
    }

}



