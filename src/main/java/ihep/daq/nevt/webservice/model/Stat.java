package ihep.daq.nevt.webservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class Stat {
    private double vibrate_avg;
    private double vibrate_max;
    private double vibrate_min;

    public Stat(Double vibrate_avg, Double vibrate_max, Double vibrate_min) {
        this.vibrate_avg = vibrate_avg;
        this.vibrate_max = vibrate_max;
        this.vibrate_min = vibrate_min;
    }

    public Stat() {

    }
}
