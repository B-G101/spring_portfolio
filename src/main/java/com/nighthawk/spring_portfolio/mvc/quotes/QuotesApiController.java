package com.nighthawk.spring_portfolio.mvc.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("api/quotes")
public class QuotesApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily for Database CRUD operations
    @Autowired
    private QuotesJpaRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Quote>> getQuote() {
        return new ResponseEntity<>( repository.findAllByOrderByQuoteAsc(), HttpStatus.OK);
    } // to be changed to find a random quote

    /* GET List of Quotes
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable long id) {
        Optional<Quote> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Quote quote = optional.get();  // value from findByID
            return new ResponseEntity<>(quote, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

        /*
    DELETE individual Quote using ID
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Quote> deleteQuote(@PathVariable long id) {
        Optional<Quote> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Quote quote = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(quote, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }

    /*
    POST Aa record by Requesting Parameters from URI
     */
    @PostMapping("/post")
    public ResponseEntity<Object> postQuote(@RequestParam("quote") String quotee,
                                             @RequestParam("author") String author,
                                             @RequestParam("topic") String topic) {

        Quote quote = new Quote(quotee, author, topic);
        repository.save(quote);
        return new ResponseEntity<>("Quote is created successfully", HttpStatus.CREATED);
    }

        /*
    The personSearch API looks across database for partial match to term (k,v) passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> quoteSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<Quote> list = repository.findByAuthorContainingIgnoreCaseOrTopicContainingIgnoreCase(term, term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /* 
    @PostMapping("/quotes/")
    public Quote createQuote(@Valid @RequestBody Quote quote) {
        return repository.save(quote);
    }

    @PutMapping("/quotes/{id}")
    public ResponseEntity<Quote> updateQuote(@PathVariable(value = "id") Long quoteId,
        @Valid @RequestBody Quote quoteDetails) throws ResourceNotFoundException {
        Quote quote = repository.findById(quoteId)
        .orElseThrow(() -> new ResourceNotFoundException("Quote not found for this if :: " + quoteId));
        
        quote.setQuote(quoteDetails.getQuote());
        quote.setAuthor(quoteDetails.getAuthor());
        quote.setTopic(quoteDetails.getTopic());
        final Quote updatedQuote = repository.save(quote);
        return ResponseEntity.ok(updatedQuote);

    }

    @DeleteMapping("/quotes/{id}")
    public Map<String, Boolean> deleteQuote(@PathVariable(value = "id") Long quoteId)
        throws ResourceNotFoundException {
        Quote quote = repository.findById(quoteId)
        .orElseThrow(() -> new ResourceNotFoundException("Quote not found for this id :: " + quoteId));

        repository.delete(quote);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
        }
    */

}
