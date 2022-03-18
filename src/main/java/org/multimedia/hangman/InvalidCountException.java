package org.multimedia.hangman;



public class InvalidCountException extends DictionaryException {
    public InvalidCountException ()  {
        super("Dictionaries have unique words");
    }
}
