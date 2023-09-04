<template>
  <div class="page-authority">
    <div class="tool-bar">
      <el-button type="success" size="small" @click="handleAdd">
        + 新增权限</el-button
      >
    </div>
    <template>
      <el-table
        :data="authorityData"
        border
        style="width: 100%"
        max-height="100%"
      >
        <!-- <el-table-column
				type="index"
				align="center"
				 label="序号"
				width="50">
				</el-table-column>				 -->
        <el-table-column prop="id" align="center" label="ID"> </el-table-column>
        <el-table-column prop="title" align="center" label="权限名称">
        </el-table-column>
        <el-table-column prop="name" align="center" label="路由">
        </el-table-column>
        <el-table-column align="center" label="操作" class="deal-column">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleEdit(scope.row)"
              >编辑</el-button
            >
            <el-button
              type="danger"
              size="mini"
              @click="handleDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <!-- <el-pagination
        background
        layout="prev, pager, next"
        :page-size="20"
        :current-page="pageIndex"
        @current-change="handleCurrentChange"
        :total="total"
      >
      </el-pagination> -->
    </template>

    <el-dialog title="新增权限" :visible.sync="addVisible" width="600px">
      <el-form
        ref="addForm"
        label-position="left"
        label-width="140px"
        :model="addForm"
        :rules="rules"
      >
        <el-form-item label="权限名称" prop="title">
          <el-input v-model="addForm.title" placeholder="请输入权限名称" />
        </el-form-item>

        <el-form-item label="路由" prop="name">
          <el-input v-model="addForm.name" placeholder="请输入路由" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addVisible = false">取 消</el-button>
        <el-button
          type="primary"
          :loading="loading"
          :disabled="loading"
          @click="submitAdd('addForm')"
          >确 定</el-button
        >
      </span>
    </el-dialog>

    <el-dialog title="编辑权限" :visible.sync="editVisible" width="600px">
      <el-form
        ref="editForm"
        label-position="left"
        label-width="140px"
        :model="editForm"
        :rules="rules"
      >
        <el-form-item label="权限名称" prop="title">
          <el-input v-model="editForm.title" placeholder="请输入权限名称" />
        </el-form-item>

        <el-form-item label="路由" prop="name">
          <el-input v-model="editForm.name" placeholder="请输入路由" />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button
          type="primary"
          :loading="loading"
          :disabled="loading"
          @click="submitEdit('editForm')"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getAuthorityList,
  addAuthority,
  editAuthority,
  deleteAuthority
} from "@/api/user";

export default {
  name: "AuthorityIndex",
  data() {
    return {
      loading: false,
      pageIndex: 1,
      total: 0,
      authorityData: [],
      loading: false,
      addVisible: false,
      addForm: {
        pid: 0,
        title: "",
        name: "",
        sort: 0,
        status: 1
      },
      rules: {
        title: [
          {
            required: true,
            trigger: "blur",
            message: "请输入权限名称"
          }
        ],
        name: [
          {
            required: true,
            trigger: "blur",
            message: "请输入路由"
          }
        ]
      },

      editVisible: false,
      editForm: {
        pid: 0,
        title: "",
        name: "",
        sort: 0,
        status: 1
      }
    };
  },
  mounted() {
    this.getAuthorityData();
  },
  methods: {
    getAuthorityData() {
      this.loading = true;
      getAuthorityList({}).then(res => {
        this.authorityData = res.data;
        this.total = res.count;
        this.loading = false;
      });
    },
    handleCurrentChange(index) {
      this.pageIndex = index;
      this.getAuthorityData();
    },
    handleAdd() {
      this.addVisible = true;
    },
    handleEdit(authority) {
      this.editForm = authority;
      this.editVisible = true;
    },
    handleDelete(authority) {
      this.$confirm("是否确认要删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteAuthority({
            id: authority.id
          })
            .then(res => {
              this.$message.success("删除成功");
              this.getAuthorityData();
            })
            .catch(err => {
              this.$message.error("删除失败");
            });
        })
        .catch(() => {
          this.$message.success("已取消删除");
        });
    },

    submitAdd(formName) {
      this.loading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          addAuthority(this.addForm)
            .then(res => {
              this.$message.success("新增成功");
              this.loading = false;
              this.addVisible = false;
              this.addForm = {
                pid: 0,
                title: "",
                name: "",
                sort: 0,
                status: 1
              };
              this.getAuthorityData();
            })
            .catch(err => {
              this.loading = false;
              this.addVisible = false;
              this.$message.error("新增失败");
            });
        } else {
          this.loading = false;
        }
      });
    },

    submitEdit(formName) {
      this.loading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          editAuthority(this.editForm)
            .then(res => {
              this.$message.success("修改成功");
              this.loading = false;
              this.editVisible = false;
              this.getAuthorityData();
            })
            .catch(err => {
              this.$message.error("修改失败");
              this.loading = false;
              this.editVisible = false;
            });
        } else {
          this.loading = false;
        }
      });
    }
  }
};
</script>

<style lang="scss">
.page-authority {
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
