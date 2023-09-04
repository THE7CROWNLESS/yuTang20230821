<template>
  <div class="page-customer">
    <el-form
      ref="addForm"
      label-position="left"
      label-width="140px"
      :model="addForm"
      :rules="addRules"
    >
      <el-form-item label="文本编辑" prop="company_custom">
        <Tinymce ref="editor" v-model="addForm.company_custom" :height="400" />
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
import Tinymce from '@/components/Tinymce'

export default {
  name: 'Customer',
  components: { Tinymce },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '') {
        this.$message({
          message: '请填写内容',
          type: 'error'
        })
        callback(new Error('请填写内容'))
      } else {
        callback()
      }
    }
    return {
      loading: false,
      isAdd: false,
      visible: false,
      addForm: {
        company_custom: ''
      },
      addRules: {
        company_custom: [{ validator: validateRequire }]
      }
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

<style lang="scss" scoped>
.page-customer {
  padding: 50px 60px;
  .el-form {
    // width: 800px;
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
