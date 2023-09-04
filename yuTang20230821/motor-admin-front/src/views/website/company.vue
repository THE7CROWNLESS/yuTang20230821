<template>
  <div class="page-company">
    <el-form
      ref="addForm"
      label-position="left"
      label-width="140px"
      :model="addForm"
      :rules="addRules"
    >
      <el-form-item label="公司LOGO" prop="company_logo">
        <el-upload
          class="logo-uploader"
          :action="$uploadPath"
          :show-file-list="false"
          :on-success="handleSuccessLogo"
          :before-upload="beforeUpload"
        >
          <img
            v-if="addForm.company_logo"
            :src="addForm.company_logo"
            class="logo"
          />
          <i v-else class="el-icon-plus logo-uploader-icon" />
        </el-upload>
      </el-form-item>
      <el-form-item label="公司名称" prop="company_title">
        <el-input
          v-model="addForm.company_title"
          placeholder="请输入公司名称"
        />
      </el-form-item>
      <el-form-item label="平台描述" prop="company_remark">
        <el-input
          v-model="addForm.company_remark"
          placeholder="请输入平台描述"
        />
      </el-form-item>
      <el-form-item label="公司图标" prop="company_slogan">
        <el-upload
          class="logo-uploader"
          :action="$uploadPath"
          :show-file-list="false"
          :on-success="handleSuccessSlogan"
          :before-upload="beforeUpload"
        >
          <img
            v-if="addForm.company_slogan"
            :src="addForm.company_slogan"
            class="slogan-logo"
          />
          <i v-else class="el-icon-plus logo-uploader-icon" />
        </el-upload>
      </el-form-item>
      <!-- <el-form-item
        v-for="(slogan, index) in addForm.company_slogan"
        :label="'口号' + index"
        :key="slogan.key"
        :prop="'company_slogan.' + index + '.value'"
        :rules="[
          {
            required: true,
            message: '口号不能为空',
            trigger: 'blur'
          },
          { min: 2, max: 4, message: '长度在 2 到 4 个字符', trigger: 'blur' }
        ]"
      >
        <div class="slogan-item">
          <el-input v-model="slogan.value" maxlength="4"></el-input
          ><el-button
            class="delete-btn"
            type="danger"
            @click.prevent="removeSlogan(slogan)"
            >删除</el-button
          >
        </div>
      </el-form-item> -->

      <!-- <el-form-item>
        <el-button
          type="success"
          v-if="addForm.company_slogan.length < 3"
          @click="addSlogan"
          >新增口号</el-button
        >
      </el-form-item> -->

      <el-form-item label="公司介绍" prop="company_profile">
        <Tinymce ref="editor" v-model="addForm.company_profile" :height="400" />
      </el-form-item>
      <el-form-item label="联系人" prop="company_contact">
        <el-input
          v-model="addForm.company_contact"
          placeholder="请输入联系人名字"
        />
      </el-form-item>
      <el-form-item label="电话" prop="company_tel">
        <el-input v-model="addForm.company_tel" placeholder="请输入电话" />
      </el-form-item>
      <el-form-item label="手机" prop="company_phone">
        <el-input v-model="addForm.company_phone" placeholder="请输入手机号" />
      </el-form-item>
      <el-form-item label="地址" prop="company_address">
        <el-input
          v-model="addForm.company_address"
          placeholder="请输入公司地址"
        />
      </el-form-item>
      <el-form-item label="微信关注二维码" prop="company_qrcode">
        <el-upload
          class="logo-uploader"
          :action="$uploadPath"
          :show-file-list="false"
          :on-success="handleSuccessQrcode"
          :before-upload="beforeUpload"
        >
          <img
            v-if="addForm.company_qrcode"
            :src="addForm.company_qrcode"
            class="logo"
          />
          <i v-else class="el-icon-plus logo-uploader-icon" />
        </el-upload>
      </el-form-item>
      <el-form-item class="submit-row">
        <el-button
          type="primary"
          :loading="loading"
          :disabled="loading"
          @click="submit('addForm')"
          >设置</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getCompany, editCompany } from "@/api/website";
import Tinymce from "@/components/Tinymce";

