<template>
  <div class="project_detail">
    <div>
      <el-card class="box-card" body-style="padding:0px 10px">
        <div slot="header" class="clearfix">
          <span>{{ projectDetail.title }}</span>
        </div>
        <div class="project_info">
          <ul>
            <li>
              <div>内容描述：</div>
              <div>{{ projectDetail.desc }}</div>
            </li>
            <li>
              <div>申请类型：
                <el-tag type="success">{{ projectDetail.type }}</el-tag>
              </div>
            </li>
            <li>
              <div>预计花费：{{ projectDetail.itemCost }}</div>
            </li>
            <li>
              <div>预计盈利：{{ projectDetail.itemProfit }}</div>
            </li>
            <li>
              <div>申请时间：{{ projectDetail.createTime }}</div>
            </li>
            <li>
              <div>更改时间：{{ projectDetail.lastUpdateTime }}</div>
            </li>
            <li>
              <div>项目进程：{{ projectDetail.status }}</div>
            </li>
          </ul>
        </div>
        <div v-if="userPermissionNames.indexOf('op:submit')!=-1 && projectDetail.status=='项目进行中'">
          <div v-if="videoPath" style="margin-left: 40px">录屏文件:{{this.videoPath}}</div>
          <div v-else>
            <el-upload
              class="upload-demo"
              drag
              multiple
              action="/upload/video"
              :on-change="beforeVideoUpload"  ref="upload"
              :on-success="successUpload">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
              <el-button type="primary" plain round @click="submitVideo">上传视频</el-button>
            <el-button type="primary" plain round @click="selectProjectVideo">查看是否有录屏</el-button>
          </div>
        </div>
        <el-divider></el-divider>
        <div class="opProject">
          <el-button v-if="userPermissionNames.indexOf('op:submit')!=-1 && projectDetail.status=='项目进行中' "
                     @click="dialogVisibleTwo = true" size="mini" type="primary" round plain>任务分配
          </el-button>
          <el-button type="primary"
                     v-if="userPermissionNames.indexOf('op:submit')!=-1 && projectDetail.status=='项目进行中' "
                     @click="dialogVisible = true" size="mini" round plain>提交项目
          </el-button>
        </div>
      </el-card>
    </div>
    <el-dialog title="提示" :visible.sync="dialogVisibleTwo" width="30%">
      <div>
        小组人员：
        <el-select v-model="value" @change="getSelectId" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.realName"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </div>
      <div>
        任务内容：
        <el-input
          type="textarea"
          :rows="2"
          placeholder="请输入内容"
          v-model="textarea"
          style="margin-top: 20px"
        >
        </el-input>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisibleTwo = false">取 消</el-button>
            <el-button type="primary" @click="addTask">确 定</el-button>
          </span>
    </el-dialog>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <span>确定提交项目吗</span>
      <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitProject">确 定</el-button></span>
    </el-dialog>
  </div>
</template>

<script>

  import { detailProject, submitProject, submitProjectVideo } from '@/api/project'
import state from '@/store/modules/user'
import {getUserInfoOfGroup, addTask} from '@/api/userInfoGroup'
  import {selectVideo} from '@/api/untreated-project'

export default {
  name: 'ViewDetail',
  data() {
    return {
      projectDetail: '',
      userPermissionNames: '',
      dialogVisible: false,
      dialogVisibleTwo: false,
      options: '',
      value: '',
      textarea: '',
      selectedUid: '',
      videoPath: ''
    }
  },
  methods: {
    getSelectId(val) {
      this.selectedUid = val
    },
    addTask() {
      this.dialogVisibleTwo = false
      addTask({userId: this.selectedUid, context: this.textarea, projectId: this.projectDetail.id}).then(response => {
        if (response.state == 0) {
          this.$message({
            message: '提交成功',
            type: 'seccess'
          })
          this.textarea = ''
        }
      }).catch(e => {
        console.log(e)
      })
    },
    selectProjectVideo(){
      selectVideo({pid:this.$route.query.id}).then(response=>{
        this.videoPath=response.data.video
      }).catch(e=>{
        console.log(e)
      })
    },
    submitVideo(){
      submitProjectVideo({videoPath:this.projectDetail.videoPath,pid:this.projectDetail.id}).then(response=>{
        console.log(response)
        if (response.state == 0){
          this.$message({
            message: '提交视频成功',
            type:'success'
          })
        }
      }).catch(e=>{
        console.log(e)
      })
      this.selectProjectVideo()
    },
    submitProject() {
      submitProject({id: this.projectDetail.id}).then(response => {
        console.log(response)
        this.dialogVisible = false
        if (response.state == 0) {
          this.getDetail()
          this.$message({
            message: '提交成功',
            type: 'seccess'
          })
        }
      }).catch(e => {
        console.log(e)
      })
    },
    getDetail() {
      detailProject(
        {id: this.$route.query.id}
      ).then(response => {
        console.log(response)
        this.projectDetail = response.data
      }).catch(e => {
        console.log(e)
      })
    },
    getGroupUserInfo() {
      getUserInfoOfGroup().then(response => {
        console.log(response)
        this.options = response.data
      }).catch(e => {
        console.log(e)
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
      console.log(response)
      if (response.data!=null){
      this.projectDetail.videoPath=response.data;}
      console.log(this.projectDetail.videoPath)
    }
  },
  created() {
    this.userPermissionNames = state.state.roles
    if (this.userPermissionNames.indexOf('op:submit') != -1) {
      this.getGroupUserInfo()
    }
    this.getDetail()
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix {
  text-align: center;
  font-size: 24px;
  font-family: cursive;
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 800px;
  margin: 55px 0px;
}

.project_detail {
  display: flex;
  justify-content: center;
  align-items: center;
}

.project_info > ul {
  list-style-type: none;
}

.project_info > ul > li {
  padding: 10px 0;
}

.project_info > ul > li:first-child {
  display: flex;
  line-height: 30px;
}

.project_info > ul > li:first-child > div:first-child {
  width: 80px;
}
.project_info > ul > li:first-child > div:last-child{
  width: 640px;
  padding-right: 40px;
}


.opProject {
  text-align: center;
  margin-bottom: 24px;
}

.opProject >>> .el-button {
  font-size: 16px;
}
</style>
