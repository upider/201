package ihep.daq.nevt.webservice.controller;

import ihep.daq.nevt.webservice.model.Data201VO;
import ihep.daq.nevt.webservice.model.GetDataRequest;
import ihep.daq.nevt.webservice.service.MongoDBService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/")
@Api(value = "MongoDBController")
public class MongoDBController {
    @Resource
    private final MongoDBService mongoDBService;

    public MongoDBController(MongoDBService mongoDBService) {
        this.mongoDBService = mongoDBService;
    }

    @PostMapping("getData")
    @ApiOperation(value="查询接口", httpMethod = "POST")
    public Data201VO getData(@RequestBody GetDataRequest req) {
        System.out.println(req);
        return mongoDBService.getData(req.getFromTime(), req.getToTime(), req.getFromTemp(), req.getToTemp(),
                req.getFromGear(), req.getToGear(), req.getPageIndex(), req.getNumPerPage());
    }

}
