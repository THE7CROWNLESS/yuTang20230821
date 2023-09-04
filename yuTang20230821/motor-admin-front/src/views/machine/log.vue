<template>
  <div class="front-logs">
    <template>
      <el-table
        :data="logs"
        tooltip-effect="dark"
        border
        style="width: 100%"
        stripe
        empty-text="无操作记录"
      >
        <el-table-column
          prop="machine_code"
          align="center"
          label="设备ID"
          width="178"
        />
        <el-table-column prop="machine_name" label="设备名称" align="center" />
        <el-table-column prop="create_time" align="center" label="时间" />
        <el-table-column prop="nickname" align="center" label="操作员" />
        <el-table-column prop="action" align="center" label="事件" />
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
import { getLogs } from "@/api/machine";

export default {
  name: "MachineLog",
  data() {
    return {
      loading: false,
      pageIndex: 1,
      total: 0,
      logs: []
    };
  },
  mounted() {
    this.getLogsData();
  },
  methods: {
    getLogsData() {
      this.loading = true;
      const params = {
        page: this.pageIndex,
        id: this.$route.params.id
      };
      getLogs(params).then(res => {
        this.logs = res.data;
        this.total = res.count;
        this.loading = false;
      });
    },
    handleCurrentChange(index) {
      this.pageIndex = index;
      this.getLogsData();
    }
  }
};
</script>

<style lang="scss">
.front-logs {
  padding: 20px 20px 50px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
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
