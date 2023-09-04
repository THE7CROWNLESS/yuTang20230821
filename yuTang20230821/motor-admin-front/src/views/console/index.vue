<template>
  <div class="app-container">
    <el-alert
      title="注意："
      type="warning"
      description="直接发命令给设备，请谨慎使用；只能给运行中的设备发送命令；"
      :closable="false"
    />
    <el-divider />

    <div class="container">
      <el-form
        ref="form"
        label-position="left"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="设备" prop="id">
          <el-autocomplete
            class="search-id-input"
            clearable
            resize="vertical"
            v-model="form.id"
            value-key="value"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入设备的ID"
            @select="selectMachine"
            @clear="clearMachine"
          ></el-autocomplete>
          <!-- <el-select
            v-model="form.id"
            placeholder="请选择设备"
            clearable
            filterable
            @change="selectMachine"
          >
            <el-option
              v-for="item in allMachines"
              :key="item.id"
              :label="item.machine_name + '  ( ' + item.IMEI + ' )'"
              :value="item.id"
            />
          </el-select> -->
        </el-form-item>

        <template v-if="currentMachineId">
          <el-form-item label="状态">
            <div
              class="machineStatus"
              :class="{
                green:
                  currrentMachine.is_online == 1 && currrentMachine.status == 1
              }"
            >
              <span>{{ setMachineStatus(currrentMachine) }}</span>
            </div>
          </el-form-item>
          <el-form-item
            label="调试"
            v-if="
              [1, 2, 3, '1', '2', '3'].includes(currrentMachine.machine_type)
            "
          >
            <div class="debugging-row">
              <el-button type="primary" @click="handleDebug('0106A1060001')"
                >启动</el-button
              >
              <el-button type="danger" @click="handleDebug('0106A1060000')"
                >停机</el-button
              >
              <el-button type="info" @click="handlePass">PASS</el-button>
            </div>
          </el-form-item>
        </template>

        <el-form-item label="命令" prop="code">
          <el-input
            v-model.trim="form.code"
            maxlength="30"
            clearable
            placeholder="请输入30个以内的16进制的字符"
          />
        </el-form-item>

        <el-form-item class="submit-item">
          <el-button @click="resetForm('form')">重置</el-button>
          <el-button type="primary" @click="submitForm('form')">提交</el-button>
        </el-form-item>

        <!-- <el-form-item label="查询格式">
          <div class="table-tip">0103+寄存器地址+0001</div>
        </el-form-item> -->
        <div class="table-tip">查询格式：<i>0103 + 寄存器地址 + 0001</i></div>

        <!-- <el-form-item label="寄存器地址表"> -->
        <div class="table-tip">寄存器地址表 "增氧机信息查询表"</div>
        <template>
          <el-table class="search-table" :data="searchTable" border>
            <el-table-column prop="addr" label="寄存器地址" align="center">
            </el-table-column>
            <el-table-column prop="name" label="名称" align="center">
            </el-table-column>
          </el-table>
        </template>
        <!-- </el-form-item> -->
      </el-form>
      <div class="revice">
        <div class="title">
          <span>信息接收显示区</span>
        </div>
        <div class="recevice-box">
          <ul>
            <li
              v-for="(item, index) of receviceNews"
              :key="index"
              :class="item.type"
            >
              {{ item.msg }}
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getMachines } from "@/api/machine";
import { addCommand } from "@/api/console";
import mqtt from "mqtt";
export default {
  data() {
    var validateMacth = (rule, value, callback) => {
      if (!this.currentMachineId) {
        callback(new Error("没有找到这个ID的设备"));
      } else {
        callback();
      }
    };
    var validateType = (rule, value, callback) => {
      const reg = /^[A-Za-z0-9]+$/;

      if (!reg.test(value)) {
        callback(new Error("命令必须是由数字和字母组成"));
      } else {
        callback();
      }
    };
    return {
      filterMachines: [],
      allMachines: [],
      form: {
        id: "",
        code: ""
      },
      rules: {
        id: [
          {
            required: true,
            message: "请选择设备",
            trigger: "change"
          },
          {
            validator: validateMacth,
            trigger: "blur"
          }
        ],
        code: [
          {
            required: true,
            message: "请输入命令",
            trigger: "blur"
          },
          {
            min: 1,
            max: 30,
            message: "长度在 1 到 30 个字符",
            trigger: "blur"
          },
          {
            validator: validateType,
            trigger: "blur"
          }
        ]
      },
      loading: false,
      currentMachineId: "",
      receviceNews: [],
      client: null,
      currentTime: "",
      timeout: null,
      searchTable: Object.freeze([
        { addr: 7000, name: "运行频率" },
        { addr: 7001, name: "设定频率" },
        { addr: 7002, name: "母线电压" },
        { addr: 7003, name: "输出电压" },
        { addr: 7004, name: "输出电流" },
        { addr: 7005, name: "输出功率" },
        { addr: "F00B", name: "载波频率" },
        { addr: "FB00-FB07", name: "多段速" }
      ])
    };
  },
  computed: {
    currrentMachine() {
      return this.allMachines.filter(x => x.id === this.currentMachineId)[0];
    }
  },
  mounted() {
    this.fetchData();
    this.createConnection();
  },
  beforeDestroy() {
    this.client && this.client.end();
  },
  methods: {
    handleDebug(debugCode) {
      this.form.code = debugCode;
    },
    handlePass() {
      this.$confirm(
        "将设备状态设为“入库”，请确认 开机和停机 功能正常",
        "设置设备状态",
        {
          distinguishCancelAndClose: true,
          confirmButtonText: "设置",
          cancelButtonText: "取消"
        }
      )
        .then(() => {
          this.setPassCode();
        })
        .catch(action => {
          this.$message({
            type: "info",
            message: "已放弃设置"
          });
        });
    },
    async setPassCode() {
      let machineCode = this.form.id.split(" ")[3];
      const match = this.allMachines.find(x => {
        return x.machine_code == machineCode;
      });
      let params = {
        id: match.id,
        code: "pass"
      };
      try {
        await addCommand(params);
        this.$message.success("设置成功");
      } catch (error) {
        this.$message.error("设置失败");
      }
    },
    fetchData() {
      getMachines({
        page: 0
        // keyword: ""
      }).then(res => {
        this.allMachines = res.data.map(x => {
          x.value = `${x.machine_name}  ( ${x.machine_code} ) （ ${x.IMEI} ）`;
          return x;
        });
      });
    },
    querySearchAsync(queryString, cb) {
      var machines = this.allMachines;
      var results = queryString
        ? machines.filter(this.createStateFilter(queryString))
        : machines;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 3000 * Math.random());
    },
    createStateFilter(queryString) {
      return state => {
        return (
          state.machine_code
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    submitForm(formName) {
      this.loading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.currrentMachine.is_online == 0) {
            this.$message.error("当前设备已离线，无法接收指令");
            return;
          }
          this.postData();
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    async postData() {
      let machineCode = this.form.id.split(" ")[3];
      const match = this.allMachines.find(x => {
        return x.machine_code == machineCode;
      });
      let params = {
        id: match.id,
        code: this.form.code
      };
      try {
        await addCommand(params);
        this.$message.success("提交成功");
        this.loading = false;
        // 收发区
        const timeFormat = this.getCurrent();
        const sendcode = {
          type: "send",
          msg: `[${timeFormat}] 发送：${this.form.code}（校验位忽略）`
        };
        this.receviceNews.push(sendcode);
      } catch (error) {
        this.loading = false;
        this.$message.error("提交失败");
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    setMachineStatus(item) {
      return item.is_online == 0
        ? "离线"
        : item.status == 1
        ? "运行"
        : item.status == 2
        ? "停机"
        : "故障";
    },
    selectMachine(item) {
      this.currentMachineId = item.id;
      this.receviceNews = [];
    },
    clearMachine() {
      this.currentMachineId = "";
      this.receviceNews = [];
    },
    // 创建mqtt连接
    createConnection() {
      const clientId =
        "mqttjs_" +
        Math.random()
          .toString(16)
          .substr(2, 8);

      const host = "ws://www.ecarboncn.com:8083/mqtt";

      const options = {
        keepalive: 20,
        clientId: clientId,
        protocolId: "MQTT",
        protocolVersion: 4,
        clean: true,
        reconnectPeriod: 1000,
        connectTimeout: 30 * 1000,
        username: "ychjasp",
        password: "821006",
        will: {
          topic: "WillMsg",
          payload: "Connection Closed abnormally..!",
          qos: 0,
          retain: false
        },
        rejectUnauthorized: false
      };

      console.log("connecting mqtt client");
      this.client = mqtt.connect(host, options);

      this.client.on("error", err => {
        console.log("Connection error: ", err);
        this.client.end();
      });

      this.client.on("reconnect", () => {
        console.log("Reconnecting...");
      });

      this.client.on("connect", () => {
        console.log("Client connected:" + clientId);
        this.client.subscribe("/+/SET", { qos: 0 });
        // client.publish('testtopic', 'ws connection demo...!', { qos: 0, retain: false })
      });

      this.client.on("message", (topic, message, packet) => {
        console.log(
          "Received Message: " + message.toString() + "\nOn topic: " + topic
        );
        console.log("设备返回值：", this.getRespone(message));
        console.log(packet);
        const receivedId = topic.split("/")[1];
        console.log(
          "设备返回信息的IMEI：",
          receivedId,
          "当前设备的IMEI：",
          this.currrentMachine?.IMEI
        );
        console.log("xx", receivedId == this.currrentMachine?.IMEI);

        if (receivedId == this.currrentMachine?.IMEI) {
          const timeFormat = this.getCurrent();
          const resp = this.getRespone(message);
          const recev = {
            type: "recev",
            msg: `[${timeFormat}] 接收：${resp}`
          };
          this.receviceNews.push(recev);
        }
      });

      this.client.on("close", () => {
        console.log(clientId + " disconnected");
      });
    },
    getCurrent() {
      const now = new Date();
      let hh = now.getHours();
      let mm = now.getMinutes();
      let ss = now.getSeconds();
      hh = hh > 9 ? hh : `0${hh}`;
      mm = mm > 9 ? mm : `0${mm}`;
      ss = ss > 9 ? ss : `0${ss}`;
      return `${hh}:${mm}:${ss}`;
    },
    getRespone(res) {
      const array = new Uint8Array(res);
      let str = "";
      for (var i = 0; i < array.length; i++) {
        var tmp = array[i].toString(16);
        if (tmp.length === 1) {
          tmp = "0" + tmp;
        }
        str += " " + tmp;
      }
      str = str.toUpperCase().trim();
      return str;
    }
  }
};
</script>

<style scoped lang="scss">
.container {
  display: flex;
  align-content: center;
  justify-content: space-around;
}
.el-form {
  width: 460px;

  .el-select {
    width: 100%;
  }

  .submit-item {
    margin-top: 50px;
  }
}

.revice {
  width: 460px;
  min-height: 300px;
  .title {
    font-size: 16px;
    padding-bottom: 16px;
  }

  .recevice-box {
    width: 500px;
    padding: 0 20px 0 0;
    height: 240px;
    overflow-y: auto;
    border: 1px solid #f2f2f2;
    li {
      font-size: 14px;
      line-height: 20px;
    }
    .recev {
      color: green;
    }
    .send {
      color: red;
    }
  }
}
.machineStatus {
  color: red;
  font-weight: 800;
  &.green {
    color: green;
  }
}

.search-id-input {
  width: 360px;
}

.table-tip {
  font-size: 14px;
  color: #475569;
  padding: 10px;

  i {
    color: #1e293b;
    font-weight: bold;
  }
}
.search-table {
  min-height: auto;
  padding-bottom: 10px 0;
}
</style>
