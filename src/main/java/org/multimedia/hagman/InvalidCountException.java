package org.multimedia.hagman;



public class InvalidCountException extends DictionaryException {
    public InvalidCountException ()  {
        super("Dictionaries have unique words");
    }
}
