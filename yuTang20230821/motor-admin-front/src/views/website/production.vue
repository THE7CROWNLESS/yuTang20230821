<template>
  <div class="page-production">
    <div class="tool-bar">
      <el-button type="success" size="small" @click="handleAdd">
        + 新增产品</el-button
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
      <el-table-column align="center" label="ID" prop="id" width="80px" />
      <el-table-column align="center" label="排序" prop="sort" width="80px" />
      <el-table-column
        align="center"
        label="分类ID"
        prop="cate_id"
        width="80px"
      />
      <el-table-column align="center" label="分类名称">
        <template slot-scope="scope">
          {{ scope.row.cateInfo.title }}
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        label="详情"
        prop="detail"
        show-overflow-tooltip
      />
      <el-table-column
        align="center"
        label="浏览量"
        prop="hits"
        width="100px"
      />
      <el-table-column align="center" label="创建时间" prop="create_time" />
      <!-- <el-table-column align="center" label="分类ID">
        <template slot-scope="scope">
          {{ scope.row.cateInfo.id }}
        </template>
      </el-table-column> -->

      <el-table-column align="center" label="缩略图">
        <template slot-scope="scope">
          <el-image
            style="height: 35px"
            :src="scope.row.thumb"
            :preview-src-list="[scope.row.thumb]"
          />
        </template>
      </el-table-column>
      <!-- <el-table-column align="center" label="产品细节图">
        <template slot-scope="scope">
          <el-image
            v-if="scope.row.images"
            style="height: 35px"
            :src="scope.row.images[0]"
            :preview-src-list="scope.row.images"
          />
          <span v-else>暂无</span>
        </template>
      </el-table-column> -->
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
      :title="isAdd ? '新增产品' : '编辑产品'"
      :visible.sync="visible"
      width="1100px"
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
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="addForm.sort" :min="1" label="排序" />
        </el-form-item>
        <el-form-item label="分类" prop="cate_id">
          <template>
            <el-select
              v-model="addForm.cate_id"
              filterable
              placeholder="请选择分类"
            >
              <el-option
                v-for="item in cateData"
                :key="item.id"
                :label="item.title"
                :value="item.id"
              />
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="缩略图" prop="thumb">
          <div class="span">最佳尺寸：282px ✖️ 212px</div>
          <el-upload
            class="logo-uploader"
            :action="$uploadPath"
            :show-file-list="false"
            :on-success="handleSuccess"
            :before-upload="beforeUpload"
          >
            <img v-if="addForm.thumb" :src="addForm.thumb" class="logo" />
            <i v-else class="el-icon-plus logo-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item label="描述" prop="detail">
          <Tinymce ref="editor" v-model="addForm.detail" :height="300" />
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
  getProduction,
  addProduction,
  editProduction,
  deleteProduction,
  getProductionCate
} from "@/api/website";
import Tinymce from "@/components/Tinymce";

export default {
  name: "ProductionIndex",
  components: { Tinymce },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === "") {
        this.$message({
          message: "请填写描述",
          type: "error"
        });
        callback(new Error("请填写描述"));
      } else {
        callback();
      }
    };
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
        cate_id: "",
        thumb: "",
        detail: "",
        sort: ""
      },
      addRules: {
        title: [
          {
            required: true,
            trigger: "blur",
            message: "请输入标题"
          }
        ],
        cate_id: [
          {
            required: true,
            trigger: "change",
            message: "请选择分类"
          }
        ],
        thumb: [
          {
            required: true,
            trigger: "blur",
            message: "请输入缩略图路径"
          }
        ],
        detail: [{ required: true, validator: validateRequire }],
        sort: [
          {
            required: true,
            trigger: "blur",
            message: "请输入序号"
          }
        ]
      },
      cateData: []
    };
  },
  mounted() {
    this.getTable();
    this.getCate();
  },
  methods: {
    getTable() {
      const params = {
        page: this.pageIndex
      };
      getProduction(params).then(res => {
        this.tableData = res.data;
        this.total = res.count;
      });
    },
    getCate() {
      const params = {
        // page: 1
      };
      getProductionCate(params).then(res => {
        this.cateData = res.data;
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
      this.visible = true;
      this.isAdd = false;
      this.addForm = {
        id: item.id,
        title: item.title,
        cate_id: item.cate_id,
        detail: item.detail,
        thumb: item.thumb,
        sort: item.sort
      };
      this.$nextTick(() => {
        this.$refs.editor?.setContent(item.detail);
      });
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
      const res = await deleteProduction({ id });
      this.$message.success(res.msg);
      this.getTable();
    },
    submit(formName) {
      this.loading = true;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.isAdd) {
            this.postAdd(this.addForm);
          } else {
            this.postEdit(this.addForm);
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
        cate_id: "",
        thumb: "",
        detail: "",
        sort: ""
      };
    },
    async postAdd(params) {
      try {
        await addProduction(params);
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
    async postEdit(params) {
      try {
        await editProduction(params);
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
      this.addForm.thumb = res.data;
    },
    beforeUpload(file) {
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
.page-production {
  padding: 20px 20px 50px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;

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
      min-width: 178px;
      min-height: 178px;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .logo {
      display: inline-block;
      width: 282px;
      height: 212px;
    }
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
  .input-images-row {
    display: flex;
    .el-button {
      margin-left: 30px;
    }
  }
}
</style>
