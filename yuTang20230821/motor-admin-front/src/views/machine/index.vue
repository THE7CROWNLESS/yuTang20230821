<template>
  <div class="front-machines">
    <div class="tool-bar">
      <div class="search-row">
        <el-input
          class="keyword-input"
          placeholder="请输入ID/设备名称"
          clearable
          v-model="keyword"
        ></el-input>
        <el-input
          class="keyword-input"
          placeholder="请输入微信名/微信名"
          clearable
          v-model="searchName"
        ></el-input>
        <template>
          <el-select v-model="stat" clearable placeholder="请选择产品状态">
            <el-option
              v-for="item in statOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
        <template>
          <el-select
            v-model="machine_type"
            clearable
            placeholder="请选择设备类型"
          >
            <el-option
              v-for="item in machineTypes"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
        <template>
          <el-date-picker
            v-model="endBindDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择设备有效期"
          >
          </el-date-picker>
        </template>
      </div>

      <div class="btn-row">
        <el-button type="primary" class="search-btn" @click="fetchData"
          >搜索</el-button
        >
        <el-button type="warn" class="rest-btn" @click="handleReset"
          >重置</el-button
        >
      </div>
    </div>
    <div class="machine-total">
      <span>设备数量：</span>
      <span class="number">{{ total }}</span>
    </div>
    <template>
      <el-table
        :data="machines"
        tooltip-effect="dark"
        border
        style="width: 100%"
        stripe
        :row-class-name="tableRowClassName"
      >
        <!-- <el-table :data="machines" tooltip-effect="dark" border style="width: 100%" @selection-change="handleSelectionChange"> -->
        <!-- <el-table-column
          type="selection"
         align="center"
          width="30">
        </el-table-column>         -->
        <el-table-column
          prop="machine_code"
          align="center"
          label="设备ID"
          width="84px"
        />
        <el-table-column prop="machine_name" align="center" label="设备名称" />
        <!-- <el-table-column prop="machine_code" align="center" label="物理设备码">
        </el-table-column> -->
        <el-table-column prop="IMEI" align="center" label="IMEI" />
        <el-table-column
          prop="ICCID"
          align="center"
          label="ICCID"
          width="178"
        />

        <el-table-column align="center" label="设备类型">
          <template slot-scope="scope">
            <span>{{ setMachineType(scope.row) }}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column prop="create_time" align="center" label="创建时间">
        </el-table-column> -->
        <!-- <el-table-column prop="over_time" align="center" label="服务到期时间">
        </el-table-column> -->

        <!-- <el-table-column prop="first_bind_time" label="首次绑定时间" align="center"> </el-table-column> -->
        <!-- <el-table-column prop="end_bind_date" label="计费结束时间" align="center"> </el-table-column> -->
        <!-- <el-table-column prop="online_count" label="累计在线时长" align="center"> </el-table-column> -->
        <!-- <el-table-column prop="electric_count" label="累计用电" align="center"> </el-table-column> -->
        <el-table-column label="产品状态" align="center">
          <template slot-scope="scope">
            {{ setStatusTxt(scope.row.stat) }}
          </template>
        </el-table-column>

        <el-table-column prop="over_date" label="设备有效期" align="center" />
        <el-table-column
          prop="end_bind_date"
          label="服务有效期"
          align="center"
        />

        <el-table-column label="在线状态" align="center" width="80">
          <template slot-scope="scope">
            {{ scope.row.is_online == 1 ? "在线" : "离线" }}
          </template>
        </el-table-column>

        <el-table-column label="上线时间" align="center">
          <template slot-scope="scope">
            {{
              scope.row.is_online == 1
                ? scope.row.online_time
                : scope.row.offline_time
            }}
          </template>
        </el-table-column>

        <!-- <el-table-column label="设备状态" align="center">
          <template slot-scope="scope">
              {{setMachineStatus(scope.row)}}
          </template>
        </el-table-column> -->
        <el-table-column prop="username" label="微信名" align="center" />
        <el-table-column prop="remark" label="备注名" align="center" />

        <el-table-column align="center" label="操作" class="deal-column">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              @click="editMachineType(scope.row)"
              >编辑</el-button
            >
            <el-button
              type="primary"
              size="mini"
              @click="editMachineStatus(scope.row)"
              >出库</el-button
            >
            <el-button type="primary" size="mini" @click="checkCode(scope.row)"
              >二维码</el-button
            >
            <el-button
              type="primary"
              size="mini"
              :disabled="checkMachineDisable"
              @click="checkMachineData(scope.row)"
              >设备数据</el-button
            >

            <el-button type="default" size="mini" @click="goToLogs(scope.row)"
              >操作日志</el-button
            >
            <el-button type="danger" size="mini" @click="delMachine(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        layout="prev, pager, next"
        :page-size="20"
        :current-page="pageIndex"
        @current-change="handleCurrentChange"
        :total="total"
      >
      </el-pagination>
    </template>

    <el-dialog title="修改设备类型" :visible.sync="typeVisible" width="700px">
      <el-form
        label-position="left"
        label-width="140px"
        ref="editForm"
        :model="editForm"
        :rules="editRules"
      >
        <el-form-item label="名称：">
          <span>{{ currentMachine.machine_name }}</span>
        </el-form-item>
        <el-form-item label="类型：">
          <el-radio-group v-model="editForm.machineType">
            <el-radio
              v-for="(item, index) of machineTypes"
              :key="index"
              :label="item.value"
            >
              {{ item.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="设备有效期：" prop="over_date">
          <el-date-picker
            v-model="editForm.over_date"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item>

        <el-form-item label="服务有效期：" prop="end_bind_date">
          <el-date-picker
            v-model="editForm.end_bind_date"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注名">
          <el-input v-model="editForm.remark" style="width:220px;" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="typeVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitType('editForm')"
          >确 定</el-button
        >
      </span>
    </el-dialog>

    <el-dialog title="录入物流信息" :visible.sync="statusVisible" width="680px">
      <el-form
        ref="productionForm"
        label-position="left"
        label-width="140px"
        :model="productionForm"
        :rules="rules"
      >
        <el-form-item label="设备名称：">
          <span>{{ currentMachine.machine_name }}</span>
        </el-form-item>
        <el-form-item label="选择快递：" prop="express_name">
          <!-- <el-input v-model="productionForm.express_name" prop="express_name" placeholder="请输入快递单号" /> -->
          <el-select
            v-model="productionForm.express_name"
            placeholder="请选择快递"
          >
            <el-option
              v-for="item of delivery"
              :key="item.id"
              :label="item.name"
              :value="item.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="快递单号：" prop="express_no">
          <el-input
            v-model="productionForm.express_no"
            :disabled="productionForm.express_name === ''"
            placeholder="请输入快递单号"
          />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="statusVisible = false">取 消</el-button>
        <el-button
          type="primary"
          v-loading="statusLoading"
          :disabled="statusLoading"
          @click="submitStatus('productionForm')"
          >确 定</el-button
        >
      </span>
    </el-dialog>

    <el-dialog title="物流信息" :visible.sync="checkVisible" width="680px">
      <el-form
        ref="productionForm"
        label-position="left"
        label-width="140px"
        :model="productionForm"
      >
        <el-form-item label="设备名称：">
          <span>{{ currentMachine.machine_name }}</span>
        </el-form-item>
        <el-form-item label="选择快递：">
          <span>{{ productionForm.express_name }}</span>
        </el-form-item>
        <el-form-item label="快递单号：">
          <span>{{ productionForm.express_no }}</span>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog
      title="二维码"
      :visible.sync="qrVisible"
      class="qr-dialog"
      width="500px"
    >
      <img class="qrcode-img" :src="qr_code" alt="" />
    </el-dialog>

    <el-dialog
      class="machine-dialog"
      title="设备数据"
      :visible.sync="machineVisible"
      width="400px"
    >
      <el-form class="machine-form" label-position="left" label-width="90px">
        <el-form-item label="设备名称：">
          <span>{{ machineDetail.machine_name }}</span>
        </el-form-item>
        <el-form-item label="设备ID：">
          <span>{{ machineDetail.machine_code }}</span>
        </el-form-item>
        <!-- 增氧机 -->
        <template v-if="machineDetail.machine_type == 1">
          <div class="manager-row">
            <el-form-item label="塘主：">
              <span>{{ machineDetail.username || "无" }}</span>
            </el-form-item>
            <el-form-item label="管理者：" class="manager-item">
              <span>{{ machineDetail.manager || "无" }}</span>
            </el-form-item>
          </div>

          <el-form-item label="状态：">
            <el-tag
              size="mini"
              type="success"
              effect="dark"
              v-if="machineDetail.is_online == 1"
              >在线</el-tag
            >
            <el-tag type="info" size="mini" effect="dark" v-else>离线</el-tag>
          </el-form-item>
        </template>

        <!-- 测氧仪/PH检测仪 -->
        <template
          v-if="
            machineDetail.machine_type == 4 || machineDetail.machine_type == 5
          "
        >
          <el-form-item label="温度：">
            <span>{{ machineDetail.degree || 0 }} °C</span>
          </el-form-item>
          <el-form-item label="溶氧值：">
            <span>{{ machineDetail.oxygen || 0 }} mg/L</span>
          </el-form-item>
        </template>
        <!-- 其他电机类设备 -->
        <template v-else>
          <div
            v-if="
              (machineDetail.is_online == 1 &&
                machineDetail.machine_type == 1) ||
                machineDetail.machine_type != 1
            "
          >
            <el-form-item label="转速：">
              <span>{{ machineDetail.speed || 0 }} RPM</span>
            </el-form-item>
            <el-form-item label="功率：">
              <span
                >{{
                  machineDetail.power ? machineDetail.power * 0.001 : 0
                }}
                kW</span
              >
            </el-form-item>
          </div>
        </template>

        <!-- 增氧机 -->
        <template v-if="machineDetail.machine_type == 1">
          <el-form-item label="累计时长：">
            <span>{{ machineDetail.online_count || 0 }} H</span>
          </el-form-item>
          <div class="divider-title">
            <h2>本月用电</h2>
          </div>
          <el-form-item label="累计用电：">
            <span
              >{{
                machineDetail.electric_count
                  ? (machineDetail.electric_count / 10).toFixed(2)
                  : 0
              }}
              KWH</span
            >
          </el-form-item>
          <div class="exprot">
            <el-button
              type="primary"
              @click="handleExportElectr"
              v-loading="exportLoading"
              >导出月度用电数据</el-button
            >
          </div>
        </template>

        <!-- 测氧仪 -->
        <template v-if="machineDetail.machine_type == 4">
          <div class="exprot">
            <el-button
              type="primary"
              @click="handleExportLog"
              v-loading="exportLoading"
              >导出7天用电数据</el-button
            >
          </div>
        </template>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {
  getMachines,
  changeMachineType,
  handleShip,
  deleteMachine,
  getMachineDetail,
  getOxyElectric,
  getOxyLog
} from "@/api/machine";
import constants from "@/utils/constants";

export default {
  name: "MachineManage",
  data() {
    return {
      loading: false,
      pageIndex: 1,
      machines: [],
      machineTypes: Object.freeze([
        {
          value: 0,
          label: "无"
        },
        {
          value: 1,
          label: "增氧"
        },
        {
          value: 2,
          label: "投料"
        },
        {
          value: 3,
          label: "风扇"
        },
        {
          value: 4,
          label: "测氧仪"
        },
        {
          value: 5,
          label: "PH检测仪"
        }
      ]),
      multipleSelection: [],
      typeVisible: false,
      currentMachine: {},
      editForm: {
        machineType: 1,
        over_date: "",
        end_bind_date: "",
        remark: ""
      },
      editRules: {
        over_date: [
          {
            // type: "date",
            required: true,
            message: "请选择设备有效期",
            trigger: "change"
          }
        ],
        end_bind_date: [
          {
            // type: "date",
            required: true,
            message: "请选择服务有效期",
            trigger: "change"
          }
        ]
      },
      qr_code: "",
      qrVisible: false,
      statusVisible: false,
      checkVisible: false,

      delivery: Object.freeze(constants.delivery),
      productionForm: {
        express_name: "",
        express_no: ""
      },
      rules: {
        express_name: [
          // { required: true, trigger: 'change', validator: checkExpressName }
          {
            required: true,
            trigger: "change",
            message: "请选择快递"
          }
        ],
        express_no: [
          // { required: true, trigger: 'blur', validator: checkExpressNo }
          {
            required: true,
            trigger: "blur",
            message: "请输入快递单号"
          }
        ]
      },
      statusLoading: false,
      total: 0,

      machineVisible: false,
      keyword: "",
      searchName: "",
      stat: "",
      statOptions: [
        {
          label: "生产",
          value: "0"
        },
        {
          label: "入库",
          value: "1"
        },
        {
          label: "出库",
          value: "2"
        },
        {
          label: "客户建档",
          value: "3"
        }
      ],
      endBindDate: "",
      machine_type: "",
      machineDetail: {},
      electric: [],
      oxyLog: [],
      exportLoading: false,
      checkMachineDisable: false
    };
  },
  watch: {},
  mounted() {
    this.fetchData();
  },
  methods: {
    setStatusTxt(status) {
      const math = this.statOptions.find(x => {
        return x.value == status;
      });
      return math ? math.label : "无";
    },
    handleReset() {
      this.machine_type = "";
      this.stat = "";
      this.keyword = "";
      this.searchName = "";
      this.fetchData();
    },

    fetchData() {
      this.loading = true;
      const params = {
        page: this.pageIndex,
        stat: this.stat,
        machine_type: this.machine_type,
        keyword: this.keyword,
        name: this.searchName,
        over_date: this.endBindDate
      };
      getMachines(params).then(res => {
        this.machines = res.data;
        this.total = res.count;
        this.loading = false;
      });
    },
    handleCurrentChange(index) {
      this.pageIndex = index;
      this.fetchData();
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
    setMachineType(item) {
      return this.machineTypes.find(x => x.value === item.machine_type)?.label;
    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    editMachineType(machine) {
      this.currentMachine = machine;
      this.editForm.machineType = machine.machine_type;
      this.editForm.over_date = machine.over_date;
      this.editForm.end_bind_date = machine.end_bind_date;
      this.editForm.remark = machine.remark;

      this.typeVisible = true;
    },
    submitType(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          changeMachineType({
            id: this.currentMachine.id,
            machine_type: this.editForm.machineType,
            over_date: this.editForm.over_date,
            end_bind_date: this.editForm.end_bind_date,
            remark: this.editForm.remark
          })
            .then(res => {
              this.fetchData();
              this.typeVisible = false;
            })
            .catch(_ => {
              this.typeVisible = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    checkCode(machine) {
      if (!machine.qr_code) {
        this.$message.warning("没有二维码");
        return;
      }
      this.qr_code = machine.qr_code;
      this.qrVisible = true;
    },
    async checkMachineData(machine) {
      this.checkMachineDisable = true;
      this.currentMachine = machine;
      try {
        // 用电记录
        if (machine.machine_type == 1) {
          const resElectric = await getOxyElectric({ id: machine.id });
          this.electric = resElectric.data && resElectric.data.reverse();
          console.log(resElectric.data);
        } else if (machine.machine_type == 4) {
          // 测氧仪历史记录
          const resLog = await getOxyLog({ id: machine.id });
          this.oxyLog = resLog.data;
          console.log(resLog.data);
        }

        //   获取设备详情数据
        // 2021-8-16 增加下面设备详情接口，currentMachine和machineDetail,都包含部分设备数据
        const res = await getMachineDetail({ id: machine.id });
        this.machineDetail = res.data;
        console.log(res.data);

        this.machineVisible = true;
        this.checkMachineDisable = false;
      } catch (error) {
        this.checkMachineDisable = false;
      }
    },
    tableRowClassName({ row, rowIndex }) {
      if (row.machine_type === 0) {
        return "warning-row";
      }
      return "";
    },
    editMachineStatus(machine) {
      this.currentMachine = machine;
      this.productionForm.express_name = machine.express_name;
      this.productionForm.express_no = machine.express_no;
      if (machine.express_name) {
        // this.$message.warning('设备的物流信息已录入，无法更改')
        // return;
        this.checkVisible = true;
      } else {
        this.statusVisible = true;
      }
    },
    submitStatus(formName) {
      // console.log(this.productionForm);
      this.statusLoading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          handleShip({
            id: this.currentMachine.id,
            express_name: this.productionForm.express_name,
            express_no: this.productionForm.express_no
          })
            .then(res => {
              this.$message.success("操作成功");
              this.statusVisible = false;
              this.statusLoading = false;
              this.fetchData();
            })
            .catch(_ => {
              this.statusLoading = false;

              this.statusVisible = false;
            });
        } else {
          // console.log("error submit!!");
          this.statusLoading = false;

          return false;
        }
      });
    },

    goToLogs(machine) {
      this.$router.push({ name: "MachineLog", params: { id: machine.id } });
    },
    delMachine(machine) {
      this.$confirm(
        "此操作删除的数据不可恢复，请谨慎操作！",
        "删除设备所有数据",
        {
          distinguishCancelAndClose: true,
          confirmButtonText: "确定删除",
          cancelButtonText: "放弃"
        }
      )
        .then(() => {
          this.sureDeleteMachine(machine);
        })
        .catch(action => {
          this.$message({
            type: "info",
            message: "您已放弃删除"
          });
        });
    },
    sureDeleteMachine(machine) {
      deleteMachine({ id: machine.id })
        .then(res => {
          this.fetchData();
          this.$message.success("删除成功");
        })
        .catch(_ => {
          this.$message.error("删除失败");
        });
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]));
    },
    handleExportElectr() {
      if (this.electric && this.electric.length == 0) {
        this.$message.error("没有记录到此设备的用电量数据");
        return;
      }
      this.exportLoading = true;
      import("@/utils/Export2Excel").then(excel => {
        const tHeader = ["转多多增氧机月度用电汇总", "", ""];
        const pond_title = this.currentMachine.pond_title
          ? this.currentMachine.pond_title
          : "无";
        let data = [
          ["鱼塘名称", pond_title, ""],
          ["塘主名字", this.machineDetail.username, ""],
          ["增氧机编号", this.machineDetail.machine_code, ""],
          ["总时长", this.machineDetail.online_count, ""],
          //   ["总用电量", this.machineDetail.electric_count, ""],
          [
            "总用电量",
            this.machineDetail.electric_count
              ? (this.machineDetail.electric_count / 10).toFixed(2)
              : 0,
            ""
          ],
          ["数据", "时间", "kwh"]
        ];

        this.electric.forEach((oneMonth, inx) => {
          data.push([
            inx === 0 ? "月度用电" : inx + 1,
            oneMonth.date,
            (oneMonth.electric / 10).toFixed(2)
          ]);
        });

        // 合并单元格
        // const merges = ["A1:C1", "B2:C2", "B3:C3", "B4:C4", "B5:C5", "B6:C6"];
        const merges = ["A1:C1"];
        console.log(data);

        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename:
            "转多多增氧机-" +
            this.machineDetail.machine_name +
            "-" +
            this.machineDetail.machine_code +
            "-" +
            "月度用电汇总",
          merges,
          autoWidth: false,
          bookType: "xlsx"
        });
        this.exportLoading = false;
      });
    },
    handleExportLog() {
      this.exportLoading = true;
      import("@/utils/Export2Excel").then(excel => {
        const tHeader = ["转多多测氧仪数据", "", "", ""];
        const pond_title = this.currentMachine.pond_title
          ? this.currentMachine.pond_title
          : "无";

        let data = [
          ["鱼塘名称", pond_title, "", ""],
          [
            "塘主名字",
            this.machineDetail.username ? this.machineDetail.username : "",
            "",
            ""
          ],
          ["测氧仪编号", this.machineDetail.machine_code, "", ""],
          ["数据", "时间", "含氧量", "温度值"]
        ];

        const res = this.oxyLog.result;
        let count = 0;
        for (let key in res) {
          if (Object.hasOwnProperty.call(res, key)) {
            const val = res[key];
            const oxyLength = val.oxygen.length;
            for (let i = 0; i < oxyLength; i++) {
              const oneOxy = val.oxygen[i];
              const pie = parseInt(60 / oneOxy.length);
              for (let j = 0; j < oneOxy.length; j++) {
                const oneHourOxy = oneOxy[j];
                const currentClock = parseInt(j * pie);
                const minu =
                  currentClock < 10 ? "0" + currentClock : currentClock;
                count++;
                data.push([
                  count,
                  `${key} ${val.hours[i]}:${minu}`,
                  `${oneHourOxy}`,
                  `${val.degrees[i][j]}`
                ]);
              }
            }
          }
        }

        // 合并单元格
        const merges = ["A1:D1"];
        // const merges = ["A1:D1", "B2:D2", "B3:D3", "B4:D4"];

        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename:
            "转多多测氧仪-" +
            this.machineDetail.machine_name +
            "-" +
            this.machineDetail.machine_code +
            "-" +
            "用电记录",
          merges,
          autoWidth: false,
          bookType: "xlsx"
        });
        this.exportLoading = false;
      });
    }
  }
};
</script>

