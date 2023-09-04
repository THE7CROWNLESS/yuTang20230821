<template>
  <div class="page-role">
    <div class="tool-bar">
      <el-button type="success" size="small" @click="handleAdd">
        + 新增角色</el-button
      >
    </div>
    <template>
      <el-table
        :data="roleData"
        v-loading="tableLoading"
        border
        style="width: 100%"
        max-height="100%"
      >
        <el-table-column type="index" align="center" label="序号" width="50">
        </el-table-column>
        <el-table-column prop="id" align="center" label="ID"> </el-table-column>
        <el-table-column prop="title" align="center" label="角色名称">
        </el-table-column>
        <!-- <el-table-column prop="status" align="center" label="状态">
				</el-table-column> -->
        <el-table-column align="center" label="拥有权限">
          <template slot-scope="scope">
            {{ setAuth(scope.row.rules) }}
          </template>
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

    <el-dialog title="新增角色" :visible.sync="addVisible" width="600px">
      <el-form
        ref="addForm"
        label-position="left"
        label-width="140px"
        :model="addForm"
        :rules="addRules"
      >
        <el-form-item label="角色名称" prop="title">
          <el-input v-model="addForm.title" placeholder="请输入角色名称" />
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

    <el-dialog title="编辑角色" :visible.sync="editVisible" width="800px">
      <el-form
        ref="editForm"
        label-position="left"
        label-width="140px"
        :model="editForm"
        :rules="editRules"
      >
        <el-form-item label="角色名称" prop="title">
          <el-input v-model="editForm.title" placeholder="请输入角色名称" />
        </el-form-item>

        <el-form-item label="权限" prop="">
          <template>
            <el-checkbox
              :indeterminate="isIndeterminate"
              v-model="checkAll"
              @change="handleCheckAllChange"
              >全选</el-checkbox
            >
            <div style="margin: 15px 0;"></div>
            <el-checkbox-group
              v-model="editForm.rules"
              @change="handleCheckedAuthChange"
            >
              <el-checkbox
                v-for="item in authorityJson"
                :label="item.id"
                :key="item.id"
                >{{ item.title }}</el-checkbox
              >
            </el-checkbox-group>
          </template>
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
  getRoleList,
  getAuthorityJson,
  addRole,
  editRole,
  deleteRole,
  getAuthorityList
} from "@/api/user";

export default {
  name: "RoleIndex",
  data() {
    return {
      tableLoading: false,
      pageIndex: 1,
      total: 0,
      roleData: [],
      loading: false,
      addVisible: false,
      addForm: {
        title: "",
        status: 1
      },
      addRules: {
        title: [
          {
            required: true,
            trigger: "blur",
            message: "请输入角色名称"
          }
        ]
      },

      editVisible: false,
      editForm: {
        title: "",
        status: 1,
        rules: []
      },
      editRules: {
        title: [
          {
            required: true,
            trigger: "blur",
            message: "请输入角色名称"
          }
        ]
      },

      checkAll: false,
      isIndeterminate: true,
      authorityJson: [],
      allAuthority: []
    };
  },
  mounted() {
    this.getRoleData();
    this.getAllAuthority();
  },
  methods: {
    getRoleData() {
      this.tableLoading = true;
      getRoleList({})
        .then(res => {
          this.roleData = res.data;
          this.total = res.count;
          this.tableLoading = false;
        })
        .catch(err => {
          this.tableLoading = false;
        });
    },
    getAllAuthority() {
      getAuthorityList().then(res => {
        this.allAuthority = res.data;
      });
    },
    setAuth(rules) {
      let titles = [];
      this.allAuthority.forEach(x => {
        if (rules.includes(x.id)) {
          titles.push(x.title);
        }
      });
      return titles.toString();
    },
    handleCurrentChange(index) {
      this.pageIndex = index;
      this.getRoleData();
    },
    handleAdd() {
      this.addVisible = true;
    },
    handleEdit(role) {
      this.editForm = JSON.parse(JSON.stringify(role));
      const rules = role.rules.split(",").map(Number);
      this.$set(this.editForm, "rules", rules);
      this.getAuthority(role.id);
      this.editVisible = true;
    },
    getAuthority(id) {
      getAuthorityJson({
        id
      }).then(res => {
        this.authorityJson = res.data;
      });
    },

    handleCheckAllChange(val) {
      const rules = val ? this.authorityJson.map(x => x.id) : [];
      this.$set(this.editForm, "rules", rules);
      this.isIndeterminate = false;
    },
    handleCheckedAuthChange(value) {
      console.log(value);

      let checkedCount = value.length;
      this.checkAll = checkedCount === this.authorityJson.length;
      this.isIndeterminate =
        checkedCount > 0 && checkedCount < this.authorityJson.length;
    },

    handleDelete(role) {
      this.$confirm("是否确认要删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteRole({
            id: role.id
          })
            .then(res => {
              this.$message.success("删除成功");
              this.getRoleData();
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
          addRole(this.addForm)
            .then(res => {
              this.$message.success("新增成功");
              this.loading = false;
              this.addVisible = false;
              this.addForm = {
                title: "",
                status: 1
              };
              this.getRoleData();
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
      const isForbit =
        !this.editForm.rules.length ||
        (this.editForm.rules.length === 1 && this.editForm.rules[0] === 0);
      if (isForbit) {
        this.$message.error("请设置角色的权限");
        return;
      }
      this.loading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.editForm.rules = this.editForm.rules.filter(x => x > 0);
          editRole(this.editForm)
            .then(res => {
              this.$message.success("修改成功");
              this.loading = false;
              this.editVisible = false;
              this.getRoleData();
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
.page-role {
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
