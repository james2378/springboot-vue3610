<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','college_name') || $check_field('add','college_name') || $check_field('set','college_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学院名称" prop="college_name">
					<el-input id="college_name" v-model="form['college_name']" placeholder="请输入学院名称"
							  v-if="user_group === '管理员' || (form['college_information_id'] && $check_field('set','college_name')) || (!form['college_information_id'] && $check_field('add','college_name'))" :disabled="disabledObj['college_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','college_name')">{{form['college_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','college_address') || $check_field('add','college_address') || $check_field('set','college_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学院地址" prop="college_address">
					<el-input id="college_address" v-model="form['college_address']" placeholder="请输入学院地址"
							  v-if="user_group === '管理员' || (form['college_information_id'] && $check_field('set','college_address')) || (!form['college_information_id'] && $check_field('add','college_address'))" :disabled="disabledObj['college_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','college_address')">{{form['college_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_number') || $check_field('add','contact_number') || $check_field('set','contact_number')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系电话" prop="contact_number">
					<el-input id="contact_number" v-model="form['contact_number']" placeholder="请输入联系电话"
							  v-if="user_group === '管理员' || (form['college_information_id'] && $check_field('set','contact_number')) || (!form['college_information_id'] && $check_field('add','contact_number'))" :disabled="disabledObj['contact_number_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_number')">{{form['contact_number']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','college_pictures') || $check_field('add','college_pictures') || $check_field('set','college_pictures')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学院图片" prop="college_pictures">
					<el-upload :disabled="disabledObj['college_pictures_isDisabled']" id="college_pictures" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_college_pictures"
						:show-file-list="false" v-if="user_group === '管理员' || (form['college_information_id'] && $check_field('set','college_pictures')) || (!form['college_information_id'] && $check_field('add','college_pictures'))">
						<img v-if="form['college_pictures']" :src="$fullUrl(form['college_pictures'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','college_pictures')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['college_pictures'])" :preview-src-list="[$fullUrl(form['college_pictures'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','college_introduction') || $check_field('add','college_introduction') || $check_field('set','college_introduction')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学院介绍" prop="college_introduction">
					<el-input type="textarea" id="college_introduction" v-model="form['college_introduction']" placeholder="请输入学院介绍"
						v-if="user_group === '管理员' || (form['college_information_id'] && $check_field('set','college_introduction')) || (!form['college_information_id'] && $check_field('add','college_introduction'))" :disabled="disabledObj['college_introduction_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','college_introduction')">{{form['college_introduction']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "college_information_id",
				url_add: "~/api/college_information/add?",
				url_set: "~/api/college_information/set?",
				url_get_obj: "~/api/college_information/get_obj?",
				url_upload: "~/api/college_information/upload?",

				query: {
					"college_information_id": 0,
				},

				form: {
					"college_name":'', // 学院名称
					"college_address":'', // 学院地址
					"contact_number":'', // 联系电话
					"college_pictures":'', // 学院图片
					"college_introduction":'', // 学院介绍
					"college_information_id": 0, // ID

				},
				disabledObj:{
					"college_name_isDisabled": false,
					"college_address_isDisabled": false,
					"contact_number_isDisabled": false,
					"college_pictures_isDisabled": false,
					"college_introduction_isDisabled": false,
				},

			}
		},
		methods: {
			/**
			 * 上传学院图片
			 * @param {Object} param图片参数
			 */
			upload_college_pictures(param){
				this.uploadFile(param.file, "college_pictures");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/college_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/college_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/college_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/college_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/college_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
