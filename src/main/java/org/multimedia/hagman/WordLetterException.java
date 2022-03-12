package org.multimedia.hagman;


public class WordLetterException extends DictionaryException {
    public WordLetterException ()  {
        super("Dictionary words must contain only uppercase letter from A-Z");
    }
}
