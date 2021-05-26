package ihep.daq.nevt.webservice.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
        GearData gearData = new GearData();
        gearData.setGear(document.getInteger("gear"));
        double temperature = document.getDouble("temperature");
        if (data.contains(gearData)) {
            gearData.incrementHeightTotal(1);
            for (TempHeight tempHeight : gearData.getData()){
                if(temperature >= tempHeight.getFromTemp() && temperature <= tempHeight.getToTemp()) {
                    tempHeight.incrementHeight(1);
                }
                return;
            }
            return;
        }
        count++;
        for (TempHeight tempHeight : gearData.getData()) {
            if(temperature >= tempHeight.getFromTemp() && temperature <= tempHeight.getToTemp()) {
                tempHeight.setHeight(1);
            }
        }
        data.add(gearData);
    }
}
