package org.multimedia.hagman;



public class InvalidRangeException extends DictionaryException {
    public InvalidRangeException() {
        super("Dictionaries must have words with 6 or more letters");
    }
}
