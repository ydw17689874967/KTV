<template>
  <div class="app-container">

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%; margin-top: 20px"
      @sort-change="sortChange"
    >
      <el-table-column
        align="center"
        label="序号"
        type="index"
      >
        <template slot-scope="scope">
          <span>{{ (listQuery.pageNum - 1) * listQuery.pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目标题" prop="title" :formatter="stateFormatTitle"  align="center">
      </el-table-column>

      <el-table-column prop="desc" width="" align="center" label="项目描述" show-overflow-tooltip>
        <template slot-scope="scope">
          <span v-if="scope.row.desc === ''">-</span>
          <span v-else>{{ scope.row.desc }}</span>
        </template>
      </el-table-column>

     <!-- <el-table-column label="项目描述" :formatter="stateFormatDesc" prop="desc" min-width="150px">
      </el-table-column>-->
      <el-table-column label="项目类型" align="center">
        <template slot-scope="{row}">
          <span>{{ row.type }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目小组" align="center">
        <template slot-scope="{row}">
          <span>{{ row.groupName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预计花费">
        <template slot-scope="{row}">
          <span>{{ row.itemCost }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请开始时间" class-name="status-col">
        <template slot-scope="{row}">
          <span>{{ row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请更新时间" class-name="status-col">
        <template slot-scope="{row}">
          <span>{{ row.lastUpdateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请状态" class-name="status-col">
        <template slot-scope="{row}">
          <span>{{ row.status }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button size="mini" type="success"
                     @click="viewDetails(row.id)"
          >
            查看
          </el-button>
          <el-button size="mini" type="success"
                     @click="changeProject(row.id)" v-if=" (userPermissionNames.indexOf('op:submit')!=-1 && row.status==='项目经理审阅未通过') || (userPermissionNames.indexOf('op:submit')!=-1 && row.status==='老板审阅未通过')">
            修改
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="listQuery.pageSize"
      @current-change="changePageNum"
      style="text-align: center;margin-top: 30px"
    >
    </el-pagination>

  </div>
</template>

<script>
import { fetchList, fetchPv, createArticle, updateArticle } from '@/api/article'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import { getList } from '@/api/project'
import state from '@/store/modules/user'

export default {
  name: 'ViewProject',
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return calendarTypeKeyValue[type]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 100,
      listLoading: true,
      userPermissionNames: '',
      listQuery: {
        pageSize: 7,
        pageNum: 1
      },
      importanceOptions: [1, 2, 3],
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showReviewer: false,
      temp: {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        type: '',
        status: 'published'
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      },
      downloadLoading: false
    }
  },
  created() {
    this.userPermissionNames = state.state.roles
    this.getList()
  },
  methods: {
    stateFormatDesc(row, column, cellValue) {
      if (!cellValue) return ''
      if (cellValue.length > 10) {       //最长固定显示10个字符
        return cellValue.slice(0, 10) + '...'
      }
      return cellValue
    },
    stateFormatTitle(row, column, cellValue) {
      if (!cellValue) return ''
      if (cellValue.length > 5) {       //最长固定显示10个字符
        return cellValue.slice(0, 5) + '...'
      }
      return cellValue
    },
    changePageNum(val) {
      console.log(val)
      this.listQuery.pageNum = val
      this.getList()
    },
    getList() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
        console.log(response)
        this.list = response.data.list
        this.total = response.data.total
        console.log(this.list)
        setTimeout(() => {
          this.listLoading = false
        }, 0.5 * 1000)
      }).catch(e => {
        console.log(e)
      })
    },
    viewDetails(id) {
      console.log(id)
      this.$router.push({ path: '/view/project/DeatilProject', query: { id: id } })
    },
    changeProject(id){
      console.log(id)
      this.$router.push({path: '/view/project/UpdateProject', query: { id: id } } )
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作Success',
        type: 'success'
      })
      row.status = status
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        status: 'published',
        type: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
          this.temp.author = 'vue-element-admin'
          createArticle(this.temp).then(() => {
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          updateArticle(tempData).then(() => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row, index) {
      this.$notify({
        title: 'Success',
        message: 'Delete Successfully',
        type: 'success',
        duration: 2000
      })
      this.list.splice(index, 1)
    },
    handleFetchPv(pv) {
      fetchPv(pv).then(response => {
        this.pvData = response.data.pvData
        this.dialogPvVisible = true
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
        const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
        const data = this.formatJson(filterVal)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}` ? 'ascending' : 'descending'
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
