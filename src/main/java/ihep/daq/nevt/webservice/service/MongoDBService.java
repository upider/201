package ihep.daq.nevt.webservice.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import ihep.daq.nevt.webservice.model.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDBService {
    private final MongoDBManager mongoDBManager;

    public MongoDBService(MongoDBManager mongoDBManager) {
        this.mongoDBManager = mongoDBManager;
    }

    public Data201VO getData(long fromTime, long toTime, double fromTemp, double toTemp, int fromGear, int toGear, int pageIndex, int numPerPage) {
        String dbName = "201";
        String collectionName = "data";
        MongoTemplate mongoTemplate = mongoDBManager.get(dbName);
        MongoCollection<Document> collection = mongoTemplate.getCollection(collectionName);

        List<Bson> datas = new ArrayList<>();
        datas.add(Filters.gt("time", fromTime));
        datas.add(Filters.lt("time", toTime));
        datas.add(Filters.gt("temperature", fromTemp));
        datas.add(Filters.lt("temperature", toTemp));
        datas.add(Filters.gt("gear", fromGear));
        datas.add(Filters.lt("gear", toGear));
        Bson filter = Filters.and(datas);

        int skip = numPerPage*(pageIndex-1);

        long totalRecord = collection.countDocuments();

        FindIterable<Document> findIterable = collection.find(filter).skip(skip).limit(numPerPage);

        Data201VO data201VO = new Data201VO();
        Table table = new Table();
        table.setTotalElements(totalRecord);
        table.setTotalPages(Math.round(totalRecord / (float) numPerPage));
        Stat stat = new Stat(0, Float.MIN_VALUE, Float.MAX_VALUE);
        float cnt = 0;
        float total = 0;
        GraphGear graphGear = new GraphGear();
        GraphTemp graphTemp = new GraphTemp();

        for (Document doc : findIterable) {
            cnt++;
            total += doc.getDouble("vibrate_avg");
            stat.setVibrate_max(Math.max(stat.getVibrate_max(), doc.getDouble("vibrate_max")));
            stat.setVibrate_max(Math.min(stat.getVibrate_min(), doc.getDouble("vibrate_min")));

            table.addData(doc);
            graphGear.addGearData(doc);
            graphTemp.addTempData(doc);
        }

        graphGear.calculateHeight();
        graphTemp.calculateHeight();
        stat.setVibrate_avg(total / cnt);
        data201VO.setGraphGear(graphGear);
        data201VO.setStat(stat);
        data201VO.setGraphTemp(graphTemp);
        data201VO.setTable(table);

        return data201VO;
    }
}
