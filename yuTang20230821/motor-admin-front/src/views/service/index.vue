<template>
	<div class="page-service">

		<template>
			<el-table :data="serviceData" tooltip-effect="dark" border style="width: 100%" stripe empty-text="暂无数据">
				<el-table-column align="center" label="设备名称" prop="machine_name" />
				<el-table-column align="center" label="设备ID" prop="machine_code" />
				<el-table-column align="center" label="设备问题" prop="content" />
				<el-table-column align="center" label="创建时间" prop="create_time" />
				<el-table-column align="center" label="联系人" prop="contact" />
				<el-table-column align="center" label="手机号" prop="phone" />
				<el-table-column align="center" label="微信昵称">
					<template slot-scope="scope">
						{{scope.row.userInfo.nickname}}
					</template>
				</el-table-column>
				<el-table-column align="center" label="微信手机号">
					<template slot-scope="scope">
						{{scope.row.userInfo.phone}}
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
		getService
	} from "@/api/service";

	export default {
		name: "ServiceIndex",
		data() {
			return {
				loading: false,
				pageIndex: 1,
				total: 0,
				serviceData: [],
			};
		},
		mounted() {
			this.getServiceData();
		},
		methods: {
			getServiceData() {
				this.loading = true;
				const params = {
					page: this.pageIndex,
				};
				getService(params).then((res) => {
					this.serviceData = res.data;
					this.total = res.count;
					this.loading = false;
				});
			},
			handleCurrentChange(index) {
				this.pageIndex = index;
				this.getServiceData();
			},

		},
	};
</script>

<style lang="scss">
	.page-service {
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
