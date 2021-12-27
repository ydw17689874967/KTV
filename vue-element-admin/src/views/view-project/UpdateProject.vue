<template>
  <div class="content">
    &nbsp
    <el-form style="width: 700px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
             class="demo-ruleForm"
    >
      <el-form-item label="审核标题" prop="title">
        <el-input v-model="ruleForm.title" disabled="disabled">

        </el-input>
      </el-form-item>
      <el-form-item label="内容描述" prop="desc">
        <el-input
          type="textarea"
          :rows="5"
          placeholder="请输入内容"
          v-model="ruleForm.desc"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="项目类型" prop="type">
        <el-input v-model="ruleForm.type" disabled="disabled">

        </el-input>
      </el-form-item>
      <el-form-item label="预计花费" prop="itemCost">
        <el-input v-model.number="ruleForm.itemCost" ></el-input>
      </el-form-item>
      <el-form-item label="预计盈利" prop="itemProfit" >
        <el-input v-model.number="ruleForm.itemProfit"></el-input>
      </el-form-item>
      <el-form-item label="文件名称" prop="video" disabled="disabled">
        <el-input v-model="ruleForm.video" disabled="disabled"></el-input>
      </el-form-item>
      <el-upload
        align="center"
        class="upload-demo"
        drag
        multiple
        action="/upload/video"
        :on-change="beforeVideoUpload"  ref="upload"
        :on-success="successUpload">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      </el-upload>
      <el-form-item style="text-align: center" >
        <el-button type="primary" @click="submitForm('ruleForm')">提交修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { apply } from '@/api/project'
import { getProDetail } from '@/api/project'
import { updateVideo } from '@/api/project'

export default {
  data() {
    return {
      list:null,
      ruleForm: {
        id:'',
        title: '',
        desc: '',
        type: '',
        itemCost: '',
        itemProfit: '',
        video:'',
        options: [{
          value: '微信小程序',
          label: '微信小程序'
        }, {
          value: 'web项目',
          label: 'web项目'
        }, {
          value: '游戏',
          label: '游戏'
        }]
      },
      rules: {
        title: [
          { required: true, message: '请输入申请项目标题', trigger: 'blur' },
          { min: 5, max: 10, message: '长度在 5 到 10 个字符', trigger: 'blur' }
        ],
        desc: [
          { required: true, message: '请输入申请项目描述', trigger: 'blur' }
        ],
        options: [
          { required: true, message: '请选择项目类型', trigger: 'change' }
        ],
        itemCost: [
          { required: true, message: '金额不能为空'},
          { type: 'number', message: '金额必须为数字值'}, // 且不包括小数
        ],
        itemProfit: [
          { required: true, message: '金额不能为空'},
          { type: 'number', message: '金额必须为数字值'}, // 且不包括小数
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //访问project/apply
          updateVideo({
            id:this.$route.query.id,
            desc: this.ruleForm.desc,
            itemCost: this.ruleForm.itemCost,
            itemProfit: this.ruleForm.itemProfit,
            video: this.ruleForm.video,
          }).then(date => {
            alert("操作成功")
            this.$router.push('/view/project/ListProject')
          }).catch(e => {
            console.log(e)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    success() {
      this.$message({
        message: '申请发送成功',
        type: 'success'
      })
      alert("修改成功")
    },
    error() {
      this.$message({
        message: '申请发送失败',
        type: 'error'
      })
    },
    setDefaultType() {
      this.ruleForm.type = this.ruleForm.options[0].value
    },
    getProDetail(id) {
      console.log("this.$route.query.id"+this.$route.query.id)
      id=this.$route.query.id
      getProDetail(id).then(response =>{
        console.log(response.data)
        this.list=response.data
        console.log(this.list)
        this.ruleForm.title=this.list.title
        this.ruleForm.desc=this.list.desc
        this.ruleForm.type=this.list.type
        this.ruleForm.itemCost=this.list.itemCost
        this.ruleForm.itemProfit=this.list.itemProfit
        this.ruleForm.video=this.list.video
      })
    },
    beforeVideoUpload(file){
      console.log(file)
      const isMP4 = file.raw.type === 'video/mp4';
      const isLt2G = file.size / 1024 / 1024 / 1024 < 2;
      if (!isMP4) {
        this.$message.error('请上传格式为mp4的视频');
      } else if (!isLt2G) {
        this.$message.error('上传视频不可大于2GB');
      }
      return isMP4 && isLt2G;
    },
    successUpload(response, file, fileList){
      console.log("response.data"+response.data)
      this.ruleForm.video=response.data
      console.log(this.ruleForm.video)
    }
  },
  created() {
    this.setDefaultType()
    this.getProDetail()
  }
}
</script>

<style scoped>
.content {
  width: 100%;
  height: 600px;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
