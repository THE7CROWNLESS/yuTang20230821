<template>
  <div class="page-program">
    <div class="tool-bar">
      <el-button type="success" size="small" @click="handleAdd">
        + 新增分类</el-button>
    </div>
    <el-table
      :data="tableData"
      tooltip-effect="dark"
      border
      style="width: 100%"
      stripe
      empty-text="暂无数据"
    >
      <el-table-column align="center" label="分类名称" prop="title" />
      <el-table-column align="center" label="分类ID" prop="id" />
      <el-table-column align="center" label="创建时间" prop="create_time" />

      <el-table-column align="center" label="操作" class="deal-column">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            @click="handleEdit(scope.row)"
          >编辑</el-button>
          <el-button
            type="primary"
            size="mini"
            @click="handleDelete(scope.row)"
          >删除</el-button>
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

    <el-dialog
      :title="isAdd ? '新增分类' : '编辑分类'"
      :visible.sync="visible"
      width="800px"
      @close="closeDialog"
    >
      <el-form
        ref="addForm"
        label-position="left"
        label-width="140px"
        :model="addForm"
        :rules="addRules"
      >
        <el-form-item label="标题" prop="title">
          <el-input v-model="addForm.title" placeholder="请输入标题" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="visible = false">取 消</el-button>
        <el-button
          type="primary"
          :loading="loading"
          :disabled="loading"
          @click="submit('addForm')"
        >确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getArticleCate,
  addArticleCate,
  editArticleCate,
  deleteArticleCate
} from '@/api/website'

export default {
  name: 'ProgramCate',
  data() {
    return {
      loading: false,
      pageIndex: 1,
      total: 0,
      tableData: [],
      isAdd: false,
      visible: false,
      addForm: {
        id: '',
        pid: 2,
        title: ''
      },
      addRules: {
        title: [
          {
            required: true,
            trigger: 'blur',
            message: '请输入标题'
          }
        ]
      }
    }
  },
  mounted() {
    this.getTable()
  },
  methods: {
    getTable() {
      const params = {
        page: this.pageIndex,
        pid: 2
      }
      getArticleCate(params).then(res => {
        this.tableData = res.data
        this.total = res.count
      })
    },
    handleCurrentChange(index) {
      this.pageIndex = index
      this.getTable()
    },

    handleAdd() {
      this.visible = true
      this.isAdd = true
    },
    handleEdit(item) {
      this.addForm = {
        id: item.id,
        pid: 2,
        title: item.title
      }
      this.visible = true
      this.isAdd = false
    },
    handleDelete(item) {
      this.$confirm('是否确认要删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.sureDelete(item.id)
        })
        .catch(() => {
          this.$message.success('已取消删除')
        })
    },
    async sureDelete(id) {
      const res = await deleteArticleCate({ id })
      this.$message.success(res.msg)
      this.getTable()
    },
    submit(formName) {
      this.loading = true
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.isAdd) {
            this.postAdd()
          } else {
            this.postEdit()
          }
        } else {
          this.loading = false
        }
      })
    },
    closeDialog() {
      this.$refs.addForm.resetFields()
      this.addForm = {
        id: '',
        pid: 2,
        title: ''
      }
    },
    async postAdd() {
      try {
        await addArticleCate(this.addForm)
        this.$message.success('新增成功')
        this.loading = false
        this.visible = false
        this.getTable()
      } catch (error) {
        this.loading = false
        this.visible = false
        this.$message.error('新增失败')
      }
    },
    async postEdit() {
      try {
        await editArticleCate(this.addForm)
        this.$message.success('修改成功')
        this.loading = false
        this.visible = false
        this.getTable()
      } catch (error) {
        this.loading = false
        this.visible = false
        this.$message.error('修改失败')
      }
    }
  }
}
</script>

<style lang="scss">
.page-program {
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
.input-images-row{
  display: flex;
  .el-button{
    margin-left: 30px;
  }
}
</style>
