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
            TempHeight tempHeight = new TempHeight(i * 10 , (i + 1)*10);
        }
    }

    public void incrementHeightTotal(int h) {
        heightTotal += h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GearData gearData = (GearData) o;
        return gear == gearData.gear &&
                Objects.equals(data, gearData.data);
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

    public TempHeight(double fromTemp, double toTemp) {
        this.fromTemp = fromTemp;
        this.toTemp = toTemp;
    }

    public void incrementHeight(double h) {
        height += h;
    }
}
