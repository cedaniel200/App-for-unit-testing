package com.cedaniel200.practice.persistence.phrase;

import com.cedaniel200.practice.model.Phrase;

public interface PhraseDao {
    long create(Phrase phrase);
    Phrase findById(long id);
}
