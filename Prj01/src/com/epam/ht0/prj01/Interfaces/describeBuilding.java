package com.epam.ht0.prj01.Interfaces;

import com.epam.ht0.prj01.Entity.Building;
import com.epam.ht0.prj01.Entity.Furniture;
import com.epam.ht0.prj01.Entity.Lamp;
import com.epam.ht0.prj01.Entity.Room;

import java.util.List;

public interface describeBuilding {
    void describe (Building building);
    void squareDescribe(Room room);
    void lampDescribe(List<Lamp> lamps);
    void furnitureDescribe(List<Furniture> furniture);
}
