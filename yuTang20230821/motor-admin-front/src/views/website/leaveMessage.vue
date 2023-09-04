<template>
  <div class="page-carousel">
    <el-table
      :data="tableData"
      tooltip-effect="dark"
      border
      style="width: 100%"
      stripe
      empty-text="暂无数据"
    >
      <el-table-column align="center" label="名称" prop="title" />
      <el-table-column align="center" label="ID" prop="id" />
      <el-table-column align="center" label="排序" prop="sort" />
      <el-table-column align="center" label="浏览器量" prop="hits" />
      <el-table-column align="center" label="创建时间" prop="create_time" />
      <el-table-column align="center" label="文章分类">
        <template slot-scope="scope">
          {{ scope.row.cate_id === 1 ? '公司资讯' : (scope.row.cate_id === 2 ? '公司资讯' : '常见问题') }}
        </template>
      </el-table-column>

    </el-table>

    <el-pagination
      background
      layout="prev, pager, next"
      :page-size="20"
      :current-page="pageIndex"
      :total="total"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import {
  getLeaveMessage
} from '@/api/website'

export default {
  name: 'CarouselIndex',
  data() {
    return {
      pageIndex: 1,
      total: 0,
      tableData: []
    }
  },
  mounted() {
    this.getTable()
  },
  methods: {
    getTable() {
      const params = {
        page: this.pageIndex
      }
      getLeaveMessage(params).then(res => {
        this.tableData = res.data
        this.total = res.count
      })
    },
    handleCurrentChange(index) {
      this.pageIndex = index
      this.getTable()
    }
  }
}
</script>

<style lang="scss">
.page-carousel {
  padding: 20px 20px 50px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
}
.tool-bar {
  padding-right: 80px;
  padding-bottom: 20px;
  text-align: right;
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
