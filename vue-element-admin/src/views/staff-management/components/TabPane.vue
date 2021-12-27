<template>
  <div class="tabPane">
    <el-table :data="list" border fit highlight-current-row style="width: 100%">

      <el-table-column
        v-loading="loading"
        align="center"
        label="头像"
        width="100"
        element-loading-text="请给我点时间！"
      >
        <template slot-scope="scope">
          <img v-if="scope.row.avatar != null && scope.row.avatar != ''" class="avatar_img" v-model="scope.row.avatar" :src="'http://localhost:8080/download/image/' + scope.row.avatar">
          <div v-else class="block"><el-avatar :size="60" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar></div>
        </template>
      </el-table-column>


      <el-table-column width="120px" align="center" label="用户名">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="密码">
        <template slot-scope="scope">
          <span>{{ scope.row.password }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="姓名">
        <template slot-scope="scope">
          <span>{{ scope.row.realName }}</span>
        </template>
      </el-table-column>

      <el-table-column width="80" align="center" label="性别">
        <template slot-scope="scope">
          <span>{{ scope.row.sex == 1 ? "男" : "女" }}</span>
        </template>
      </el-table-column>

      <el-table-column width="80px" align="center" label="年龄">
        <template slot-scope="scope">
          <span>{{ scope.row.age }}</span>
        </template>
      </el-table-column>


      <el-table-column class-name="status-col" label="手机号" width="120px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phoneNumber }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="邮箱" width="180px" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="操作" min-width="250px" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" @click="updateUserInfo(scope.row.id)">修改</el-button>
          <el-popconfirm
            confirm-button-text='确定'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="您确定要删除这个员工吗？"
            @onConfirm="deleteStaff(scope.row.id)"
            style="margin-left: 10px"
          >
            <el-button v-show="type === 'GC'" slot="reference"  type="danger" icon="el-icon-delete">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div class="add">
      <el-tooltip :enterable="false" class="item" effect="dark" content="添加员工" placement="right">
        <el-button icon="el-icon-circle-plus-outline" @click="addUserInfo()" v-show="type === 'GC'" round></el-button>
      </el-tooltip>
    </div>

      <el-pagination
        layout="prev, pager, next"
        :total="total"
        :page-size="listQuery.limit"
        @current-change="handleCurrentChange"
        :current-page.sync="listQuery.page"
        style="text-align: center;margin-top: 15px;"
      >
      </el-pagination>

    <el-dialog
      title="修改信息"
      @close="resetForm"
      :visible.sync="dialogVisible"
      width="50%"
      top="20px">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="头　像" prop="avatar">
          <el-upload class="avatar-uploader"
                     action="/upload/image" :show-file-list="false"
                     :on-change="beforeAvatarUpload" :auto-upload="false" ref="upload"
                     :on-success="successUpload">
            <img v-model="nowAvatar" v-if="nowAvatar != null && nowAvatar !== ''" :src="nowAvatar"
                 class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item hidden label="Id" prop="id">
          <el-input v-model="ruleForm.id"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密　码" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="姓　名" prop="realName">
          <el-input v-model="ruleForm.realName"></el-input>
        </el-form-item>
        <el-form-item label="性　别" prop="sex">
          <el-radio-group v-model="ruleForm.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年　龄" prop="age">
          <el-input type="age" v-model.number="ruleForm.age"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNumber">
          <el-input v-model="ruleForm.phoneNumber"></el-input>
        </el-form-item>
        <el-form-item label="邮　箱" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" v-if="op == 1" @click="submitAddForm('ruleForm')">添加</el-button>
          <el-button type="primary" v-if="op == 2" @click="submitUpdateForm('ruleForm')">修改</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {getRoleUserInfo, getUserInfoById,updateUserInfoBy,addUserInfoBy,deleteStaff} from '@/api/userInfo'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  props: {
    type: {
      type: String,
      default: 'AC'
    }
  },
  data() {
    return {
      list: null,
      listQuery: {
        page: 1,
        limit: 5,
        role: this.type,
        sort: '+id'
      },
      total:0,
      dialogVisible: false,
      ruleForm: {
        id:0,
        avatar:'',
        username:'',
        password:'',
        realName:'',
        age:'',
        sex:1,
        email:'',
        phoneNumber:''
      },
      imgUrl: '',
      op:0,
      rules: {
        avatar: [{
          message: '请选择头像',
          trigger: 'blur'
        }],
        username: [{
          required: true,
          message: '请输入昵称',
          trigger: 'blur'
        },
          {
            min: 1,
            max: 10,
            message: '长度在 3 到 10 个字符',
            trigger: 'blur'
          }
        ],
        password: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        },
          {
            min: 1,
            max: 10,
            message: '长度在 6 到 10 个字符',
            trigger: 'blur'
          }
        ],
        realName: [
          {
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          },
          {
            min: 2,
            max: 10,
            message: '长度在 2 到 10 个字符',
            trigger: 'blur'
          }],
        age: [{
          required: true,
          message: '年龄不能为空'
        },
          {
            type: 'number',
            message: '年龄必须为数字值'
          }
        ],
        sex: [{
          required: true,
          message: '请设置性别',
          trigger: 'change'
        }],
        phoneNumber: [{
          required: true,
          message: '手机号不能为空',
          trigger: 'blur'
        },
          {
            min: 11,
            max: 11,
            message: '请输入11位正确手机号',
            trigger: 'blur'
          }
        ],
        email: [{
          required: true,
          message: '请输入邮箱地址',
          trigger: 'blur'
        },
          {
            type: 'email',
            message: '请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ]
      },
      nowAvatar:'',
      loading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      getRoleUserInfo(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total;
        this.loading = false
      })
    },
    resetForm(){
      this.$refs.ruleForm.resetFields();
      this.nowAvatar = '';
    },
    handleCurrentChange(val){
      this.listQuery.page = val;
      this.getList();
    },
    addUserInfo(){
      this.dialogVisible = true;
      this.op = 1;
    },
    updateUserInfo(id) {
      this.dialogVisible = true;
      this.op = 2;
      getUserInfoById({id}).then(response => {
        this.ruleForm = response.data;
        this.imgUrl = this.ruleForm.avatar;
        if (this.ruleForm.avatar == null){
          this.$set(this.ruleForm,'avatar',this.ruleForm.avatar)
        } else {
          this.nowAvatar = "http://localhost:8080/download/image/" + this.ruleForm.avatar;
        }

      })
    },
    submitUpdateForm(formData){
      this.$refs[formData].validate((valid) => {
        if (valid) {
          this.$refs.upload.submit();
          setTimeout(() => {
            updateUserInfoBy(this.ruleForm).then(response => {
              if (response.state === 0) {
                this.$message({
                  showClose: true,
                  message: response.msg,
                  type: 'success'
                });
              } else {
                this.$message({
                  showClose: true,
                  message: response.msg,
                  type: 'warning'
                });
              }
              this.dialogVisible = false;
              this.getList();
            }).catch(e => {
              console.log(e)
              this.dialogVisible = false;
            });
          }, 1000);
        } else {
          return false;
        }
      });
    },
    submitAddForm(formData){
      this.$refs[formData].validate((valid) => {
        if (valid) {
          console.log(this.ruleForm)
          this.$refs.upload.submit();
          setTimeout(() => {
            addUserInfoBy(this.ruleForm).then(response => {
              if (response.state === 0) {
                this.getList();
                this.$message({
                  showClose: true,
                  message: response.msg,
                  type: 'success'
                });
              } else {
                this.$message({
                  showClose: true,
                  message: response.msg,
                  type: 'warning'
                });
              }
              this.dialogVisible = false;
              this.getList();
            }).catch(e => {
              console.log(e)
              this.dialogVisible = false;
            });
          }, 1000);
        } else {
          return false;
        }
      });
    },
    deleteStaff(id){
      deleteStaff({id}).then(response => {
        if (response.state === 0) {
          this.getList();
          this.$message({
            showClose: true,
            message: response.msg,
            type: 'success'
          });
        } else {
          this.$message({
            showClose: true,
            message: response.msg,
            type: 'warning'
          });
        }
        this.getList();
      }).catch(e =>{
        console.log(e);
      })
    },
    successUpload(response, file, fileList) {
      this.ruleForm.avatar = response.data;
    },
    beforeAvatarUpload(file, fileList) {
      const isJPG = file.raw.type === 'image/jpeg';
      const isPNG = file.raw.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;
      console.log(file)
      if (!isJPG && !isPNG) {
        this.$message.error('上传头像图片不是 JPG、PNG 格式!');
      } else if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      } else {
        this.nowAvatar = URL.createObjectURL(file.raw);
      }

      return isJPG && isLt2M;
    }
  }
}
</script>
<style scoped>
  .tabPane >>> .avatar-uploader .el-upload {
    margin-left: 15px;
    border: 1px dashed #d9d9d9;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    border-radius: 50px;
    margin-bottom: 10px;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 80px;
    height: 80px;
    line-height: 80px;
    text-align: center;
  }

  .avatar {
    width: 80px;
    height: 80px;
    display: block;
  }

  .add{
    margin-top: 15px;
    text-align: center;
  }

  .add >>> .el-button{
    width: 300px;
    padding:5px 10px;
  }

  .add >>> .el-icon-circle-plus-outline{
    font-size: 25px;
  }

  .avatar_img{
    width: 60px;
    height: 60px;
    border-radius: 50px;
  }
</style>

