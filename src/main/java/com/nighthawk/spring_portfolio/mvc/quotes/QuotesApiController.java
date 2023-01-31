package com.nighthawk.spring_portfolio.mvc.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/quotes")  // all requests in file begin with this URI
public class QuotesApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily for Database CRUD operations
    @Autowired
    private QuotesJpaRepository repository;

    /* GET List of Quotes
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Quotes>> getQuotes() {
        // ResponseEntity returns List of Quotes provide by JPA findAll()
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<Quotes> setLike(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<Quotes> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Quotes quote = optional.get();  // value from findByID
            quote.setHaha(quote.getHaha()+1); // increment value
            repository.save(quote);  // save entity
            return new ResponseEntity<>(quote, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }

    /* Update Jeer
     */
    @PutMapping("/jeer/{id}")
    public ResponseEntity<Quotes> setJeer(@PathVariable long id) {
        Optional<Quotes> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Quotes quote = optional.get();
            quote.setBoohoo(quote.getBoohoo()+1);
            repository.save(quote);
            return new ResponseEntity<>(quote, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
