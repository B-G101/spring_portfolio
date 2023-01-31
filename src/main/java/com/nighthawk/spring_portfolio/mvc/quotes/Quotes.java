package com.nighthawk.spring_portfolio.mvc.quotes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data  // Annotations to simplify writing code (ie constructors, setters)
@NoArgsConstructor
@AllArgsConstructor
@Entity // Annotation to simplify creating an entity, which is a lightweight persistence domain object. Typically, an entity represents a table in a relational database, and each entity instance corresponds to a row in that table.
public class Quotes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String quote;

    private int haha;
    private int boohoo;

    // starting quotes
    public static String[] init() {
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
    }
}
