package ihep.daq.nevt.webservice.model;


import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class TempData {
    private double fromTemp;
    private double toTemp;
    private double height;
    private List<GearHeight> data;

    public TempData(double fromTemp, double toTemp) {
        this.data = new LinkedList<>();
        this.fromTemp = fromTemp;
        this.toTemp = toTemp;
    }

    public void incrementHeight(double h) {
        height += h;
    }

    public void addGearHeight(int gear, int height) {
        data.add(new GearHeight(gear, height));
    }
}

@Getter
@Setter
class GearHeight {
    private int gear;
    private double height;

    public GearHeight(int gear, float height) {
        this.gear = gear;
        this.height = height;
    }

    public void incrementHeight(double h) {
        height += h;
    }
}
