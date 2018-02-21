package com.epam.ht0.prj01.Interfaces;

import com.epam.ht0.prj01.Entity.Furniture;
import com.epam.ht0.prj01.Entity.Lamp;
import com.epam.ht0.prj01.Exceptions.IlluminanceTooMuchException;
import com.epam.ht0.prj01.Exceptions.SpaceUsageTooMuchException;

public interface AddItem {
    void add(Furniture unit) throws SpaceUsageTooMuchException;
    void add(Lamp lamp) throws IlluminanceTooMuchException;
}
