package com.ruoyi.web.controller.pond;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.query.DeliveryQuery;
import com.ruoyi.system.domain.query.MachineEditQuery;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.service.IMachineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设备管理模块
 *
 * @author lls
 */
@RestController
@RequestMapping("/admin/machine")
public class MachineController {

    @Resource(name = "machineServiceImpl")
    private IMachineService machineService;

    @Resource(name = "tokenService")
    private TokenService tokenService;

    /**
     * 设备列表接口
     * 查询设备列表接口时，首先从yt_machine表中查出所有列，然后根据machineId以及is_del为0在yt_user_machine查询出数据，获取到了pondTitle，
     * 再根据yt_user_machine表中的user_id，在yt_user表中查询出nickname，这就是master字段的值
     * 然后再根据yt_user_machine中的id在yt_pond_group_machine中根据user_machine_id查询出数据，并且is_master为0，再拿到user_id，再从yt_user表中查询出nickname,这就是manager的值
     *
     * @param keyword     设备ID或设备名
     * @param stat        设备状态 1已入库 2已发货 3已安装
     * @param page        页数
     * @param machineType 设备类型
     * @param overDate    设备有效期
     * @param name        备注名或微信名
     * @return 设备列表信息，其中还包含了用户信息
     */
    @GetMapping("/getMachineList")
    public AjaxResult getMachineList(@RequestParam(name = "keyword", required = false) String keyword, @RequestParam(name = "stat", required = false) String stat,
                                     @RequestParam(name = "page", required = false) String page, @RequestParam(name = "machine_type", required = false) String machineType,
                                     @RequestParam(name = "over_date", required = false) String overDate, @RequestParam(name = "name", required = false) String name) {
        PageInfo<MachineListVo> list = machineService.getMachineList(keyword, stat, page, machineType, overDate, name);
        return AjaxResult.success("查询成功！", (int) list.getTotal(), list.getList());
    }

    /**
     * 设备编辑接口，根据设备id更新设备的机器类型、结束时间（可不填）、计费结束时间、备注（可不填）
     * 所有字段更新的是yt_machine表同时根据id对应yt_user_machine表中的machine_id更新其记录的machine_type
     * 更新完成之后，根据id查询出最新的machine信息
     *
     * @param machineEditQuery 查询body
     * @return
     */
    @PostMapping("/editMachine")
    public AjaxResult editMachine(@RequestBody MachineEditQuery machineEditQuery) {
        System.out.println(machineEditQuery);

//        String overDateString = machineEditQuery.getOver_date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.CHINESE);
//        if (overDateString != null && !"".equals(overDateString)) {
////            //进行转化时区
////            Date overDate = null;
////            try {
////                overDate = dateFormat.parse(overDateString.replace("Z", "+0000"));
////            } catch (ParseException e) {
////                throw new RuntimeException(e);
////            }
////            //转换为年月日时分秒
////            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            String formatOverDate = df.format(overDate);
//            machineEditQuery.setOver_date(Timestamp.valueOf(overDateString).toString());
//        }
//        String endBindDateString = machineEditQuery.getEnd_bind_date();
//        //进行转化时区
//        Date endBindDate = null;
//        try {
//            endBindDate = dateFormat.parse(endBindDateString.replace("Z", "+0000"));
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        //转换为年月日时分秒
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String formatEndBindDateString = df.format(endBindDate);
//        machineEditQuery.setEnd_bind_date(Timestamp.valueOf(formatEndBindDateString).toString());
//        System.out.println();
//        System.out.println(machineEditQuery);
//        System.out.println();
//        machineService.editMachine(machineEditQuery);
        return AjaxResult.success("更新成功！");
    }

    /**
     * 根据id查询设备详情
     *
     * @param id 设备id
     * @return 设备详情
     */
    @GetMapping("/detail")
    public AjaxResult detail(@RequestParam("id") String id) {
        MachineDetailVo machine = machineService.detail(id);
        return AjaxResult.success("查询成功！", machine);
    }

