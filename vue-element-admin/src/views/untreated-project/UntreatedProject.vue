<template>
  <div class="untreated">
    <template v-if="roleId==3">
      <el-table
        :data="project"
        border
        style="width: 95%;margin: 0px auto"
        :header-cell-style="{textAlign: 'center'}"
        :cell-style="{textAlign: 'center'}">
        <el-table-column
          prop="projectId"
          label="编号"
          width="50px">
        </el-table-column>
        <el-table-column
          prop="title"
          label="项目标题"
          width="180px">
        </el-table-column>
        <el-table-column
          prop="type"
          label="项目类型"
          width="180px">
        </el-table-column>
        <el-table-column
          prop="desc"
          label="项目详情" show-overflow-tooltip>
        </el-table-column>
        <el-table-column
          prop="groupName"
          label="小组名称"
          width="150px">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="项目时间"
          width="150px">
        </el-table-column>
        <el-table-column
          label="成本金额"
          width="120px">
          <template slot-scope="scope">
            {{scope.row.itemCost}}元
          </template>
        </el-table-column>
        <el-table-column
          label="预计利润"
          width="120px">
          <template slot-scope="scope">
            {{scope.row.itemProfit}}元
          </template>
        </el-table-column>
        <el-table-column label="操作" width="100px">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="primary"
            @click="pass(scope.row.projectId)">拨款</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        @current-change="handleCurrentChange"
        :page-size="this.pageSize"
        layout="total, prev, pager, next"
        :total="this.total"
        style="text-align: center;margin-top: 20px;">
      </el-pagination>
    </template>
    <el-tabs type="border-card" style="width: 95%;margin: auto" v-model="activeName" @tab-click="audit" v-else>
      <el-tab-pane label="待审批" name="0">
        <el-table
          :data="project"
          border
          style="width: 100%;margin: 0px auto"
          :header-cell-style="{textAlign: 'center'}"
          :cell-style="{textAlign: 'center'}">
          <el-table-column
            prop="projectId"
            label="编号"
            width="50px">
          </el-table-column>
          <el-table-column
            prop="title"
            label="项目标题"
            width="180px">
          </el-table-column>
          <el-table-column
            prop="type"
            label="项目类型"
            width="180px">
          </el-table-column>
         <!-- <el-table-column
            prop="desc"
            label="项目详情">
          </el-table-column>-->
          <el-table-column prop="desc" width="" align="center" label="描述" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.desc === ''">-</span>
              <span v-else>{{ scope.row.desc }}</span>
            </template>
          </el-table-column>

          <el-table-column
            prop="groupName"
            label="小组名称"
            width="150px">
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="项目时间"
            width="150px">
          </el-table-column>
          <el-table-column
            label="成本金额"
            width="120px">
            <template slot-scope="scope">
              {{scope.row.itemCost}}元
            </template>
          </el-table-column>
          <el-table-column
            label="预计利润"
            width="120px">
            <template slot-scope="scope">
              {{scope.row.itemProfit}}元
            </template>
          </el-table-column>
          <el-table-column label="操作" prop="projectId" width="150px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                @click="pass(scope.row.projectId)">通过</el-button>
              <el-button
                size="mini"
                type="danger"
              @click="refusal(scope.row.projectId)">驳回</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          @current-change="handleCurrentChange"
          :page-size="this.pageSize"
          layout="total, prev, pager, next"
          :total="this.total"
          style="text-align: center;margin-top: 20px;">
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="待审阅" name="1">
        <el-table
          :data="project"
          border
          style="width: 100%;margin: 0px auto"
          :header-cell-style="{textAlign: 'center'}"
          :cell-style="{textAlign: 'center'}">
          <el-table-column
            prop="projectId"
            label="编号"
            width="50px">
          </el-table-column>
          <el-table-column
            prop="title"
            label="项目标题"
            width="180px">
          </el-table-column>
          <el-table-column
            prop="type"
            label="项目类型"
            width="120px">
          </el-table-column>
          <el-table-column
            prop="desc"
            label="项目详情" show-overflow-tooltip>
          </el-table-column>
          <el-table-column
            prop="groupName"
            label="小组名称"
            width="150px">
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="项目时间"
            width="150px">
          </el-table-column>
          <el-table-column
            label="成本金额"
            width="120px">
            <template slot-scope="scope">
              {{scope.row.itemCost}}元
            </template>
          </el-table-column>
          <el-table-column
            label="预计利润"
            width="120px">
            <template slot-scope="scope">
              {{scope.row.itemProfit}}元
            </template>
          </el-table-column>
          <el-table-column label="操作" width="250px">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
              @click="pass(scope.row.projectId)">通过</el-button>
              <el-button
                size="mini"
                type="primary" plain
                @click="dialogShow(scope.row.projectId)">查看录屏</el-button>
              <el-button
                size="mini"
                type="danger"
              @click="refusal(scope.row.projectId)">驳回</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          @current-change="handleCurrentChange"
          :page-size="this.pageSize"
          layout="total, prev, pager, next"
          :total="this.total"
          style="text-align: center;margin-top:20px;">
        </el-pagination>
      </el-tab-pane>
    </el-tabs>
    <el-dialog
      title="录屏文件"
      :visible.sync="centerDialogVisible"
      width="60%"
      @closed="closeVideo()"
      center>
      <video ref="myvideo" style="width: 100%" :src="videoPath">

      </video>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
  import { selectUntreated, refusalUntreated, passUntreated, selectVideo } from '@/api/untreated-project'
export default {
  name: 'UntreatedProject',
  data() {
    return {
      project: [],
      myvideo:{},
      activeName: '0',
      roleId: 0,
      nowTab:{
        name:"0"
      },
      pageNo:1,
      pageSize:5,
      total:0,
      centerDialogVisible:false,
      videoPath:''

    }
  },
  methods: {
    audit(tab) {
      this.activeName = tab.name
      this.nowTab = tab;
      selectUntreated({ key: this.activeName,
        pageNo:this.pageNo,
        pageSize:this.pageSize }).then(response => {
          this.project = response.data.list.list
          this.total = response.data.list.total
      }).catch(e => {
        console.log(e)
      })
    },
    pass(pid) {
      passUntreated({ key: this.activeName, pid }).then(response => {
        this.audit(this.nowTab)
      }).catch(e => {
        console.log(e)
      })
    },
    refusal(pid) {
      refusalUntreated({ key: this.activeName, pid }).then(response => {
        this.audit(this.nowTab)
      }).catch(e => {
        console.log(e)
      })
    },
    handleCurrentChange(val) {
      this.pageNo=val
      this.audit({name:this.activeName})
    },
    dialogShow(pid){
      selectVideo({ pid:pid }).then(response=>{
        this.videoPath="/download/video/"+response.data.video;
        this.centerDialogVisible=true
        this.$nextTick(()=>{
          this.myvideo = this.$refs.myvideo;
          this.myvideo.play();
        })
      }).catch(e=>{
        console.log(e)
      })
    },
    closeVideo(){
      this.myvideo.pause();
      this.myvideo = {};
    }
  },
  created() {
    selectUntreated({ key: this.activeName,
      pageNo:this.pageNo,
    pageSize:this.pageSize}).then(response => {
      console.log(response)
      this.project = response.data.list.list
      this.roleId = response.data.roleId
      this.total = response.data.list.total
    }).catch(e => {
      console.log(e)
    })
  }
}
</script>

<style lang="scss">
  .el-tooltip__popper {
    max-width:30%;
    max-height: 50%;
  }
</style>

<style scoped>
  .untreated{
    margin-top:20px;
  }
</style>
