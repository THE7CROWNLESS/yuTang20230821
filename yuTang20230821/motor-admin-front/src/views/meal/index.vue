<template>
	<div class="page-meal">
		<div class="tool-bar">
			<el-button type="success" size="small" @click="handleAdd"> + 新增套餐</el-button>
		</div>
		<template>
			<el-table :data="mealData" tooltip-effect="dark" border style="width: 100%" stripe empty-text="暂无数据">
				<el-table-column align="center" label="套餐名" prop="title" />
				<el-table-column align="center" label="套餐ID" prop="id" />
				<el-table-column align="center" label="原始价格" prop="origin_price" />
				<el-table-column align="center" label="实际支付价格" prop="pay_price" />
				<el-table-column align="center" label="有效天数" prop="valid_day" />
				<el-table-column align="center" label="创建时间" prop="create_time" />
				<el-table-column align="center" label="操作" class="deal-column">
					<template slot-scope="scope">
						<el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
						<el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
					</template>
				</el-table-column>
			</el-table>
		</template>

    <el-pagination background layout="prev, pager, next" :page-size="20" :current-page="pageIndex"
      @current-change="handleCurrentChange" :total="total">
    </el-pagination>

	</div>
</template>




<script>
	import {
		getMeal,
		deleteMeal,
	} from "@/api/meal";

	export default {
		name: "MealIndex",
		data() {
			return {
				loading: false,
				pageIndex: 1,
				total: 0,
				mealData: [],
			};
		},
		mounted() {
			this.getMealData();
		},
		methods: {
			getMealData() {
				this.loading = true;
				const params = {
					page: this.pageIndex,
				};
				getMeal(params).then((res) => {
					this.mealData = res.data;
					this.total = res.count;
					this.loading = false;
				});
			},
			handleCurrentChange(index) {
				this.pageIndex = index;
				this.getMealData();
			},
			handleAdd(){
				this.$router.push({name: 'MealAdd',params: {id: 0}})
			},
			handleEdit(meal){
				this.$store.commit('meal/SET_EDIT_MEAL', meal)
				this.$router.push({name: 'MealEdit',params: {id: meal.id}})
			},			
			handleDelete(meal){
				this.$confirm('是否确认要删除?', '提示',{
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				})
				.then(() => {
					deleteMeal({
						id: meal.id,
					}).then((res) => {
						this.$message.success("删除成功")
						this.getMealData();
					}).catch((err) => {
							this.$message.error("删除失败")
					});
		
				}).catch(() => {
					this.$message.success("已取消删除")
				})

			},
		},
	};
</script>

<style lang="scss">
	.page-meal {
		padding: 20px 20px 50px;
		display: flex;
		flex-wrap: wrap;
		flex-direction: column;

	}

	.tool-bar{
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
