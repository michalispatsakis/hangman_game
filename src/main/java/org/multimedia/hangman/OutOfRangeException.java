package org.multimedia.hangman;

public class OutOfRangeException extends ChoiceException {
    public OutOfRangeException (){
        super("Chosen letter position is outside of allowed range");
    }
}
