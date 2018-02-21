package com.epam.ht0.prj01.Exceptions;

/**
 * This exception prints message when used square more than total square in the room multiplied for SPACE_USAGE_PERCENT
 */
public class SpaceUsageTooMuchException extends Exception {
    public SpaceUsageTooMuchException(String message){
        super(message);
    }
}
