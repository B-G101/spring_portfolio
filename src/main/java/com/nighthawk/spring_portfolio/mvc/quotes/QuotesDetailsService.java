package com.nighthawk.spring_portfolio.mvc.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.security.SecureRandom;

import java.util.List;

import javax.security.auth.message.MessagePolicy.TargetPolicy;

@Service
@Transactional
public class QuotesDetailsService {
    @Autowired  // Inject QuoteJpaRepository
    private QuotesJpaRepository quoteJpaRepository;

    /* Quote Section */

    public  List<Quote>listAll() {
        return quoteJpaRepository.findAllByOrderByQuoteAsc();
    }

    // custom query to find match to name or email
    public  List<Quote>list(String author, String topic) {
        return quoteJpaRepository.findByAuthorContainingIgnoreCaseOrTopicContainingIgnoreCase(author, topic);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<Quote>listLike(String term) {
        return quoteJpaRepository.findByAuthorContainingIgnoreCaseOrTopicContainingIgnoreCase(term, term);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<Quote>listLikeNative(String term) {
        String like_term = String.format("%%%s%%",term);  // Like required % rappers
        return quoteJpaRepository.findByLikeTermNative(like_term);
    }

    public void save(Quote quote) {
        quoteJpaRepository.save(quote);
    }

    public Quote get(long id) {
        return (quoteJpaRepository.findById(id).isPresent())
                ? quoteJpaRepository.findById(id).get()
                : null;
    }

    public Quote getByAuthor(String author) {
        return (quoteJpaRepository.findByAuthor(author));
    }

    public void delete(long id) {
        quoteJpaRepository.deleteById(id);
    }

   /*  public List<Quote> getRandomQuotes(List<Quote> quotes) {
        List<Quote> randomQuotes = new ArrayList<>();
        List<Quote> copy = new ArrayList<>(quotes);
    
        SecureRandom rand = new SecureRandom();
        randomQuotes.add(copy.get(rand.nextInt(copy.size())));
     
        return randomQuotes;
    } */


}
