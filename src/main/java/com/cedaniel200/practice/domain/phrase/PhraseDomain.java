package com.cedaniel200.practice.domain.phrase;

import com.cedaniel200.practice.model.Phrase;

public interface PhraseDomain {

    long create(String phrase, String author);
    Phrase getPhrase(long idPhrase);

}
