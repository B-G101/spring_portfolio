package com.nighthawk.spring_portfolio.mvc.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// Built using article: https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html
// or similar: https://asbnotebook.com/2020/04/11/spring-boot-thymeleaf-form-validation-example/
@Controller
@RequestMapping("/mvc/quote")
public class QuotesViewController {
    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD
    @Autowired
    private QuotesDetailsService repository;

    @GetMapping("/read")
    public String quote(Model model) {
        List<Quote> list = repository.listAll();
        model.addAttribute("list", list);
        return "quote/read";
    }

    /*  The HTML template Forms and QuoteForm attributes are bound
        @return - template for quote form
        @param - Quote Class
    */
    @GetMapping("/create")
    public String quoteAdd(Quote quote) {
        return "quote/create";
    }

    /* Gathers the attributes filled out in the form, tests for and retrieves validation error
    @param - Quote object with @Valid
    @param - BindingResult object
     */
    @PostMapping("/create")
    public String quoteSave(@Valid Quote quote, BindingResult bindingResult) {
        // Validation of Decorated QuoteForm attributes
        if (bindingResult.hasErrors()) {
            return "quote/create";
        }
        repository.save(quote);
        // Redirect to next step
        return "redirect:/mvc/quote/read";
    }

    @GetMapping("/update/{id}")
    public String quoteUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("quote", repository.get(id));
        return "quote/update";
    }

    @PostMapping("/update")
    public String quoteUpdateSave(@Valid Quote quote, BindingResult bindingResult) {
        // Validation of Decorated QuoteForm attributes
        if (bindingResult.hasErrors()) {
            return "quote/update";
        }
        repository.save(quote);

        // Redirect to next step
        return "redirect:/mvc/quote/read";
    }

    @GetMapping("/delete/{id}")
    public String quoteDelete(@PathVariable("id") long id) {
        repository.delete(id);
        return "redirect:/mvc/quote/read";
    }

    @GetMapping("/search")
    public String quote() {
        return "quote/search";
    }

}