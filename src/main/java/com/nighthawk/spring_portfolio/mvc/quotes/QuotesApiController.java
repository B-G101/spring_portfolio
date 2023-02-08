package com.nighthawk.spring_portfolio.mvc.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
