package com.cedaniel200.practice.domain.phrase;

import com.cedaniel200.practice.domain.persistence.phrase.FakePhraseDao;
import com.cedaniel200.practice.model.Phrase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhraseDomainDefaultTest {

    private PhraseDomain phraseDomain;

    @BeforeEach
    void setup(){
        phraseDomain = new PhraseDomainDefault(new FakePhraseDao());
    }

    @Test
    void create_phrase_shouldCreateItAndReturnId(){
        long expected = 0;
        long idActual = phraseDomain.create("a phrase", "Cesar");
        assertEquals(expected, idActual);
        assertEquals("Cesar", phraseDomain.getPhrase(idActual).getAuthor());
    }

    @Test
    void create_phraseWithEmptyAuthor_shouldAssignAnonymous(){
        long idActual = phraseDomain.create("a phrase", "");
        assertEquals("anonymous", phraseDomain.getPhrase(idActual).getAuthor());
    }

    @Test
    void create_phraseWithNullAuthor_shouldAssignAnonymous(){
        long idActual = phraseDomain.create("a phrase", null);
        assertEquals("anonymous", phraseDomain.getPhrase(idActual).getAuthor());
    }

    @Test
    void getPhrase_shouldReturnSavedPhrase(){
        long id = phraseDomain.create("a phrase", "");
        Phrase phrase = phraseDomain.getPhrase(id);
        assertEquals(id, phrase.getId());
        assertTrue("a phrase".equalsIgnoreCase(phrase.getPhrase()));
    }

}
