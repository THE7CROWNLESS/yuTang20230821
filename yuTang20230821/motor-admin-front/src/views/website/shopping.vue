<template>
  <div class="page-company">
    <el-form
      ref="addForm"
      label-position="left"
      label-width="140px"
      :model="addForm"
      :rules="addRules"
    >
      <el-form-item label="淘宝链接" prop="tb_url">
        <el-input v-model.trim="addForm.tb_url" placeholder="请输入淘宝链接" />
      </el-form-item>
      <el-form-item label="商城链接" prop="shop_url">
        <el-input v-model.trim="addForm.shop_url" placeholder="请输入商城链接" />
      </el-form-item>

      <el-form-item class="submit-row">
        <el-button
          type="primary"
          :loading="loading"
          :disabled="loading"
          @click="submit('addForm')"
        >设置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getCompany, editCompany } from '@/api/website'

export default {
  name: 'Shopping',
  data() {
    return {
      loading: false,
      isAdd: false,
      visible: false,
      addForm: {
        tb_url: '',
        shop_url: ''
      },
      addRules: {
        tb_url: [
          {
            required: true,
            trigger: 'blur',
            message: '请输入淘宝链接'
          }
        ],
        shop_url: [
          {
            required: true,
            trigger: 'blur',
            message: '请输入商城链接'
          }
        ]
      },
      cateData: []
    }
  },
  mounted() {
    this.getContent()
  },
  methods: {
    getContent() {
      const params = {
        page: this.pageIndex
      }
      getCompany(params).then(res => {
        this.addForm = res.data
      })
    },
    submit(formName) {
      this.loading = true
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.postEdit()
        } else {
          this.loading = false
        }
      })
    },
    async postEdit() {
      try {
        await editCompany(this.addForm)
        this.$message.success('设置成功')
        this.loading = false
        this.visible = false
        this.getContent()
      } catch (error) {
        this.loading = false
        this.visible = false
        this.$message.error('设置失败')
      }
    }
  }
}
</script>

<style lang="scss">
.page-company {
  padding: 50px 60px;
  .el-form {
    width: 800px;
  }
  .company_logo {
    display: flex;
    .el-image {
      margin-left: 30px;
    }
  }
  .submit-row {
    padding: 30px 0;
  }
}
</style>
