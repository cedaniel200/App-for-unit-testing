package com.cedaniel200.practice.domain.persistence.phrase;

import com.cedaniel200.practice.model.Phrase;
import com.cedaniel200.practice.persistence.phrase.PhraseDao;

import java.util.ArrayList;
import java.util.List;

public class FakePhraseDao implements PhraseDao {

    private List<Phrase> db;

    public FakePhraseDao() {
        db = new ArrayList<>();
    }

    @Override
    public long create(Phrase phrase) {
        db.add(phrase);
        return db.size() - 1;
    }

    @Override
    public Phrase findById(long id) {
        return db.get((int)id);
    }
}
