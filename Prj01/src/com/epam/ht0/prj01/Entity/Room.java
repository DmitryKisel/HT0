package com.epam.ht0.prj01.Entity;

import com.epam.ht0.prj01.Exceptions.IlluminanceTooMuchException;
import com.epam.ht0.prj01.Exceptions.SpaceUsageTooMuchException;
import com.epam.ht0.prj01.Interfaces.AddItem;

import java.util.ArrayList;
import java.util.List;

import static com.epam.ht0.prj01.Entity.Building.LIGHT_UP_LIMIT;
import static com.epam.ht0.prj01.Entity.Building.SPACE_USAGE_PERCENT;
import static com.epam.ht0.prj01.Entity.Building.WINDOWS_LIGHT;

public class Room implements AddItem{
    private String roomName;
    private double square;
    private int windowsNumber;

    private int light;
    private double usedSquare;
    private List<Lamp> lamps;
    private List<Furniture> furniture;

    public Room(String roomName, double square, int windowsNumber)  {
        this.roomName = roomName;
        this.square = square;
        this.windowsNumber = windowsNumber;
        usedSquare = 0;
        light = windowsNumber * WINDOWS_LIGHT;
        lamps = new ArrayList<>();
        furniture = new ArrayList<>();
    }

    /**
     * This methods takes lamp as argument and add it to the lamps list
     * @param lamp
     * @throws IlluminanceTooMuchException
     */
    @Override
    public void add(Lamp lamp) throws IlluminanceTooMuchException {
        String message = "Предел освещенности превышен. Максимально допустимое значение " + LIGHT_UP_LIMIT + " люкс";
        if (lamp.getIllumination() + light > LIGHT_UP_LIMIT) throw new IlluminanceTooMuchException(message);
        else {
            light += lamp.getIllumination();
            lamps.add(lamp);
        }
    }

    /**
     * This methods takes furniture as argument and add it to the lamps list
     * @param unit
     * @throws SpaceUsageTooMuchException
     */
    @Override
    public void add(Furniture unit) throws SpaceUsageTooMuchException {
       String message = "В комнате нет свободного места, используемая площадь в комнате должна составлять "
               + SPACE_USAGE_PERCENT + " от общей площади";
        if (usedSquare >= SPACE_USAGE_PERCENT * square) throw new SpaceUsageTooMuchException(message);
        else {
            usedSquare += unit.getFurnitureSquare();
            furniture.add(unit);
        }
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public int getWindowsNumber() {
        return windowsNumber;
    }

    public void setWindowsNumber(int windowsNumber) {
        this.windowsNumber = windowsNumber;
    }

    public int getLight() {
        return light;
    }

    public double getUsedSquare() {
        return usedSquare;
    }

    public List<Lamp> getLamps() {
        return lamps;
    }

    public List<Furniture> getFurniture() {
        return furniture;
    }
}


