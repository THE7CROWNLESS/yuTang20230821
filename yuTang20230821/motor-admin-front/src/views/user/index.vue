<template>
  <div class="front-users">
    <div class="tool-bar">
      <el-button type="success" size="small" @click="handleAdd">
        + 新增管理员</el-button
      >
    </div>
    <template>
      <el-table :data="users" border style="width: 100%" max-height="100%">
        <el-table-column prop="id" align="center" label="管理人ID">
        </el-table-column>
        <el-table-column prop="username" align="center" label="管理人名称">
        </el-table-column>
        <el-table-column prop="role" align="center" label="管理人所属组">
        </el-table-column>
        <el-table-column prop="group_id" align="center" label="管理人所属组ID">
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

    <el-dialog
      :title="isAdd ? '新增管理员' : '编辑管理员'"
      :visible.sync="visible"
      width="600px"
      @close="closeDialog"
    >
      <el-form
        ref="addForm"
        label-position="left"
        label-width="140px"
        :model="addForm"
        :rules="addRules"
      >
        <el-form-item label="管理组" prop="group_id">
          <template>
            <el-select v-model="addForm.group_id" placeholder="请选择管理组">
              <el-option
                v-for="item in groups"
                :key="item.id"
                :label="item.title"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" required prop="password">
          <el-input
            type="password"
            v-model="addForm.password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" required prop="check_password">
          <el-input
            type="password"
            v-model="addForm.check_password"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="visible = false">取 消</el-button>
        <el-button
          type="primary"
          :loading="loading"
          :disabled="loading"
          @click="submit('addForm')"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  getManagerList,
  addManager,
  editManager,
  deleteManager,
  getGroup
} from "@/api/user";
import { validUsername, validPwd } from "@/utils/validate";

export default {
  name: "Login",
  data() {
    const validateUsername = (rule, value, callback) => {
      console.log("xx", !validUsername(value));

      if (!validUsername(value)) {
        callback(new Error("用户名由4~16位英文、数字、下划线组成"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (!validPwd(value)) {
        callback(new Error("密码由6~16位英文、数字、下划线组成"));
      } else {
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.addForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      loading: false,
      pageIndex: 1,
      total: 0,
      users: [],
      groups: [],
      isAdd: false,
      visible: false,
      addForm: {
        username: "",
        group_id: "",
        password: "",
        check_password: ""
      },
      addRules: {
        username: [
          {
            required: true,
            trigger: "blur",
            validator: validateUsername
          }
        ],
        password: [
          {
            required: true,
            trigger: "blur",
            validator: validatePassword
          }
        ],
        check_password: [{ validator: validatePass2, trigger: "blur" }],
        group_id: [
          {
            required: true,
            trigger: "change",
            message: "请选择管理组"
          }
        ]
      }
    };
  },
  watch: {},
  mounted() {
    this.fetchData();
    this.getGroupData();
  },
  methods: {
    fetchData() {
      this.loading = true;
      const params = {
        page: this.pageIndex
      };
      getManagerList(params).then(res => {
        this.users = res.data;
        this.total = res.count;
        this.loading = false;
      });
    },
    getGroupData() {
      getGroup().then(res => {
        this.groups = res.data;
      });
    },
    handleCurrentChange(index) {
      this.pageIndex = index;
      this.fetchData();
    },
    handleAdd() {
      this.visible = true;
      this.isAdd = true;
    },
    handleEdit(manager) {
      this.addForm.username = manager.username;
      this.addForm.group_id = manager.group_id;
      this.addForm.id = manager.id;
      this.visible = true;
      this.isAdd = false;
    },
    handleDelete(manager) {
      this.$confirm("是否确认要删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteManager({
            id: manager.id
          })
            .then(res => {
              this.$message.success("删除成功");
              this.fetchData();
            })
            .catch(err => {
              this.$message.error("删除失败");
            });
        })
        .catch(() => {
          this.$message.success("已取消删除");
        });
    },
    submit(formName) {
      this.loading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.isAdd) {
            this.postAdd();
          } else {
            this.postEdit();
          }
        } else {
          this.loading = false;
        }
      });
    },
    closeDialog() {
      this.$refs.addForm.resetFields();
    },
    postAdd() {
      addManager(this.addForm)
        .then(res => {
          this.$message.success("新增成功");
          this.loading = false;
          this.visible = false;
          this.addForm = {
            username: "",
            group_id: "",
            password: "",
            check_password: ""
          };
          this.fetchData();
        })
        .catch(err => {
          this.loading = false;
          this.visible = false;
          this.$message.error("新增失败");
        });
    },

    postEdit() {
      editManager(this.addForm)
        .then(res => {
          this.$message.success("修改成功");
          this.loading = false;
          this.visible = false;
          this.addForm = {
            username: "",
            group_id: "",
            password: "",
            check_password: ""
          };
          this.fetchData();
        })
        .catch(err => {
          this.$message.error("修改失败");
          this.loading = false;
          this.visible = false;
        });
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
    padding: 3px 0;
  }
}
</style>
