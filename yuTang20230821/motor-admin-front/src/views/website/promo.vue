<template>
  <div class="page-promo">
    <el-form ref="addForm" label-position="left" label-width="140px">
      <el-form-item label="公司宣传片">
        <el-upload
          class="upload-demo"
          drag
          :action="$uploadPath"
          accept=".mp4"
          :multiple="false"
          :before-upload="beforeUpload"
          :on-success="handleSuccess"
          :on-remove="handleRemove"
        >
          <i class="el-icon-upload" />
          <div class="el-upload__text">
            将mp4格式的视频文件拖到此处，或<em>点击上传</em>
          </div>
          <div slot="tip" class="el-upload__tip">
            只能上传mp4格式的视频文件，且不超过50MB
          </div>
        </el-upload>
      </el-form-item>
      <el-form-item label="视频预览">
        <div class="video-review">
          <video v-if="company_video" :src="company_video" controls autoplay />
          <div class="no-video" v-else>还没有上传视频</div>
        </div>
      </el-form-item>
      <el-form-item class="submit-row">
        <el-button
          type="primary"
          :loading="loading"
          :disabled="loading"
          @click="submit"
        >设置</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
import { getCompany, editCompany } from '@/api/website'

export default {
  name: 'Promo',
  data() {
    return {
      loading: false,
      isAdd: false,
      visible: false,
      company_video: '',
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
        this.company_video = res.data.company_video?.trim()
      })
    },
    beforeUpload(file) {
      const isMP4 = file.type === 'video/mp4'
      const isLt2M = file.size / 1024 / 1024 < 50

      if (!isMP4) {
        this.$message.error('上传宣传视频只能是 MP4 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传视频大小不能超过 50MB!')
      }
      return isMP4 && isLt2M
    },
    handleSuccess(file) {
      this.company_video = file.data
    },
    handleRemove(file, fileList) {
      this.company_video = ''
    },
    submit() {
      this.loading = true
      console.log(this.company_video)
      if (this.company_video) {
        this.postEdit()
      } else {
        this.$message.error('请选择MP4格式的视频')
        this.loading = false
      }
    },
    async postEdit() {
      const data = {
        company_video: this.company_video
      }
      try {
        await editCompany(data)
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
.page-promo {
  padding: 50px 60px;
  display: flex;

  .el-form {
    width: 500px;
  }
  .submit-row {
    padding: 30px 0;
  }
.video-review{
    width: 360px;
    min-height: 218px;
    video{
        width: 100%;
    }
    .no-video{
        color: red;
    }
}
}
</style>
