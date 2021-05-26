package ihep.daq.nevt.webservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Data201PO {
    long num;
    double gear;
    double temp;
    double vibrate_avg;
    double vibrate_max;
    double vibrate_min;
    double voltage_avg;
    double voltage_max;
    double voltage_min;
}
