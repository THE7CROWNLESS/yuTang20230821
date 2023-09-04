<template>
	<div class="edit-meal">

		<el-form ref="form" label-position="left" :model="form" :rules="rules" label-width="200px">
			<el-form-item label="套餐标题" prop="title">
				<el-input v-model.trim="form.title" minlength="1" maxlength="30" placeholder="请输入30个字符以内的标题" />
			</el-form-item>

			<el-form-item label="原始价格" prop="origin_price">
				<el-input-number v-model="form.origin_price" :min="1" :controls="false" label="请输入原始价格">
				</el-input-number>
				元
			</el-form-item>

			<el-form-item label="实际支付价格" prop="pay_price">
				<el-input-number v-model="form.pay_price" :min="1" :controls="false" label="请输入实际支付价格">
				</el-input-number>
				元
			</el-form-item>

			<el-form-item label="有效期天数" prop="valid_day">
				<el-input-number v-model="form.valid_day" :min="1" :controls="false" label="请输入有效果天数"></el-input-number>
				天
			</el-form-item>

			<el-form-item class="submit-item">
				<el-button @click="resetForm('form')">重置</el-button>
				<el-button type="primary" :disabled="loading" @click="submitForm('form')">提交</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	import {
		editMeal,
	} from "@/api/meal";

	import {
		mapState
	} from 'vuex';

	export default {
		data() {
			return {
				form: {
					id: 0,
					title: '',
					origin_price: 0,
					pay_price: 0,
					valid_day: 0,
				},
				rules: {
					title: [{
							required: true,
							message: '请输入命令',
							trigger: 'blur'
						},
						{
							min: 1,
							max: 30,
							message: '长度在 1 到 30 个字符',
							trigger: 'blur'
						}
					],
					origin_price: [{
						required: true,
						message: '请输入原始价格',
						trigger: 'blur'
					}, ],
					pay_price: [{
						required: true,
						message: '请输入实际支付价格',
						trigger: 'blur'
					}, ],
					valid_day: [{
						required: true,
						message: '请输入有效果天数',
						trigger: 'blur'
					}, ],
				},
				loading: false
			}
		},
		mounted() {
			this.form = JSON.parse(JSON.stringify(this.meal))
		},
		computed: {
			...mapState('meal', ['meal'])
		},
		methods: {
			submitForm(formName) {
				this.loading = true;
				this.$refs[formName].validate((valid) => {
					if (valid) {
						delete this.form.create_time;
						editMeal(this.form)
							.then((res) => {
								this.$message.success("编辑成功");
								this.loading = false;
								this.$router.push('/meal/index')
							})
							.catch((_) => {
								this.loading = false;
								this.$message.error("编辑失败");
							});
					} else {
						console.log('error submit!!');
						this.loading = false;
						return false;
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			}
		}
	}
</script>

<style scoped lang="scss">
	.el-form {
		padding: 0 30px;

		.el-input,
		.el-input-number {
			width: 300px;
			margin-right: 20px;
		}

		.submit-item {
			margin-top: 50px;
		}
	}
</style>
