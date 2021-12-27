<template>
  <div>
  <el-card class="app-container">
    <el-table v-loading="listLoading" :data="shuju" border>

      <el-table-column align="center" label="序号" width="50px">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column width="" align="center" label="标题">
        <template slot-scope="scope">
          <span>{{ scope.row.title }}</span>
        </template>
      </el-table-column>

<!--      <el-table-column :formatter="stateFormatDesc" prop="desc" width="160px" align="center" label="描述">-->
<!--      </el-table-column>-->

      <el-table-column prop="desc" width="" align="center" label="描述" show-overflow-tooltip>
        <template slot-scope="scope">
          <span v-if="scope.row.desc === ''">-</span>
          <span v-else>{{ scope.row.desc }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" align="center" label="类型">
        <template slot-scope="scope">
          <span>{{ scope.row.type }}</span>
        </template>
      </el-table-column>

      <el-table-column width="110px" align="center" label="项目花费">
        <template slot-scope="scope">
          <span>{{ scope.row.itemCost }}元</span>
        </template>
      </el-table-column>

      <el-table-column width="110px" align="center" label="项目利润">
        <template slot-scope="scope">
          <span>{{ scope.row.itemProfit }}元</span>
        </template>
      </el-table-column>

      <el-table-column width="" align="center" label="创建时间">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>

      <el-table-column width="" align="center" label="最后更新时间">
        <template slot-scope="scope">
          <span>{{ scope.row.lastUpdateTime }}</span>
        </template>
      </el-table-column>

      <el-table-column width="75px" align="center" label="审核状态">
        <template slot-scope="scope">
          <span>{{ scope.row.auditStatus }}</span>
        </template>
      </el-table-column>

      <el-table-column  width="" align="center" label="操作" v-if="role === 4">
        <template slot-scope="scope">
          <span v-if="scope.row.auditStatus === '项目已拨款'">
            <el-button  type="primary" plain @click="changeStatus(scope.row.id)">开始项目</el-button>
          </span>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="total,prev, pager, next"
      :total="total"
      :page-size="listQuery.pageSize"
      @current-change="changePageNum"
      style="text-align: center;margin-top: 30px"
    >
    </el-pagination>

  </el-card>
  </div>
</template>

<script>
  import { fetchApproved } from '@/api/article'
  import { updateStatus } from '@/api/information'
  import {getRoleId} from '@/api/homePageInfo'
  import state from '@/store/modules/user'

  export default {
    name: 'ViewApprovedItems',
    data() {
      return {
        shuju: [],
        total: 0,
        role:0,
        listLoading: true,
        listQuery: {
          pageSize: 2,
          pageNum: 1
        }
        // search: '',
        // searchData:''
      }
    },
    created() {
      this.getDetail()
    },
    methods: {
      changePageNum(val) {
        // console.log(val+'val')
        // val是当前页码
        this.listQuery.pageNum = val
        this.getDetail()
      },
      getDetail() {
        this.listLoading = true
        fetchApproved(this.listQuery).then(response => {
          this.shuju = response.data.list
          this.total = response.data.total
          console.log(this.shuju)
          console.log(this.total)
          setTimeout(() =>{
            this.listLoading=false
          },0.5*1000)
        }).catch(e =>{
          console.log(e)
        })
        getRoleId({username:state.state.name}).then(response => {
          this.role = response.data;
        })
      },
      /*getDetail() {
        this.listLoading = true
        fetchApproved().then(response => {
          this.shuju = response.data
          this.total = response.data.length
          this.listLoading = false
        })
      },*/
      changeStatus(id) {
        console.log(id)
        updateStatus(id).then(response => {
          console.log(response.data)
          if (response.data) {
            alert('修改成功')
            this.getDetail()
          }else {
            alert('修改失败')
          }
        }).catch()
      }
    }
  }
</script>

<style lang="scss">
  .el-tooltip__popper {
    max-width:30%;
    max-height: 50%;
  }
</style>
