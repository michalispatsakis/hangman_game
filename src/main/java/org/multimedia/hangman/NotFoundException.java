package org.multimedia.hangman;



public class NotFoundException extends ApiException {
    public NotFoundException (String str){
        super(str);
    }
}