export default {
  name: "ProductionIndex",
  components: { Tinymce },
  data() {
    return {
      loading: false,
      isAdd: false,
      visible: false,
      addForm: {
        company_title: "",
        company_logo: "",
        company_remark: "",
        company_profile: "",
        company_contact: "",
        company_tel: "",
        company_phone: "",
        company_address: "",
        company_slogan: "",
        // company_slogan: [
        //   {
        //     value: ""
        //   }
        // ],
        company_qrcode: ""
      },
      addRules: {
        company_title: [
          {
            required: true,
            trigger: "blur",
            message: "请输入公司名称"
          }
        ],
        company_logo: [
          {
            required: true,
            trigger: "blur",
            message: "请点击上传公司LOGO"
          }
        ],
        company_slogan: [
          {
            required: true,
            trigger: "blur",
            message: "请点击上传公司图标"
          }
        ],
        company_remark: [
          {
            required: true,
            trigger: "blur",
            message: "请输入平台描述"
          }
        ],
        company_profile: [
          {
            required: true,
            trigger: "blur",
            message: "请输入简介"
          }
        ],
        company_contact: [
          {
            required: true,
            trigger: "blur",
            message: "请输入联系人名字"
          }
        ],
        company_tel: [
          {
            required: true,
            trigger: "blur",
            message: "请输入电话"
          }
        ],
        company_phone: [
          {
            required: true,
            message: "请输入手机号",
            trigger: "blur"
          },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ],
        company_address: [
          {
            required: true,
            trigger: "blur",
            message: "请输入公司地址"
          }
        ],
        company_qrcode: [
          {
            required: true,
            trigger: "blur",
            message: "请点击上传二维码"
          }
        ]
      },
      cateData: []
    };
  },
  mounted() {
    this.getContent();
  },
  methods: {
    getContent() {
      const params = {
        page: this.pageIndex
      };
      getCompany(params).then(res => {
        this.addForm = res.data;
        // let data = res.data;
        // data.company_slogan = data.company_slogan.trim()
        //   ? data.company_slogan
        //       .trim()
        //       .split(" ")
        //       .map(x => {
        //         return { value: x };
        //       })
        //   : [{ value: "" }];
        // this.addForm = JSON.parse(JSON.stringify(data));
      });
    },
    submit(formName) {
      this.loading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.postEdit();
        } else {
          this.loading = false;
        }
      });
    },
    async postEdit() {
      //   const params = JSON.parse(JSON.stringify(this.addForm));
      //   params.company_slogan = params.company_slogan.map(x => x.value).join(" ");
      try {
        await editCompany(this.addForm);
        // await editCompany(params);
        this.$message.success("修改成功");
        this.loading = false;
        this.visible = false;
        this.getContent();
      } catch (error) {
        this.loading = false;
        this.visible = false;
        this.$message.error("修改失败");
      }
    },
    handleSuccessLogo(res) {
      this.addForm.company_logo = res.data;
    },
    handleSuccessQrcode(res) {
      this.addForm.company_qrcode = res.data;
    },
    handleSuccessSlogan(res) {
      this.addForm.company_slogan = res.data;
    },
    beforeUpload(file) {
      console.log(file);

      const isJPG = ["image/png", "image/jpeg", "image/svg+xml"].includes(
        file.type
      );
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("此图片格式不能上传，请换JPG格式的图片！");
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    removeSlogan(item) {
      if (this.addForm.company_slogan.length === 1) {
        this.$message.error("已经是最后一个口号了");
        return;
      }
      var index = this.addForm.company_slogan.indexOf(item);
      if (index !== -1) {
        this.addForm.company_slogan.splice(index, 1);
      }
    },
    addSlogan() {
      this.addForm.company_slogan.push({
        value: "",
        key: Date.now()
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.page-company {
  padding: 50px 60px;
  .el-form {
    max-width: 1060px;
    .slogan-item {
      display: flex;
      .delete-btn {
        margin-left: 20px;
      }
    }
  }
  .submit-row {
    padding: 30px 0;
  }
}
.logo-uploader {
  .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    &:hover {
      border-color: #409eff;
    }
  }
  .logo-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .logo {
    width: 178px;
    height: 178px;
    display: block;
  }
  .slogan-logo {
    width: 196px;
  }
}
</style>
