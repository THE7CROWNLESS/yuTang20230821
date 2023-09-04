<template>
  <div class="author-profile">
    <el-form
      ref="addForm"
      label-position="left"
      label-width="140px"
      :model="addForm"
      :rules="addRules"
    >
      <el-form-item label="用户名" prop="username">
        <el-input v-model="addForm.username" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          v-model="addForm.password"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="check_password">
        <el-input
          type="password"
          v-model="addForm.check_password"
          autocomplete="off"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          style="width:100%;margin-bottom:50px;"
          type="primary"
          :loading="loading"
          :disabled="loading"
          @click="handleSubmit('addForm')"
          >保存</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { editAuthor } from "@/api/user";
import { validUsername, validPwd } from "@/utils/validate";
export default {
  name: "Login",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error("用户名由4~16位英文、数字、下划线组成"));
      } else {
        callback();
      }
    };
    // const validatePassword = (rule, value, callback) => {
    //   if (!validPwd(value)) {
    //     callback(new Error("密码由6~16位英文、数字、下划线组成"));
    //   } else {
    //     callback();
    //   }
    // };
    var validatePass2 = (rule, value, callback) => {
      //   if (value === "") {
      //     callback(new Error("请再次输入密码"));
      //   } else
      if (value !== this.addForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      loading: false,
      addForm: {
        id: "",
        username: "",
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
        // password: [
        //   {
        //     required: true,
        //     trigger: "blur",
        //     validator: validatePassword
        //   }
        // ],
        check_password: [{ validator: validatePass2, trigger: "blur" }]
      }
    };
  },
  mounted() {
    this.addForm.id = this.$store.state.user.adminInfo.id;
    this.addForm.username = this.$store.state.user.adminInfo.username;
  },
  methods: {
    handleSubmit(formName) {
      this.loading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.handlePost();
        } else {
          this.loading = false;
        }
      });
    },
    closeDialog() {
      this.$refs.addForm.resetFields();
    },
    handlePost() {
      editAuthor(this.addForm)
        .then(res => {
          this.$message.success("新增成功");
          this.$store.commit("user/SET_NAME", this.addForm.username);
        })
        .catch(err => {
          this.loading = false;
          this.$message.error("新增失败");
        });
    }
  }
};
</script>

<style lang="scss" scoped>
.author-profile {
  padding: 30px 40px;

  .el-form {
    width: 600px;
  }
}
</style>