    /**
     * 设备操作日志
     *
     * @param page 页数
     * @param id   对应表中的user_machine_id
     * @return 日志集合
     */
    @GetMapping("/log")
    public AjaxResult log(@RequestParam("page") String page, @RequestParam("id") String id) {
        PageInfo<MachineActionLogVo> pageInfo = machineService.log(page, id);
        return AjaxResult.success("查询成功！", pageInfo.getSize(), pageInfo.getList());
    }

    /**
     * 选择快递发货接口
     * 首先会根据id去yt_machine表中查询是否存在该设备，如果不存在则报不存在设备
     * 如果存在，更新这条记录的快递名称和单号
     *
     * @param deliveryQuery 包含了设备id、物流公司名称、物流单号
     * @return 响应数据
     */
    @PostMapping("/delivery")
    public AjaxResult delivery(@RequestBody DeliveryQuery deliveryQuery) {
        machineService.delivery(deliveryQuery);
        return AjaxResult.success();
    }

    /**
     * 在线发送指令接口
     * 1、检查该id是否在表中存在设备，如果不存在直接抛出异常
     * 2、如果code为pass
     * 2.1 检查当前设备的stat是否为0，如果不为0，则该设备已入库，无需操作
     * 2.2 如果stat为0，则更新其stat为1
     * 3、检查16进制命令是否正确
     * 4、操作redis
     *
     * @param commandVo 设备id和对应的16进制码
     * @return 成功则返回200状态码
     */
    @PostMapping("/command")
    public AjaxResult command(@RequestBody CommandVo commandVo, HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        machineService.command(commandVo, loginUser);
        return AjaxResult.success("提交成功");
    }

    /**
     * 后台设备操作日志接口
     *
     * @param page 页数
     * @param id   设备id
     * @return 日志列表
     */
    @GetMapping("/systemLog")
    public AjaxResult systemLog(@RequestParam("page") String page, @RequestParam("id") String id) {
        PageInfo<MachineActionSystemLogVo> pageInfo = machineService.systemLog(page, id);
        return AjaxResult.success("", pageInfo.getSize(), pageInfo.getList());
    }

    /**
     * 设备删除接口
     *
     * @param id 设备id
     * @return 成功或失败
     */
    @GetMapping("/deleteMachine")
    public AjaxResult deleteMachine(@RequestParam("id") String id) {
        machineService.deleteMachine(id);
        return AjaxResult.success();
    }

    /**
     * 溶氧仪历史记录查询接口
     * 1、根据id查询设备是否存在，如果不存在直接抛出对应异常
     * 2、设备存在，判断设备是否为溶氧仪(machine_type==4)，如果不是，直接抛出对应异常
     * 3、查询yt_machine_oxygen_log表
     *
     * @param id
     * @return
     */
    @GetMapping("/getOxygenLog")
    public AjaxResult getOxygenLog(@RequestParam("id") String id) {
//        machineService.getOxygenLog(id);
        int[][] test = new int[2][3];
        // 最终返回的map
        Map<String, Map> map = new HashMap<>();
        test[0][0] = 1;
        test[0][1] = 2;
        test[0][2] = 3;
        test[1][0] = 4;
        test[1][1] = 5;
        test[1][2] = 6;
        // map中的value Map
        Map<String, Map> hashMap = new HashMap<>();
        // hashMap中的value
        Map<String, List<List<Integer>>> msg = new HashMap<>();
        List<List<Integer>> all = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(0);
            list.add(1);
            list.add(2);
            list.add(3);
            all.add(list);
        }
        msg.put("oxygen", all);
        hashMap.put("2022-01-20", msg);
        map.put("result", hashMap);
        OxygenLogVo oxygenLogVo = machineService.getOxygenLog(id);
        return AjaxResult.success("", map);
    }

    /**
     * 耗电统计
     *
     * @param id 设备id
     * @return
     */
    @GetMapping("/electric")
    public AjaxResult electric(@RequestParam("id") String id) {
        List<MachineElectricVo> list = machineService.electric(id);
        return AjaxResult.success("", list);
    }
}
