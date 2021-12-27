<template>
  <div class="treated">
    <template v-if="roleId==3">
      <el-table
        :data="project"
        border
        style="width: 95%;margin: auto"
        :header-cell-style="{textAlign: 'center'}"
        :cell-style="{textAlign: 'center'}"
      >
        <el-table-column
          prop="title"
          label="项目标题"
          width="180px"
        />
        <el-table-column
          prop="type"
          label="项目类型"
          width="180px"
        />
        <el-table-column
          prop="desc"
          label="项目详情"
          show-overflow-tooltip
        />

        <el-table-column
          prop="groupName"
          label="小组名称"
          width="150px"
        />
        <el-table-column
          prop="createTime"
          label="项目创建时间"
          width="150px"
        />
        <el-table-column
          label="成本金额"
          width="120px"
        >
          <template slot-scope="scope">
            {{ scope.row.itemCost }}元
          </template>
        </el-table-column>
        <el-table-column
          label="预计利润"
          width="120px"
        >
          <template slot-scope="scope">
            {{ scope.row.itemProfit }}元
          </template>
        </el-table-column>
        <el-table-column
          label="状态"
          width="150px"
        >
          <template slot-scope="scope">
            <span v-if="scope.row.stateId ==2">未通过项目经理审批</span>
            <span v-else-if="scope.row.stateId==3">老板审核中</span>
            <span v-else-if="scope.row.stateId==4">未通过老板审批</span>
            <span v-else-if="scope.row.stateId==5">项目已拨款</span>
            <span v-else-if="scope.row.stateId==6">项目未拨款</span>
            <span v-else-if="scope.row.stateId==7">项目进行中</span>
            <span v-else-if="scope.row.stateId==9">未通过项目经理审阅</span>
            <span v-else-if="scope.row.stateId==10">老板审阅中</span>
            <span v-else-if="scope.row.stateId==11">未通过老板审阅</span>
            <span v-else>项目完成</span>
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
    </template>
    <el-tabs v-else v-model="activeName" type="border-card" style="width: 95%;margin: auto" @tab-click="audit">
      <el-tab-pane label="已审批" name="0">
        <el-table
          :data="project"
          border
          style="width: 100%;margin: auto"
          :header-cell-style="{textAlign: 'center'}"
          :cell-style="{textAlign: 'center'}"
        >
          <el-table-column
            prop="title"
            label="项目标题"
            width="180px"
          />
          <el-table-column
            prop="type"
            label="项目类型"
            width="180px"
          />
          <!--<el-table-column
            prop="desc"
            label="项目详情"
          />-->
          <el-table-column prop="desc" width="" align="center" label="描述" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.desc === ''">-</span>
              <span v-else>{{ scope.row.desc }}</span>
            </template>
          </el-table-column>

          <el-table-column
            prop="groupName"
            label="小组名称"
            width="150px"
          />
          <el-table-column
            prop="createTime"
            label="项目创建时间"
            width="150px"
          />
          <el-table-column
            label="成本金额"
            width="120px"
          >
            <template slot-scope="scope">
              {{ scope.row.itemCost }}元
            </template>
          </el-table-column>
          <el-table-column
            label="预计利润"
            width="120px"
          >
            <template slot-scope="scope">
              {{ scope.row.itemProfit }}元
            </template>
          </el-table-column>
          <el-table-column
            label="状态"
            width="150px"
          >
            <template slot-scope="scope">
              <span v-if="scope.row.stateId ==2">未通过项目经理审批</span>
              <span v-else-if="scope.row.stateId==3">老板审核中</span>
              <span v-else-if="scope.row.stateId==4">未通过老板审批</span>
              <span v-else-if="scope.row.stateId==5">项目已拨款</span>
              <span v-else-if="scope.row.stateId==6">项目未拨款</span>
              <span v-else-if="scope.row.stateId==7">项目进行中</span>
              <span v-else-if="scope.row.stateId==9">未通过项目经理审阅</span>
              <span v-else-if="scope.row.stateId==10">老板审阅中</span>
              <span v-else-if="scope.row.stateId==11">未通过老板审阅</span>
              <span v-else>项目完成</span>
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
      <el-tab-pane label="已审阅" name="1">
        <el-table
          :data="project"
          border
          style="width: 100%;margin: auto"
          :header-cell-style="{textAlign: 'center'}"
          :cell-style="{textAlign: 'center'}"
        >
          <el-table-column
            prop="title"
            label="项目标题"
            width="180px"
          />
          <el-table-column
            prop="type"
            label="项目类型"
            width="180px"
          />
          <el-table-column
            prop="desc"
            label="项目详情"
            show-overflow-tooltip
          />
          <el-table-column
            prop="groupName"
            label="小组名称"
            width="150px"
          />
          <el-table-column
            prop="createTime"
            label="项目创建时间"
            width="150px"
          />
          <el-table-column
            label="成本金额"
            width="120px"
          >
            <template slot-scope="scope">
              {{ scope.row.itemCost }}元
            </template>
          </el-table-column>
          <el-table-column
            label="预计利润"
            width="120px"
          >
            <template slot-scope="scope">
              {{ scope.row.itemProfit }}元
            </template>
          </el-table-column>
          <el-table-column
            label="状态"
            width="150px">
            <template slot-scope="scope">
              <span v-if="scope.row.stateId ==2">未通过项目经理审批</span>
              <span v-else-if="scope.row.stateId==3">老板审核中</span>
              <span v-else-if="scope.row.stateId==4">未通过老板审批</span>
              <span v-else-if="scope.row.stateId==5">项目已拨款</span>
              <span v-else-if="scope.row.stateId==6">项目未拨款</span>
              <span v-else-if="scope.row.stateId==7">项目进行中</span>
              <span v-else-if="scope.row.stateId==9">未通过项目经理审阅</span>
              <span v-else-if="scope.row.stateId==10">老板审阅中</span>
              <span v-else-if="scope.row.stateId==11">未通过老板审阅</span>
              <span v-else>项目完成</span>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total"
        style="text-align: center;margin-top:20px;">
        </el-pagination>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { selectTreated } from '@/api/treated-project'
export default {
  name: 'TreatedProject',
  data() {
    return {
      project: [],
      activeName: '0',
      roleId: 0,
      pageNo:1,
      pageSize:5,
      total:0
    }
  },
  created() {
    selectTreated(
      { key: this.activeName,
        pageNo:this.pageNo,
        pageSize:this.pageSize }).then(response => {
      console.log(response)
      this.project = response.data.list.list
      this.roleId = response.data.roleId
      this.total = response.data.list.total
    })
  },
  methods: {
    audit(tab) {
      this.activeName = tab.name
      selectTreated({key: this.activeName,
        pageNo:this.pageNo,
        pageSize:this.pageSize}).then(response => {
        console.log(response)
        this.project = response.data.list.list
        this.total=response.data.list.total
      }
      )
    },
      handleCurrentChange(val) {
      this.pageNo=val;
      this.audit({name:this.activeName});
      }
  }
}
</script>

<style lang="scss" scoped>
  .el-tooltip__popper {
    max-width:30%;
    max-height: 50%;
  }
</style>
<style scoped>
  .treated {
    margin-top:20px;
  }
</style>
