package ihep.daq.nevt.webservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Getter
@Setter
@Slf4j
public class GraphGear {
    private int count = 0;
    private Set<GearData> data;

    public GraphGear() {
        this.data = new HashSet<>();
    }

    public void calculateHeight() {
        for(GearData gearData : data) {
            double totalHeight = gearData.getHeightTotal();
            for(TempHeight tempHeight : gearData.getData()) {
                tempHeight.setHeight(tempHeight.getHeight() / totalHeight);
            }
        }
    }

    public void addGearData(Document document) {
        double temperature = document.getDouble("temperature");
        log.info("temperature = " + temperature);
        int gear = document.getInteger("gear");
        Iterator<GearData> it = this.data.iterator();
        while(it.hasNext()) {
            GearData gearData = it.next();
            if(gearData.getGear() == gear) {
                gearData.incrementHeightTotal(1);
                Iterator<TempHeight> it2 = gearData.getData().iterator();
                while(it2.hasNext()) {
                    TempHeight tempHeight = it2.next();
                    if(temperature >= tempHeight.getFromTemp() && temperature <= tempHeight.getToTemp()) {
                        tempHeight.incrementHeight(1);
                        return;
                    }
                }
            }
        }

        count++;
        GearData gearData = new GearData();
        gearData.setGear(document.getInteger("gear"));
        gearData.setHeightTotal(1);
        for (TempHeight tempHeight : gearData.getData()) {
            if(temperature >= tempHeight.getFromTemp() && temperature <= tempHeight.getToTemp()) {
                tempHeight.incrementHeight(1);
            }
        }
        data.add(gearData);
    }
}
