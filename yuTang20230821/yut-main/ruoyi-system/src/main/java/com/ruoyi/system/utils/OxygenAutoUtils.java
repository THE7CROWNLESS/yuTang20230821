package com.ruoyi.system.utils;
import com.ruoyi.system.domain.YtMachineNew;
import com.ruoyi.system.domain.vo.OxygenAutoVo;
import com.ruoyi.system.mapper.YtMachineNewMapper;
import com.ruoyi.system.service.IYtMachineNewService;
import com.ruoyi.system.service.YtMachineNewService;

public class OxygenAutoUtils {

    private static IYtMachineNewService ytMachineNewService;
    private static YtMachineNewMapper ytMachineNewMapper;
    // 设置默认联动范围
    public static void setDefaultOxygen(String machineCode){
        // 默认0 - 10范围
        OxygenAutoVo oxygenAutoVo = new OxygenAutoVo(machineCode);
        ytMachineNewMapper.updateAuto(oxygenAutoVo);
    }

    // 判断溶氧数据是否在联动范围
    public static void dealOxygen(YtMachineNew ytMachineNew){
        OxygenAutoVo oxygenAutoVo = ytMachineNewMapper.selectAuto(ytMachineNew.getMachineCode());
        if (oxygenAutoVo.getOxygen1() > ytMachineNew.getOxygen()) {
            // 低于则全部开启
            openAllAerator(ytMachineNew);
        }else if (oxygenAutoVo.getOxygen2() < ytMachineNew.getOxygen()){
            // 大于则全部关闭
            closeAllAerator(ytMachineNew);
        }else {
            // 范围内 调制固定速度
            computeAeratorSpeed(ytMachineNew);
        }
    }

    public static void openAllAerator(YtMachineNew ytMachineNew){
        Integer old1 = ytMachineNew.getAerator1Status();
        Integer old2 = ytMachineNew.getAerator2Status();
        Integer old3 = ytMachineNew.getAerator3Status();
        Integer old4 = ytMachineNew.getAerator4Status();
        if (old1 != 1) {
            ytMachineNew.setAerator1Status(1);
            ytMachineNewService.updateMqttAerator(ytMachineNew,"1",old1,1);
        }
        if (old2 != 1) {
            ytMachineNew.setAerator2Status(1);
            ytMachineNewService.updateMqttAerator(ytMachineNew,"2",old2,1);
        }
        if (old3 != 1) {
            ytMachineNew.setAerator3Speed(1);
            ytMachineNewService.updateMqttAerator(ytMachineNew,"3",old3,1);
        }
        if (old4 != 1) {
            ytMachineNew.setAerator4Status(1);
            ytMachineNewService.updateMqttAerator(ytMachineNew,"4",old4,1);
        }
    }

    public static void closeAllAerator(YtMachineNew ytMachineNew){
        Integer old1 = ytMachineNew.getAerator1Status();
        Integer old2 = ytMachineNew.getAerator2Status();
        Integer old3 = ytMachineNew.getAerator3Status();
        Integer old4 = ytMachineNew.getAerator4Status();
        if (old1 != 0) {
            ytMachineNew.setAerator1Status(0);
            ytMachineNewService.updateMqttAerator(ytMachineNew,"1",old1,0);
        }
        if (old2 != 0) {
            ytMachineNew.setAerator2Status(0);
            ytMachineNewService.updateMqttAerator(ytMachineNew,"2",old2,0);
        }
        if (old3 != 0) {
            ytMachineNew.setAerator3Status(0);
            ytMachineNewService.updateMqttAerator(ytMachineNew,"3",old3,0);
        }
        if (old4 != 0) {
            ytMachineNew.setAerator4Status(0);
            ytMachineNewService.updateMqttAerator(ytMachineNew,"4",old4,0);
        }
    }


    public static void computeAeratorSpeed(YtMachineNew ytMachineNew){
        openAllAerator(ytMachineNew);
        // if ()
    }
}
