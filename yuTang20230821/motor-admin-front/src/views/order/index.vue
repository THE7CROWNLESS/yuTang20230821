<template>
	<div class="page-order">

		<template>
			<el-table :data="orderData" tooltip-effect="dark" border style="width: 100%" stripe empty-text="暂无数据">

				<el-table-column align="center" label="订单号" prop="out_trade_no" />
				<el-table-column align="center" label="支付价格" prop="price" />
				<el-table-column align="center" label="有效期天数" prop="valid_day" />
				<el-table-column align="center" label="创建时间" prop="create_time" />
				<el-table-column align="center" label="用户ID">
					<template slot-scope="scope">
						{{scope.row.user.id}}
					</template>
				</el-table-column>
				<el-table-column align="center" label="用户名">
					<template slot-scope="scope">
						{{scope.row.user.username}}
					</template>
				</el-table-column>
				<el-table-column align="center" label="用户手机">
					<template slot-scope="scope">
						{{scope.row.user.phone}}
					</template>
				</el-table-column>
				<el-table-column align="center" label="设备ID">
					<template slot-scope="scope">
						{{scope.row.machine.machine_code}}
					</template>
				</el-table-column>
				<el-table-column align="center" label="设备名称">
					<template slot-scope="scope">
						{{scope.row.machine.machine_name}}
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
		getOrder
	} from "@/api/order";

	export default {
		name: "OrderIndex",
		data() {
			return {
				loading: false,
				pageIndex: 1,
				total: 0,
				orderData: [],
			};
		},
		mounted() {
			this.getOrderData();
		},
		methods: {
			getOrderData() {
				this.loading = true;
				const params = {
					page: this.pageIndex,
				};
				getOrder(params).then((res) => {
					this.orderData = res.data;
					this.total = res.count;
					this.loading = false;
				});
			},
			handleCurrentChange(index) {
				this.pageIndex = index;
				this.getOrderData();
			},

		},
	};
</script>

<style lang="scss">
	.page-order {
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
