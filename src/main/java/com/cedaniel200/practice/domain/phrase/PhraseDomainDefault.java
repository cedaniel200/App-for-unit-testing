package com.cedaniel200.practice.domain.phrase;

import com.cedaniel200.practice.model.Phrase;
import com.cedaniel200.practice.persistence.phrase.PhraseDao;

public class PhraseDomainDefault implements PhraseDomain {

    private PhraseDao phraseDao;

    public PhraseDomainDefault(PhraseDao phraseDao) {
        this.phraseDao = phraseDao;
    }

    @Override
    public long create(String phrase, String author) {
        Phrase phrase1 = new Phrase(phrase, author);
        return phraseDao.create(phrase1);
    }

    @Override
    public Phrase getPhrase(long idPhrase) {
        return phraseDao.findById(idPhrase);
    }
}
