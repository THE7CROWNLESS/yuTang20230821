<template>
  <div class="page-production">
    <div class="tool-bar">
      <el-button type="success" size="small" @click="handleAdd">
        + 新增</el-button
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
      <el-table-column
        align="center"
        label="名称"
        prop="title"
        show-overflow-tooltip
      />
      <el-table-column align="center" label="ID" prop="id" width="80px" />
      <el-table-column align="center" label="排序" prop="sort" width="80px" />
      <el-table-column align="center" label="分类" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ setCate(scope.row.cate_id) }}
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
      :title="isAdd ? '新增方案' : '编辑方案'"
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
        <el-form-item label="所属分类" prop="cate_id">
          <el-select v-model="addForm.cate_id" placeholder="请选择分类">
            <el-option
              v-for="item in cateData"
              :key="item.id"
              :label="item.title"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="方案标题" prop="title">
          <el-input v-model="addForm.title" placeholder="请输入标题" />
        </el-form-item>

        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="addForm.sort" :min="1" label="排序" />
        </el-form-item>
        <el-form-item label="详情" prop="detail">
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
  getArticleCate,
  getArticle,
  addArticle,
  editArticle,
  deleteArticle
} from "@/api/website";
import Tinymce from "@/components/Tinymce";

export default {
  name: "ArticleIndex",
  components: { Tinymce },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === "") {
        this.$message({
          message: "请填写详情",
          type: "error"
        });
        callback(new Error("请填写详情"));
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
      cateData: [],
      addForm: {
        pid: 2,
        id: "",
        title: "",
        cate_id: "",
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
        detail: [{ required: true, validator: validateRequire }],
        sort: [
          {
            required: true,
            trigger: "blur",
            message: "请输入序号"
          }
        ]
      }
    };
  },
  mounted() {
    this.getCate();
    this.getTable();
  },
  methods: {
    getCate() {
      getArticleCate({ pid: 2 }).then(res => {
        this.cateData = res.data;
      });
    },
    setCate(cateId) {
      const macth = this.cateData.find(x => {
        return x.id === cateId;
      });
      return macth ? macth.title : "无";
    },
    getTable() {
      const params = {
        page: this.pageIndex,
        pid: 2
      };
      getArticle(params).then(res => {
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
      this.visible = true;
      this.isAdd = false;
      this.addForm = {
        pid: 2,
        id: item.id,
        title: item.title,
        cate_id: item.cate_id,
        detail: item.detail,
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
      const res = await deleteArticle({ id });
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
        pid: 2,
        id: "",
        title: "",
        cate_id: "",
        detail: "",
        sort: ""
      };
    },
    async postAdd(params) {
      try {
        await addArticle(params);
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
        await editArticle(params);
        this.$message.success("修改成功");
        this.loading = false;
        this.visible = false;
        this.getTable();
      } catch (error) {
        this.loading = false;
        this.visible = false;
        this.$message.error("修改失败");
      }
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
</style>
