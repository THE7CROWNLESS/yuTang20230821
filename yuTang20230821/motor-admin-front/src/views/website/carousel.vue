<template>
  <div class="page-carousel">
    <div class="tool-bar">
      <el-button type="success" size="small" @click="handleAdd">
        + 新增轮播图</el-button
      >
    </div>
    <el-table
      :data="tableData"
      tooltip-effect="dark"
      border
      style="width: 100%"
      stripe
      empty-text="暂无数据"
    >
      <el-table-column align="center" label="名称" prop="title" />
      <el-table-column align="center" label="ID" prop="id" />
      <el-table-column align="center" label="排序" prop="sort" />
      <el-table-column align="center" label="跳转地址" prop="url" />
      <el-table-column align="center" label="创建时间" prop="create_time" />
      <el-table-column align="center" label="预览（点击）">
        <template slot-scope="scope">
          <el-image
            style="height: 35px"
            :src="scope.row.image"
            :preview-src-list="[scope.row.image]"
          />
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" class="deal-column">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleEdit(scope.row)"
            >编辑</el-button
          >
          <el-button type="primary" size="mini" @click="handleDelete(scope.row)"
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
      :total="total"
      @current-change="handleCurrentChange"
    />

    <el-dialog
      :title="isAdd ? '新增轮播图' : '编辑轮播图'"
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
        <el-form-item label="标题" prop="title">
          <el-input v-model="addForm.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="图片路径" prop="image">
          <!-- <el-input v-model="addForm.image" placeholder="请输入图片路径" /> -->
          <el-upload
            class="logo-uploader"
            :action="$uploadPath"
            :show-file-list="false"
            :on-success="handleSuccess"
            :before-upload="beforeUpload"
          >
            <img v-if="addForm.image" :src="addForm.image" class="logo" />
            <i v-else class="el-icon-plus logo-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="addForm.sort" :min="1" label="排序" />
        </el-form-item>
        <el-form-item label="跳转路径">
          <el-input v-model="addForm.url" placeholder="请输入跳转路径" />
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
  getCarousel,
  addCarousel,
  editCarousel,
  deleteCarousel
} from "@/api/website";

export default {
  name: "CarouselIndex",
  data() {
    return {
      loading: false,
      pageIndex: 1,
      total: 0,
      tableData: [],
      isAdd: false,
      visible: false,
      addForm: {
        id: "",
        title: "",
        sort: "",
        image: "",
        url: ""
      },
      addRules: {
        title: [
          {
            required: true,
            trigger: "blur",
            message: "请输入标题"
          }
        ],
        sort: [
          {
            required: true,
            trigger: "blur",
            message: "请输入序号"
          }
        ],
        image: [
          {
            required: true,
            trigger: "blur",
            message: "请输入图片路径"
          }
        ]
      }
    };
  },
  mounted() {
    this.getTable();
  },
  methods: {
    getTable() {
      const params = {
        page: this.pageIndex
      };
      getCarousel(params).then(res => {
        this.tableData = res.data;
        this.total = res.count;
      });
    },
    handleCurrentChange(index) {
      this.pageIndex = index;
      this.getTable();
    },

    handleAdd() {
      this.visible = true;
      this.isAdd = true;
    },
    handleEdit(item) {
      const editParams = JSON.parse(JSON.stringify(item));
      delete editParams.create_time;
      this.addForm = editParams;
      this.visible = true;
      this.isAdd = false;
    },
    handleDelete(item) {
      this.$confirm("是否确认要删除?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.sureDelete(item.id);
        })
        .catch(() => {
          this.$message.success("已取消删除");
        });
    },
    async sureDelete(id) {
      const res = await deleteCarousel({ id });
      this.$message.success(res.msg);
      this.getTable();
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
      this.addForm = {
        id: "",
        title: "",
        sort: "",
        image: "",
        url: ""
      };
    },
    async postAdd() {
      try {
        await addCarousel(this.addForm);
        this.$message.success("新增成功");
        this.loading = false;
        this.visible = false;
        this.getTable();
      } catch (error) {
        this.loading = false;
        this.visible = false;
        this.$message.error("新增失败");
      }
    },
    async postEdit() {
      try {
        await editCarousel(this.addForm);
        this.$message.success("修改成功");
        this.loading = false;
        this.visible = false;
        this.getTable();
      } catch (error) {
        this.loading = false;
        this.visible = false;
        this.$message.error("修改失败");
      }
    },
    handleSuccess(res, file) {
      this.addForm.image = res.data;
    },
    beforeUpload(file) {
      console.log(file.type);
      const isJPG = ["image/png", "image/jpeg"].includes(file.type);
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("此图片格式不能上传，请换JPG格式的图片!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    }
  }
};
</script>

<style lang="scss">
.page-carousel {
  padding: 20px 20px 50px;
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
      width: 100%;
      min-width: 178px;
      min-height: 178px;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .logo {
      width: 100%;
      min-width: 178px;
      min-height: 178px;
      display: block;
    }
  }
}
</style>
