package com.nighthawk.spring_portfolio.mvc.quotes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import static javax.persistence.FetchType.EAGER;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String quote;
    private String author;

    @NotEmpty
    private String topic;

    public Quote(String quote, String author, String topic) {
        this.quote = quote;
        this.author = author;
        this.topic = topic;
    }

    public static Quote[] init() {
        Quote q1 = new Quote();
        q1.setQuote("A real friend is one who walks in when the rest of the world walks out.");
        q1.setAuthor("Walter Winchell");
        q1.setTopic("Loyalty");

        Quote q2 = new Quote();
        q2.setQuote("Growing apart doesn’t change the fact that for a long time we grew side by side; our roots will always be tangled. I’m glad for that.");
        q2.setAuthor("Ally Condie");
        q2.setTopic("Loyalty");

        Quote q3 = new Quote();
        q3.setQuote("Let us be grateful to the people who make us happy; they are the charming gardeners who make our souls blossom.");
        q3.setAuthor("Marcel Proust");
        q3.setTopic("Thankfulness");

        Quote quotes[] = {q1, q2, q3};
        return(quotes);
    }

    public static void main(String[] args) {
        Quote quotes[] = init();

        for( Quote quote : quotes) {
            System.out.println(quote);
        }
    }
/*
    public long getId() {
        return id;
    }
    public void setID(long id) {
        this.id = id;
    }

    @Column(name = "quote", nullable = false)
    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Column(name = "author", nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "topic", nullable = false)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Quote [id=" + id + ", quote=" + quote + ", author=" + author + ", topic=" + topic + "]";
    }


    // starting quotes
   /*  public static String[] init() {
        final String[] quotesArray = {
            "“A real friend is one who walks in when the rest of the world walks out.” — Walter Winchell",
            "“A friend is someone who understands your past, believes in your future, and accepts you just the way you are.” — Unknown",
            "“Growing apart doesn’t change the fact that for a long time we grew side by side; our roots will always be tangled. I’m glad for that.” — Ally Condie",
            "“Let us be grateful to the people who make us happy; they are the charming gardeners who make our souls blossom.” — Marcel Proust",
            "“Lots of people want to ride with you in the limo, but what you want is someone who will take the bus with you when the limo breaks down.” — Oprah Winfrey",
            "“In the sweetness of friendship let there be laughter, for in the dew of little things the heart finds its morning and is refreshed.” — Khalil Gibran",
            "“It’s not what we have in life, but who we have in our life that matters.” — Unknown",
            "“To the world you may be just one person, but to one person you may be the world.” — Dr. Seuss",
            "“A friend is one who overlooks your broken fence and admires the flowers in your garden.” — Unknown",
            "“A friend who understands your tears is much more valuable than a lot of friends who only know your smile.” — Unknown",
            "“We weren’t sisters [or brothers] by birth, but we knew from the start…fate brought us together to be sisters [or brothers] by heart.” — Unknown",
            "“A friend is one of the best things you can be and the greatest things you can have.” — Sarah Valdez",
            "“Best friends are the people in your life who make you laugh louder, smile brighter and live better.” — Unknown",
            "“Time doesn’t take away from friendship, nor does separation.” — Tennessee Williams",
            "“When the world is so complicated, the simple gift of friendship is within all of our hands.” — Maria Shriver",
            "“There’s not a word yet for old friends who’ve just met.” — Jim Henson",
            "“A single rose can be my garden…a single friend, my world.” — Leo Buscaglia",
            "Friends are those rare people who ask how we are and then wait to hear the answer.” — Ed Cunningham"
        };
        return quotesArray;
    }*/


}

