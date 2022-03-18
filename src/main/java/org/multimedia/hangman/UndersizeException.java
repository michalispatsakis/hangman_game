package org.multimedia.hangman;



public class UndersizeException extends DictionaryException {
    public UndersizeException ()  {
        super("Dictionaries must have at least 20 words");
    }
}
