package com.cedaniel200.practice.domain.phrase;

import com.cedaniel200.practice.domain.persistence.phrase.FakePhraseDao;
import com.cedaniel200.practice.model.Phrase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhraseDomainDefaultTest {

    private PhraseDomain phraseDomain;

    @Before
    public void setup(){
        phraseDomain = new PhraseDomainDefault(new FakePhraseDao());
    }

    @Test
    public void mustBeSuccessfulWhenCreateMethodReturnTheCorrectId(){
        long expected = 0;

        long idActual = phraseDomain.create("a phrase", "Cesar");

        Assert.assertEquals(expected, idActual);
        Assert.assertEquals("Cesar", phraseDomain.getPhrase(idActual).getAuthor());
    }

    @Test
    public void mustBeSuccessfulWhenCreateMethodAssignsAnonymousToAnEmptyAuthor(){
        long expected = 0;

        long idActual = phraseDomain.create("a phrase", "");

        Assert.assertEquals("anonymous", phraseDomain.getPhrase(idActual).getAuthor());
    }

    @Test
    public void mustBeSuccessfulWhenCreateMethodAssignsAnonymousToANullAuthor(){
        long expected = 0;

        long idActual = phraseDomain.create("a phrase", null);

        Assert.assertEquals("anonymous", phraseDomain.getPhrase(idActual).getAuthor());
    }

    @Test
    public void mustBeSuccessfulWhenGetPhraseMethodReturnAPhrase(){
        long id = phraseDomain.create("a phrase", "");

        Phrase phrase = phraseDomain.getPhrase(id);

        Assert.assertEquals(id, phrase.getId());
        Assert.assertTrue("a phrase".equalsIgnoreCase(phrase.getPhrase()));
    }

}
