package ihep.daq.nevt.webservice.service;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class MongoDBManager {
    private final Map<String, MongoTemplate> mongoTemplates = new HashMap<>();

    @Value("${spring.data.mongodb.host}")
    String host;

    @Value("${spring.data.mongodb.port}")
    Integer port;

    public MongoTemplate get(String dbName) {
        if(!mongoTemplates.containsKey(dbName)) {
            MongoClient client = MongoClients.create(MongoClientSettings
                    .builder()
                    .applyToClusterSettings(
                            builder ->
                                    builder.hosts(Arrays.asList(new ServerAddress(host, port))))
                    .build()
            );
            MongoTemplate t = new MongoTemplate(client, dbName);
            mongoTemplates.put(dbName, t);
        }
        return mongoTemplates.get(dbName);
    }
}
