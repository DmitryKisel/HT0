package com.epam.ht0.prj01.Interfaces;

import com.epam.ht0.prj01.Entity.Building;
import com.epam.ht0.prj01.Exceptions.IlluminanceTooMuchException;
import com.epam.ht0.prj01.Exceptions.SpaceUsageTooMuchException;

import java.io.IOException;

public interface Manage {


    void start() throws IOException, NullPointerException,
            SpaceUsageTooMuchException, IlluminanceTooMuchException;
    void addingFurniture();
    void addingLight();
    Building roomsInput(Building building);
    int addingInRoomMenu();
    boolean checkInput(String str, String pattern);
}
