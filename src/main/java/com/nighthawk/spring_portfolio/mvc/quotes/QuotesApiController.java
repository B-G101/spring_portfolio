package com.nighthawk.spring_portfolio.mvc.quotes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nighthawk.spring_portfolio.mvc.quotes.Quote;
import com.nighthawk.spring_portfolio.mvc.quotes.QuotesJpaRepository;

import java.util.List;

@RestController
@RequestMapping("api/quotes")

public class QuotesApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily for Database CRUD operations
    @Autowired
    private QuotesJpaRepository repository;

    /* GET List of Quotes
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/quotes")
    public List<Quote> getAllQuotes() {
        return repository.findAll();

    }

    @GetMapping("/quotes/{id}")
    public ResponseEntity<Quote> getQuotebyId(@PathVariable(value = "id") Long quoteId)
    throws ResourceNotFoundException {
        Quote quote = repository.findById(quoteId)
            .orElseThrow(() -> new ResourceNotFoundException("Quote not found for this Id :: " + quoteId));
        return ResponseEntity.ok().body(quote);
    }

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

}
