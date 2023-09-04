<template>
  <div class="front-users">
    <template>
      <el-table :data="users" border style="width: 100%" max-height="100%">
        <el-table-column align="center" label="头像">
          <template slot-scope="scope">
            <el-avatar
              shape="square"
              size="small"
              :src="scope.row.avatar"
            ></el-avatar>
          </template>
        </el-table-column>
        <el-table-column prop="username" align="center" label="姓名">
        </el-table-column>
        <el-table-column prop="phone" align="center" label="手机号">
        </el-table-column>
        <el-table-column prop="province" align="center" label="省份">
        </el-table-column>
        <el-table-column prop="city" align="center" label="城市">
        </el-table-column>
        <el-table-column prop="create_time" align="center" label="创建时间">
        </el-table-column>

        <!-- <el-table-column prop="status" label="状态" width="120"> </el-table-column> -->

        <!-- <el-table-column fixed="right" align="center" label="操作">
          <template slot-scope="scope">
            <el-button @click="handleClick(scope.row)" type="text" size="small"
              >查看</el-button
            >
            <el-button type="text" size="small">编辑</el-button>
          </template>
        </el-table-column> -->
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
  </div>
</template>

<script>
import { getFrontUsers } from "@/api/user";

export default {
  name: "Login",
  data() {
    return {
      loading: false,
      pageIndex: 1,
      total: 0,
      users: []
    };
  },
  watch: {},
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      this.loading = true;
      const params = {
        page: this.pageIndex
      };
      getFrontUsers(params).then(res => {
        this.users = res.data;
        this.total = res.count;
        this.loading = false;
      });
    },
    handleCurrentChange(index) {
      this.pageIndex = index;
      this.fetchData();
    },
    handleClick(row) {
      console.log(row);
    }
  }
};
</script>

<style lang="scss" scoped>
.front-users {
  padding: 20px 40px 50px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;

  .el-table {
    flex: 1;
    min-height: 760px;
    padding-bottom: 30px;
  }

  .el-table td,
  .el-table th {
    padding: 3px 0;
  }
}
</style>
