package ihep.daq.nevt.webservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Data201VO {
    private Stat stat;
    private Table table;
    private GraphGear graphGear;
    private GraphTemp graphTemp;
}

