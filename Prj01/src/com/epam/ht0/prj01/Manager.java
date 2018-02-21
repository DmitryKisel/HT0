package com.epam.ht0.prj01;

import com.epam.ht0.prj01.Entity.Building;
import com.epam.ht0.prj01.Entity.Furniture;
import com.epam.ht0.prj01.Entity.Lamp;
import com.epam.ht0.prj01.Exceptions.IlluminanceTooMuchException;
import com.epam.ht0.prj01.Exceptions.SpaceUsageTooMuchException;
import com.epam.ht0.prj01.Interfaces.Manage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static com.epam.ht0.prj01.Entity.Building.LIGHT_UP_LIMIT;
import static com.epam.ht0.prj01.Entity.Building.SPACE_USAGE_PERCENT;

/**
 * This class makes simplest dialog interface for testing made methods
 */
public class Manager implements Manage {

    private String exitCase = "Y";
    private int menuCase = 99;   // Value of menuCase allows to navigate in console menu. "99" is unreached value.
    //So this value used as transitional status for step back in menu
    private int roomsNumber;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String line; // This parameter made for incoming from console string
    Building building;
    String message;

    @Override
    /**
     * This method runs application and get basic information about building
     */
    public void start() throws IOException, NullPointerException,
            SpaceUsageTooMuchException, IlluminanceTooMuchException {

        String buildingName;

        do {
            System.out.println("Вы хотите создать новое здание? Если да - нажмите любую клавишу и ввод, если нет - N и ввод");
            line = reader.readLine();

            if (line.equalsIgnoreCase("N")) {
                exitCase = "N";
                break;
            } else {
                System.out.println("Введите название здания");
                buildingName = reader.readLine();
                Building buildingNew = new Building(buildingName);
                building = roomsInput(buildingNew);
            }

            do {
                menuCase = addingInRoomMenu();
                if (menuCase == 0) {
                    exitCase = "N";
                    System.out.println("Работа с программой   завершена");
                    break;
                }

                switch (menuCase) {
                    case 1: {
                        addingLight();
                        menuCase = 99;
                        break;
                    }

                    case 2: {
                        addingFurniture();
                        menuCase = 99;
                        break;
                    }

                    case 3: {
                        building.describe(building);
                        break;
                    }

                    case 0: {
                        break;
                    }
                }
            } while (menuCase != 0);

        } while (!exitCase.equals("N"));
    }

