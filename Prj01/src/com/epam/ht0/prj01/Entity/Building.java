package com.epam.ht0.prj01.Entity;

import com.epam.ht0.prj01.Interfaces.describeBuilding;

import java.util.ArrayList;
import java.util.List;


public class Building implements describeBuilding {
    //This constant determines the up limit of illumination
    public static final int LIGHT_UP_LIMIT = 4000;
    //This constant determines the down limit of illumination;
    public static final int LIGHT_DOWN_LIMIT = 300;
    //This constant determines level of illumination given by one window;
    public static final int WINDOWS_LIGHT = 700;
    //This constant determines the coefficient of space of room usability;
    public static final double SPACE_USAGE_PERCENT = 0.7;
    private String name;
    private List<Room> rooms;


    public Building(String name) {
        this.name = name;
        rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


    public void addRoom(String roomName, double square, int windows) {
        Room room = new Room(roomName, square, windows);
        rooms.add(room);
    }

    /**
     * This method searches the room by name in the rooms list and returns it or message if this room
     * is absent in the list
     *
     * @param name
     * @return
     */
    public Room getRoom(String name) {
        int count = 0;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomName().equals(name))
                return rooms.get(i);
            else {
                count++;
            }
        }
        if (count == rooms.size())
            System.out.println("Комната с таким названием не найдена");
        return null;
    }

    @Override
    /**
     * This method prints information about building in determine view
     */
    public void describe(Building building) {
        System.out.println(building.getName());
        for (int i = 0; i < building.rooms.size(); i++) {
            String wind = "";
            System.out.println(" " + building.rooms.get(i).getRoomName());
            int j = building.rooms.get(i).getWindowsNumber();

            if (j == 0) {
                System.out.print("   Освещенность = " + building.rooms.get(i).getLight() +
                        "(" + "окон нет");
            } else {
                if (j < 11 || j > 19) {
                    if (j % 10 == 1) {
                        wind = "окно";
                    } else if (j % 10 == 2 || j % 10 == 3 || j % 10 == 4) {
                        wind = "окна";
                    } else wind = "окон";

                } else wind = "окон";

                System.out.print("   Освещенность = " + building.rooms.get(i).getLight() +
                        "(" + building.rooms.get(i).getWindowsNumber() + " " + wind + " по 700 лк");
            }

            lampDescribe(building.rooms.get(i).getLamps());
            if (building.rooms.get(i).getLight() <= LIGHT_DOWN_LIMIT) {
                System.out.println("Обращаем ваше внимание, что в комнате недостаточный уровень освещенности");
                System.out.println("Минимальный уровень освещенности должен составлять " + LIGHT_DOWN_LIMIT + " лк");
            }

            squareDescribe(building.rooms.get(i));

            furnitureDescribe(building.rooms.get(i).getFurniture());
            System.out.println();
        }
    }

    /**
     * This method prints information about room square usage
     *
     * @param room
     */
    @Override
    public void squareDescribe(Room room) {

        if (room.getUsedSquare() == 0) {
            System.out.println("   Площадь = " + room.getSquare() + (" м^2 (свободно 100%)"));
        } else {
            if (room.getUsedSquare() % (int) room.getUsedSquare() == 0) {
                System.out.println("   Площадь = " + room.getSquare() + " м^2 (занято "
                        + (int) room.getUsedSquare() + " м^2, гарантированно свободно "
                        + (int) (room.getSquare() - room.getUsedSquare()) + " м^2 или "
                        + (int)  (100 - (room.getUsedSquare() / room.getSquare()) * 100)+ "% площади)");
            } else {
                System.out.println("   Площадь = " + room.getSquare() + " м^2 (занято "
                        + (int) room.getUsedSquare() + "-" + (int) (room.getUsedSquare() + 1) + " м^2, гарантированно свободно "
                        + (int)Math.round (room.getSquare() - room.getUsedSquare() -1) + " м^2 или "
                        + (int) (100 -(room.getUsedSquare() / room.getSquare()) * 100) + "% площади)");
            }
        }
    }

    /**
     * This method prints information about illumination in the room
     *
     * @param lamps
     */
    @Override
    public void lampDescribe(List<Lamp> lamps) {

        if (lamps.isEmpty()) {
            System.out.println(")");
        } else if (lamps.size() == 1) {
            System.out.println(", лампочка " + lamps.get(0).getIllumination() + " лк)");
        } else {
            for (int i = 0; i < lamps.size(); i++) {
                if (i == lamps.size() - 1)
                    System.out.println(" и " + lamps.get(i).getIllumination() + " лк)");
                else if (i == 0) {
                    System.out.print(", лампочки " + lamps.get(i).getIllumination() + " лк, ");
                } else {
                    System.out.print(lamps.get(i).getIllumination() + " лк, ");
                }
            }
        }
    }

    /**
     * This method prints information about furniture in th room
     *
     * @param furniture
     */
   @Override
    public void furnitureDescribe(List<Furniture> furniture) {
        if (furniture.isEmpty()) {
            System.out.println("    Мебели нет");
        } else {
            System.out.println("   Мебель:");
            for (int i = 0; i < furniture.size(); i++) {
                int squareRounded = (int) furniture.get(i).getFurnitureSquare();
                if (furniture.get(i).getFurnitureSquare() == (double) squareRounded) {
                    System.out.println("    " + furniture.get(i).getFurnitureName() + " (площадь "
                            + furniture.get(i).getFurnitureSquare() + " м^2)");
                } else {
                    System.out.println("    " + furniture.get(i).getFurnitureName() + " (площадь от "
                            + squareRounded + " м^2 до " + (squareRounded + 1) + " м^2)");
                }
            }
        }
    }
}
