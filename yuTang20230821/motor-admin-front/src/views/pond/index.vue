<template>
	<div class="page-report">

		<template>
			<el-table :data="pondList" tooltip-effect="dark" border style="width: 100%" stripe empty-text="暂无数据">
				<el-table-column align="center" label="鱼塘名称" prop="title" />
				<el-table-column align="center" label="创建时间" prop="create_time" />
                <el-table-column label="鱼塘的设备" align="center">
					<template slot-scope="scope">
						<span v-for="(item, index) of scope.row.userMachine" :key="item.machine_id">
							{{item.machine_name}}
							<i v-if="index + 1 != scope.row.userMachine.length">、</i>
						</span>
						
					</template>
				</el-table-column>
				<el-table-column align="center" label="鱼种" prop="breed" />
				<el-table-column align="center" label="投苗时间" prop="put_date" />
				<el-table-column align="center" label="规格 （/斤）" prop="norm" />
				<el-table-column align="center" label="面积 （/亩）" prop="acreage" />
				<el-table-column align="center" label="密度 （尾/亩）" prop="density" />
			</el-table>
		</template>

    <el-pagination background layout="prev, pager, next" :page-size="20" :current-page="pageIndex"
      @current-change="handleCurrentChange" :total="total">
    </el-pagination>

	</div>
</template>




<script>
	import {
		getPond
	} from "@/api/pond";

	export default {
		name: "PondIndex",
		data() {
			return {
				loading: false,
				pageIndex: 1,
				total: 0,
				pondList: [],
			};
		},
		mounted() {
			this.getPondData();
		},
		methods: {
			getPondData() {
				this.loading = true;
				const params = {
					page: this.pageIndex,
				};
				getPond(params).then((res) => {
					this.pondList = res.data;
					this.total = res.count;
					this.loading = false;
				});
			},
			handleCurrentChange(index) {
				this.pageIndex = index;
				this.getPondData();
			},

		},
	};
</script>

<style lang="scss">
	.page-report {
		padding: 20px 20px 50px;
		display: flex;
		flex-wrap: wrap;
		flex-direction: column;

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