    @Override
    /**
     * This method run part of dialog menu for adding furniture in to the room
     */
    public void addingFurniture() {
        String roomName = null;
        double furnitureSquare = 0;
        String furnitureName = null;


        do {
            System.out.println("Введите название комнаты в которую вы хотите добавить мебель");
            try {
                roomName = reader.readLine();
            } catch (IOException e) {
                System.out.println("Введено неверное значение");
            }

            try {
                if (!building.getRoom(roomName).getRoomName().equals(roomName)) {
                    menuCase = 99;
                }
                else {
                    System.out.println("В настоящее время в комнате \"" + roomName + "\" площадью " +
                            building.getRoom(roomName).getSquare() + "м^2,  занято " +
                            building.getRoom(roomName).getUsedSquare() + " м^2");

                    menuCase = 0;
                }
                } catch (NullPointerException e) {
                menuCase = 99;
            }


        } while (menuCase != 0);

        do {
            if (building.getRoom(roomName).getUsedSquare() >=
                    SPACE_USAGE_PERCENT * building.getRoom(roomName).getSquare())
                try {
                    message = "В комнате нет свободного места, используемая площадь в комнате составляет "
                            + building.getRoom(roomName).getUsedSquare() + " м^2, общая площадь комнаты "
                            + building.getRoom(roomName).getSquare() + " м^2";
                    throw new SpaceUsageTooMuchException(message);
                } catch (SpaceUsageTooMuchException e) {
                    menuCase = 99;
                }

            else {

                System.out.println("1. Для добавление предмета мебели нажмите 1 ");
                System.out.println("0. Для выхода нажмите 0 ");

                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    System.out.println("Введено неверное значение");
                }

                if (checkInput(line, "[0 1]")) {
                    menuCase = Integer.parseInt(line) + 20;
                } else {
                    break;
                }
                switch (menuCase) {
                    case 21: {
                        try {
                            System.out.println("Введите название предмета мебели");

                            try {
                                furnitureName = reader.readLine();
                            } catch (IOException e) {
                                System.out.println("Введено неверное значение");
                            }

                            System.out.println("Введите занимаемую им площадь");

                            try {
                                line = reader.readLine();
                            } catch (IOException e) {
                                System.out.println("Введено неверное значение");
                            }

                            if (checkInput(line, "\\d+([.]\\d+?)?")) {
                                furnitureSquare = Double.parseDouble(line);
                                menuCase = 0;
                            } else {
                                System.out.println("Введено неверное значение");
                                System.out.println("Осуществите ввод в соотвествии с примером");
                                System.out.println("Пример 1: 1.5");
                                System.out.println("или");
                                System.out.println("Пример 2: 2");
                                menuCase = 99;
                            }

                            if (building.getRoom(roomName).getUsedSquare() + furnitureSquare >=
                                    SPACE_USAGE_PERCENT * building.getRoom(roomName).getSquare()) {
                                System.out.println("В комнает недостаточно свободного места");
                                break;
                            } else {
                                Furniture furnitureUnit = new Furniture(furnitureName, furnitureSquare);
                                building.getRoom(roomName).add(furnitureUnit);
                                System.out.println("Предмебели " + furnitureName + " площадью " + furnitureSquare
                                        + " м^2 успешно добавлен");
                                System.out.println("После добавления предмета мебели используемая площадь в комнате "
                                        + building.getRoom(roomName).getRoomName() + " составляет "
                                        + building.getRoom(roomName).getUsedSquare() + " м^2"
                                        + ", общая площадь составляет " + building.getRoom(roomName).getSquare() + " м^2");
                                menuCase = 99;
                            }

                        } catch (SpaceUsageTooMuchException e) {
                            System.out.println("В комнате нет свободного места, используемая площадь в комнате составляет "
                                    + building.getRoom(roomName).getUsedSquare() + " м^2, общая площадь комнаты "
                                    + building.getRoom(roomName).getSquare() + " м^2");
                            break;
                        }
                        break;
                    }

                    case 20: {
                        menuCase = 0;
                        break;
                    }
                }
            }
        } while (menuCase != 0);
    }


    /**
     * This method adds light pots in to the room and prints message if level of illumination was exceeded
     */
    @Override
    public void addingLight() {
        String roomName = null;
        int light;

        do {
            System.out.println("Введите название комнаты в которой вы хотите повысить уровень освещенности");

            try {
                roomName = reader.readLine();
            } catch (IOException e) {
                System.out.println("Введено неверное значение");
            }

            try {
                if (!building.getRoom(roomName).getRoomName().equals(roomName)) {
                    menuCase = 99;
                }
                else {
                    System.out.println("В настоящее время освещенность в комнате \"" + roomName + "\" равна "
                            + building.getRoom(roomName).getLight());
                     menuCase = 0;

                }
            } catch (NullPointerException e) {
                menuCase = 99;
                break;
            }

        } while (menuCase != 0);


        do {

            if (building.getRoom(roomName).getLight() > LIGHT_UP_LIMIT) {
                message = "Предел освещенности превышен, освещенность в комнате" +
                        " составляет " + building.getRoom(roomName).getLight() + " люкс," +
                        " максимальное значение " + LIGHT_UP_LIMIT + " люкс";
                try {
                    throw new IlluminanceTooMuchException(message);
                } catch (IlluminanceTooMuchException e) {
                    menuCase =99;
                }

            } else {
                System.out.println("1. Для добавление источника света нажмите 1 ");
                System.out.println("0. Для выхода нажмите 0 ");
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    System.out.println("Введено неверное значение");
                }
                if (checkInput(line, "[0 1]")) {
                    menuCase = Integer.parseInt(line) + 10;
                } else {
                    break;
                }

                switch (menuCase) {
                    case 11: {
                        try {
                            System.out.println("Введите освещенность источника света в люксах");
                            try {
                                line = reader.readLine();
                            } catch (IOException e) {
                                System.out.println("Введено неверное значение");
                            }
                            if (checkInput(line, "\\d+")) {
                                light = Integer.parseInt(line);
                                Lamp lamp = new Lamp(light);
                                building.getRoom(roomName).add(lamp);
                                System.out.println("Источник света с совещенностью " + light + " лк. успешно добавлен");
                                System.out.println("После добавления источника света освещенность в комнате "
                                        + building.getRoom(roomName).getRoomName() +
                                        " составляет " + building.getRoom(roomName).getLight() + " люкс");
                                menuCase = 99;
                            } else {
                                System.out.println("Введено неверное значение. Введите количество с использованием цифр");
                                System.out.println("Например: 100");
                            }

                        } catch (IlluminanceTooMuchException e) {
                            System.out.println("Предел освещенности превышен, освещенность в комнате " +
                                    building.getRoom(roomName).getRoomName() +
                                    " составляет " + building.getRoom(roomName).getLight() + " люкс," +
                                    " максимальное значение " + LIGHT_UP_LIMIT + " люкс");
                            break;
                        }
                        break;
                    }
                    case 10: {
                        menuCase = 0;
                        break;
                    }
                }
            }

        } while (menuCase != 0);
    }

    /**
     * This method gets incoming building and adds rooms with parameters. In the end it returns building back.
     *
     * @param building - incoming building
     * @return
     */
    @Override
    public Building roomsInput(Building building) {
        do {
            System.out.println("Введите количество комнат, которое выхотите добавить");
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("Введено неверное значение. Введите количество с использованием цифр");
                System.out.println("Например: 12");
            }
            if (checkInput(line, "[1-9]([0-9]+)?")) {
                roomsNumber = Integer.parseInt(line);
                menuCase = 0;
            } else {
                System.out.println("Введено неверное значение. Введите количество с использованием цифр");
                System.out.println("Например: 12");
                menuCase = 99;
            }
        } while (menuCase != 0);

        for (int i = 1; i <= roomsNumber; i++) {
            String adding;
            String roomName = null;
            double square = 0;
            int windowsNumber = 0;

            if (i % 10 == 3) adding = "-ей";
            else adding = "-ой";
            do {
                System.out.println("Введите название " + i + adding + " комнаты");
                try {
                    roomName = reader.readLine();
                } catch (IOException e) {
                    System.out.println("Введено неверное значение");
                    menuCase = 99;
                }

            } while (menuCase != 0);

            do {
                System.out.println("Введите площадь " + i + adding + " комнаты");
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    System.out.println("Введено неверное значение");
                    menuCase = 99;
                }
                if (checkInput(line, "\\d+([.]\\d+?)?")) {
                    square = Double.parseDouble(line);
                    menuCase = 0;
                } else {
                    System.out.println("Введено неверное значение");
                    System.out.println("Осуществите ввод в соотвествии с примером");
                    System.out.println("Пример 1: 1.94");
                    System.out.println("или");
                    System.out.println("Пример 2: 11");
                    menuCase = 99;
                }
            } while (menuCase != 0);
            do {
                System.out.println("Введите количество окон в " + i + adding + " комнате");
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    System.out.println("Введено неверное значение");
                    menuCase = 99;
                }

                if (checkInput(line, "\\d+")) {
                    windowsNumber = Integer.parseInt(line);
                    menuCase = 0;
                } else {
                    System.out.println("Введено неверное значение");
                    System.out.println("Осуществите ввод в соотвествии с примером");
                    System.out.println("Пример: 5 ");
                    menuCase = 99;
                }

            } while (menuCase != 0);

            building.addRoom(roomName, square, windowsNumber);
        }
        return building;
    }


    /**
     * This method allows to choose the adding the light in to the room, adding the furniture in to the room
     * or outputting information about building
     *
     * @return user choice in parameter menuCase
     */
    @Override
    public int addingInRoomMenu() {
        do {
            System.out.println("1. Для добавления источника света в комнате введите 1");
            System.out.println("2. Для добавления мебели в комнату введите 2");
            System.out.println("3. Для Вывода информации о здании введите 3");
            System.out.println("0. Для выхода из меню введите 0");

            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("Введено неверное значение");
            }
            if (checkInput(line, "[1 2 3 0]")) {
                menuCase = Integer.parseInt(line);
            } else {
                menuCase = 99;
            }
        } while (menuCase == 99);
        return menuCase;
    }

    /**
     * This method checks with regular expressions if input correct or not.
     * And returns true if correct and false if not.
     *
     * @param str     - incoming String
     * @param pattern - incoming pattern
     * @return
     */
    @Override
    public boolean checkInput(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            return false;
        } else {
            return true;
        }
    }
}
