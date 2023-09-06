package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Machine;
import com.ruoyi.system.domain.MachineActionSystemLog;
import com.ruoyi.system.domain.UserMachine;
import com.ruoyi.system.domain.query.DeliveryQuery;
import com.ruoyi.system.domain.query.MachineEditQuery;
import com.ruoyi.system.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MachineMapper {


    List<MachineListVo> getMachineList(@Param("keyword") String keyword, @Param("stat") String stat, @Param("machineType") String machineType
            , @Param("overDate") String overDate, @Param("name") String name);

    MachineDetailVo detail(@Param("id") String id);

    List<MachineActionLogVo> log(@Param("id") String id);

    Integer getMachineCountById(@Param("id") Integer id);

    Integer updateMachineExpressById(DeliveryQuery deliveryQuery);

    Machine getMachineById(@Param("id") int id);

    Integer updateMachineStatById(@Param("id") int id, @Param("stat") int stat);

    Integer saveMachineActionSystemLog(MachineActionSystemLog machineActionSystemLog);

    List<MachineActionSystemLogVo> systemLog(@Param("id") String id);

    void updateMachineDelById(@Param("id") String id, @Param("del") int i);

    UserMachine getUserMachineByMachineId(@Param("id") String id);

    void updateUserMachineDelById(@Param("id") Integer id, @Param("del") int del);

    void deletePondGroupMachineByUserMachineId(@Param("userMachineId") Integer userMachineId);

    void deletePondMachineRuleByUserMachineId(@Param("userMachineId") Integer userMachineId);

    void deleteTaskSettingByUserMachineId(@Param("userMachineId") Integer userMachineId);

    void deleteMachineOxygenMiddleByOxUserMachineId(@Param("userMachineId") Integer userMachineId);

    List<MachineElectricVo> getMachineElectric(@Param("id") String id);

    Integer updateMachineEditById(MachineEditQuery machineEditQuery);

    void updateUserMachineType(@Param("id") String id, @Param("machineType") String machineType);
}
