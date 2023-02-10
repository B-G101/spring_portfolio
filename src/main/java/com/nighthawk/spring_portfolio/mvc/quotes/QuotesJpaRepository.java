package com.nighthawk.spring_portfolio.mvc.quotes;

import com.nighthawk.spring_portfolio.mvc.quotes.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
    

@Repository
public interface QuotesJpaRepository extends JpaRepository<Quote, Long>{

}