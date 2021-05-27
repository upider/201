package ihep.daq.nevt.webservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Objects;
import java.util.List;

@Getter
@Setter
public class GearData {
    private int gear;
    private double heightTotal;
    private List<TempHeight> data;

    public GearData() {
        this.data = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            TempHeight tempHeight = new TempHeight(i * 10 , (i + 1)*10 - 1);
            this.data.add(tempHeight);
        }
    }

    public void incrementHeightTotal(double h) {
        this.heightTotal += h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GearData gearData = (GearData) o;
        return gear == gearData.gear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gear);
    }
}

@Getter
@Setter
class TempHeight {
    private double fromTemp;
    private double toTemp;
    private double height;

    public TempHeight() { }

    public TempHeight(double fromTemp, double toTemp) {
        this.fromTemp = fromTemp;
        this.toTemp = toTemp;
    }

    public void incrementHeight(double h) {
        this.height += h;
    }
}
