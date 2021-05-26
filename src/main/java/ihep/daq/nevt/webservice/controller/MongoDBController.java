package ihep.daq.nevt.webservice.controller;

import ihep.daq.nevt.webservice.model.Data201VO;
import ihep.daq.nevt.webservice.service.MongoDBService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Data201VO getData(@RequestParam(value = "fromTime", required = true) long fromTime,
                             @RequestParam(value = "toTime", required = true) long toTime,
                             @RequestParam(value = "fromTemp", required = true) double fromTemp,
                             @RequestParam(value = "toTemp", required = true) double toTemp,
                             @RequestParam(value = "fromGear", required = true) int fromGear,
                             @RequestParam(value = "toGear", required = true) int toGear,
                             @RequestParam(value = "pageIndex", required = true) int pageIndex,
                             @RequestParam(value = "numPerPage", required = true) int numPerPage) {
        return mongoDBService.getData(fromTime, toTime, fromTemp, toTemp, fromGear, toGear, pageIndex, numPerPage);
    }
}
