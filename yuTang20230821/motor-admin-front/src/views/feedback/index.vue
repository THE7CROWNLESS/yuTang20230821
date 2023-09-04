<template>
  <div class="page-feedback">

    <template>
      <el-table :data="feedbackData" tooltip-effect="dark" border style="width: 100%" stripe empty-text="暂无数据">
        <el-table-column align="center" label="主题" prop="title" />
        <el-table-column align="center" label="反馈内容" prop="message" />
        <el-table-column align="center" label="反馈用户" prop="name" />
        <el-table-column align="center" label="手机号" prop="phone" />
        <el-table-column align="center" label="qq" prop="qq" />
        <el-table-column align="center" label="邮箱" prop="email" />
        <el-table-column align="center" label="微信昵称">
          <template slot-scope="scope">
            {{ scope.row.user.username }}
          </template>
        </el-table-column>
        <el-table-column align="center" label="微信手机号">
          <template slot-scope="scope">
            {{ scope.row.user.phone }}
          </template>
        </el-table-column>
      </el-table>
    </template>

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
  getFeedback
} from '@/api/feedback'

export default {
  name: 'FeedbackIndex',
  data() {
    return {
      loading: false,
      pageIndex: 1,
      total: 0,
      feedbackData: []
    }
  },
  mounted() {
    this.getFeedbackData()
  },
  methods: {
    getFeedbackData() {
      this.loading = true
      const params = {
        page: this.pageIndex
      }
      getFeedback(params).then((res) => {
        this.feedbackData = res.data
        this.total = res.count
        this.loading = false
      })
    },
    handleCurrentChange(index) {
      this.pageIndex = index
      this.getFeedbackData()
    }

  }
}
</script>

<style lang="scss">
	.page-feedback {
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
