<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="校园资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="话题讨论"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_user/list', 'get')"
				:list="result_student_user_full_name"
				title="学生用户姓名"
				source_table="student_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_user/list', 'get')"
				:list="result_student_user_faculty"
				title="学生用户院系"
				source_table="student_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher_user/list', 'get')"
				:list="result_teacher_user_full_name"
				title="教师用户姓名"
				source_table="teacher_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/teacher_user/list', 'get')"
				:list="result_teacher_user_faculty"
				title="教师用户院系"
				source_table="teacher_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/college_information/list', 'get')"
				:list="result_college_information_college_name"
				title="学院信息学院名称"
				source_table="college_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/course_information/list', 'get')"
				:list="result_course_information_course_name"
				title="课程信息课程名称"
				source_table="course_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/course_information/list', 'get')"
				:list="result_course_information_major"
				title="课程信息专业"
				source_table="course_information"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_student_user_full_name":[],
			"result_student_user_faculty":[],
			"result_teacher_user_full_name":[],
			"result_teacher_user_faculty":[],
			"result_college_information_college_name":[],
			"result_course_information_course_name":[],
			"result_course_information_major":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取话题讨论
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取full_name
	 */
	get_student_user_full_name(){
		this.$get("~/api/student_user/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_user_full_name = json.result.list;
			result_student_user_full_name.map(o => o.title = o['full_name'])
	  			this.result_student_user_full_name = result_student_user_full_name
		 	}
		});
	},
	/**
	 * 获取faculty
	 */
	get_student_user_faculty(){
		this.$get("~/api/student_user/get_list?like=0", { page: 1, size: 10, "faculty": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_user_faculty = json.result.list;
			result_student_user_faculty.map(o => o.title = o['faculty'])
	  			this.result_student_user_faculty = result_student_user_faculty
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_teacher_user_full_name(){
		this.$get("~/api/teacher_user/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_user_full_name = json.result.list;
			result_teacher_user_full_name.map(o => o.title = o['full_name'])
	  			this.result_teacher_user_full_name = result_teacher_user_full_name
		 	}
		});
	},
	/**
	 * 获取faculty
	 */
	get_teacher_user_faculty(){
		this.$get("~/api/teacher_user/get_list?like=0", { page: 1, size: 10, "faculty": this.query.word }, (json) => {
		  if (json.result) {
			var result_teacher_user_faculty = json.result.list;
			result_teacher_user_faculty.map(o => o.title = o['faculty'])
	  			this.result_teacher_user_faculty = result_teacher_user_faculty
		 	}
		});
	},
	/**
	 * 获取college_name
	 */
	get_college_information_college_name(){
		this.$get("~/api/college_information/get_list?like=0", { page: 1, size: 10, "college_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_college_information_college_name = json.result.list;
			result_college_information_college_name.map(o => o.title = o['college_name'])
	  			this.result_college_information_college_name = result_college_information_college_name
		 	}
		});
	},
	/**
	 * 获取course_name
	 */
	get_course_information_course_name(){
		this.$get("~/api/course_information/get_list?like=0", { page: 1, size: 10, "course_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_information_course_name = json.result.list;
			result_course_information_course_name.map(o => o.title = o['course_name'])
	  			this.result_course_information_course_name = result_course_information_course_name
		 	}
		});
	},
	/**
	 * 获取major
	 */
	get_course_information_major(){
		this.$get("~/api/course_information/get_list?like=0", { page: 1, size: 10, "major": this.query.word }, (json) => {
		  if (json.result) {
			var result_course_information_major = json.result.list;
			result_course_information_major.map(o => o.title = o['major'])
	  			this.result_course_information_major = result_course_information_major
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_student_user_full_name();
		this.get_student_user_faculty();
		this.get_teacher_user_full_name();
		this.get_teacher_user_faculty();
		this.get_college_information_college_name();
		this.get_course_information_course_name();
		this.get_course_information_major();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_student_user_full_name();
	  this.get_student_user_faculty();
	  this.get_teacher_user_full_name();
	  this.get_teacher_user_faculty();
	  this.get_college_information_college_name();
	  this.get_course_information_course_name();
	  this.get_course_information_major();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
