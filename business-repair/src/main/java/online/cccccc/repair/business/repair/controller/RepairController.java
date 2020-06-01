package online.cccccc.repair.business.repair.controller;

import online.cccccc.repair.business.repair.service.TRepairService;
import online.cccccc.repair.commons.domain.TRepair;
import online.cccccc.repair.commons.dto.Result;
import online.cccccc.repair.commons.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 你是电脑
 * @date 2019/11/1 - 15:30
 */
@CrossOrigin("*")
@RestController
public class RepairController {
    public static final Logger logger = LoggerFactory.getLogger(RepairController.class);

    @Resource
    private TRepairService tRepairService;
    @Resource
    private RedisService redisService;

    @PostMapping(value = "repair")
    public Result repair(@RequestBody TRepair tRepair){
        int insert = tRepairService.insert(tRepair);
        if(insert > 0) {
            return Result.makeResult(HttpStatus.OK.value(), "提交成功", tRepair);
        }
        return Result.makeResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),"提交失败",null);
    }
    @GetMapping(value = "repair/{repairId}")
    public Result repair(@PathVariable String repairId){
        TRepair repair = tRepairService.getRepairbyId(repairId);
        return Result.makeResult(HttpStatus.OK.value(), "查询成功", repair);
    }

    @GetMapping("repairs")
    public Result repairs(){
        List<TRepair> tRepairs = tRepairService.selectAll();
        return Result.makeResult(HttpStatus.OK.value(),"查询成功",tRepairs);
    }
    @GetMapping("repair/schedule/{uuid}")
    public Result updateSchedule(@PathVariable String uuid){
        // TODO 在 redis 中 通过 uuid 查询到单号
        TRepair tRepair =(TRepair) redisService.get(uuid);
        // 拿到单号在更新进度
        if (tRepair != null) {
            int updataById = tRepairService.updateById(tRepair.getRepairId().toString());
            if (updataById>0){
                redisService.deleteKey(uuid);
                return Result.makeResult(HttpStatus.OK.value(),"更新成功",null);
            }
        }

        return Result.makeResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),"更新失败",null);
    }
}
