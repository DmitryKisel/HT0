package com.epam.ht0.prj01;

import com.epam.ht0.prj01.Exceptions.IlluminanceTooMuchException;
import com.epam.ht0.prj01.Exceptions.SpaceUsageTooMuchException;
import com.epam.ht0.prj01.Interfaces.Manage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws SpaceUsageTooMuchException, IOException, IlluminanceTooMuchException {
        Manage manager = new Manager();
        manager.start();
    }
}
