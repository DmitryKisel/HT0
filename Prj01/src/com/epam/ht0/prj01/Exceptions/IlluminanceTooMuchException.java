package com.epam.ht0.prj01.Exceptions;

/**
 * This exception prints message when illumination in the room more than LIGHT_UP_LIMIT
 */
public class IlluminanceTooMuchException extends Exception {
    public IlluminanceTooMuchException(String message){
        super(message);
    }

}
