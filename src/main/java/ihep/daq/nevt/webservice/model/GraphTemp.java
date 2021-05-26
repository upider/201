package ihep.daq.nevt.webservice.model;

import lombok.Getter;
import lombok.Setter;

import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class GraphTemp {
    private int count = 10;
    private List<TempData> data;
    private double totalHeight = 0;

    public GraphTemp() {
        this.data = new LinkedList<>();
        for (double i = 0; i < 10; i++) {
            this.data.add(new TempData(i * 10, (i+1)*10));
        }
    }

    public void calculateHeight() {
        for(TempData tempData : data) {
            double totalHeight = tempData.getHeight();
            for(GearHeight gearHeight : tempData.getData()) {
                gearHeight.setHeight(gearHeight.getHeight() / totalHeight);
            }
        }
    }

    public void addTempData(TempData e) {
        data.add(e);
    }

    public void addTempData(Document e) {
        totalHeight += 1;
        double temperature = e.getDouble("temperature");
        int gear = e.getInteger("gear");
        for(TempData tempData : data) {
            if(temperature >= tempData.getFromTemp() && temperature <= tempData.getToTemp()) {
                tempData.incrementHeight(1);
                for(GearHeight gearHeight : tempData.getData()) {
                    if(gear == gearHeight.getGear()) {
                        gearHeight.incrementHeight(1);
                        return;
                    }
                }
                tempData.addGearHeight(gear, 1);
            }
        }
    }
}
