package ihep.daq.nevt.webservice.model;


import lombok.Data;

@Data
public class GetDataRequest {
    long fromTime;
    long toTime;
    double fromTemp;
    double toTemp;
    int fromGear;
    int toGear;
    int pageIndex;
    int numPerPage;
}
