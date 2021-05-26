package ihep.daq.nevt.webservice.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Table {
    private long totalElements;
    private long totalPages;
    private List<Data201PO> data;

    public Table() {
        this.data = new LinkedList<>();
    }

    public void addData(Data201PO e) {
        data.add(e);
    }

    public void addData(Document e) {
        Data201PO data201PO = new Data201PO();
        data201PO.setGear(e.getInteger("gear"));
        data201PO.setNum(e.getInteger("num"));
        data201PO.setTemp(e.getDouble("temperature"));
        data201PO.setVibrate_avg(e.getDouble("vibrate_avg"));
        data201PO.setVibrate_max(e.getDouble("vibrate_max"));
        data201PO.setVibrate_min(e.getDouble("vibrate_min"));
        data201PO.setVoltage_avg(e.getDouble("voltage_avg"));
        data201PO.setVoltage_max(e.getDouble("voltage_max"));
        data201PO.setVoltage_min(e.getDouble("voltage_min"));
        data.add(data201PO);
    }
}
