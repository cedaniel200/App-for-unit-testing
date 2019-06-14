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
    public void mustBeSuccessfulWhenCreateMethodReturnAnId(){
        long expected = 0;

        long idActual = phraseDomain.create("a phrase", "anonymous");

        Assert.assertEquals(expected, idActual);
    }

    @Test
    public void mustBeSuccessfulWhe(){
        long id = phraseDomain.create("a phrase", "anonymous");

        Phrase phrase = phraseDomain.getPhrase(id);

        Assert.assertEquals(id, phrase.getId());
        Assert.assertTrue("a phrase".equalsIgnoreCase(phrase.getPhrase()));
    }
}
