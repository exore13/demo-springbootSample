package tanase.demo.springbootAIO.Controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Controller // This means that this class is a Controller
@RequestMapping(path="/cat") // This means URL's start with /demo (after Application path)
public class CatController {

    @GetMapping("/fact")
    public ResponseEntity<String> handle() {

        Random rand = new Random(System.currentTimeMillis());
        final String uri = "https://cat-fact.herokuapp.com/facts";

        // Call cat-fact API
        System.out.println("Realizando llamada GET a " + uri);

        RestTemplate restTemplate = new RestTemplate();
        String getCallResponse = restTemplate.getForObject(uri, String.class);
        System.out.println("Respuesta recibida:\n" + getCallResponse);


        // Convert GET response to parseable type
        JSONObject factsResponseObject = new JSONObject("{ \"myArray\": " + getCallResponse + "}");
        JSONArray factsResponseArray = factsResponseObject.getJSONArray("myArray");


        // Extract the N random entry from factsResponseArray. { 0 - factsResponseArray.length() }
        JSONObject myFactObject = factsResponseArray.getJSONObject(rand.nextInt(factsResponseArray.length()));
        String myFact = (String) myFactObject.get("text");


        return ResponseEntity.ok().eTag(null).body(myFact);
    }

}