<style lang="scss">
.front-machines {
  padding: 20px 20px 50px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;

  .tool-bar {
    padding: 10px 0 20px;
    display: flex;
    justify-content: space-between;
    .keyword-input {
      width: 200px;
    }
    .keyword-input,
    .el-select,
    .el-button {
      margin-right: 20px;
    }
    .el-select {
      width: 150px;
    }
  }
}

.machine-total {
  padding-bottom: 10px;
  color: #1e293b;
  .number {
    color: #f56c6c;
  }
}

.el-table {
  flex: 1;
  min-height: 760px;
  padding-bottom: 30px;
}

.el-table td,
.el-table th {
  padding: 5px 0;
}

.el-table__row.warning-row td {
  color: #fff;
  background-color: #f56c6c !important;
}

.qr-dialog .el-dialog__body {
  text-align: center;
}

.el-button--mini {
  padding: 2px 5px;
}

@media screen and (max-width: 500px) {
  .qr-dialog .el-dialog {
    width: 300px !important;

    .qrcode-img {
      width: 180px;
    }
  }
}

.btn-row .el-button {
  text-align: center;
  margin: 0 10px !important;
}

.machine-form {
  .el-form-item {
    margin-bottom: 0 !important;
  }
  .exprot {
    padding-top: 20px;
  }
}

.manager-row {
  display: flex;
  .manager-item {
    margin-left: 50px;
  }
}

.divider-title {
  h2 {
    line-height: 24px;
    font-size: 18px;
    font-weight: normal;
    color: #000;
  }
}
.machine-dialog {
  .el-dialog__body {
    padding: 0 20px 30px;
  }
}
</style>
