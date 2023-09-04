<template>
  <div class="page-report">
    <div class="tool-bar">
      <el-input
        placeholder="请输入8位数的设备ID"
        v-model="machine_id"
        class="input-with-select"
        clearable
        @keyup.enter.native="getReportData"
      >
        <el-button
          slot="append"
          icon="el-icon-search"
          @click="getReportData"
        ></el-button>
      </el-input>
      <!-- <div class="search-row">
        <el-input
          class="keyword-input"
          placeholder="请输入设备ID"
          clearable
          v-model="machine_id"
        ></el-input>
      </div>

      <div class="btn-row">
        <el-button type="primary" class="search-btn" @click="fetchData"
          >搜索</el-button
        >
        <el-button type="warn" class="rest-btn" @click="handleReset"
          >重置</el-button
        >
      </div> -->
    </div>
    <template>
      <el-table
        :data="reportList"
        tooltip-effect="dark"
        border
        style="width: 100%"
        stripe
        empty-text="暂无日志"
      >
        <el-table-column align="center" label="鱼塘名称" prop="pond_title" />
        <el-table-column align="center" label="故障设备" prop="machine_name" />
        <el-table-column align="center" label="设备ID" prop="machine_code" />
        <el-table-column align="center" label="故障原因" prop="error_reason" />
        <el-table-column align="center" label="故障时间" prop="create_time" />
        <el-table-column label="是否处理" align="center">
          <template slot-scope="scope">
            {{ scope.row.solve ? "已处理" : "未处理" }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="处理时间" prop="solve_time" />
        <el-table-column align="center" label="处理结果" prop="solve" />
      </el-table>
    </template>

    <el-pagination
      background
      layout="prev, pager, next"
      :page-size="20"
      :current-page="pageIndex"
      @current-change="handleCurrentChange"
      :total="total"
    >
    </el-pagination>
  </div>
</template>

<script>
import { getReport } from "@/api/report";

export default {
  name: "ReportIndex",
  data() {
    return {
      loading: false,
      pageIndex: 1,
      total: 0,
      reportList: [],
      machine_id: ""
    };
  },
  mounted() {
    this.getReportData();
  },
  methods: {
    getReportData() {
      this.loading = true;
      const params = {
        page: this.pageIndex,
        machine_id: this.machine_id
      };
      getReport(params).then(res => {
        this.reportList = res.data;
        this.total = res.count;
        this.loading = false;
      });
    },
    handleCurrentChange(index) {
      this.pageIndex = index;
      this.getReportData();
    }
  }
};
</script>

<style lang="scss">
.page-report {
  padding: 20px 20px 50px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
}

.tool-bar {
  padding: 10px 0 20px;

  .el-input-group {
    width: 400px;
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
</style>
