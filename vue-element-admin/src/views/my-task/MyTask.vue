<template>
  <el-table
    :data="list"
    style="width: 100%">
    <el-table-column prop="rid" label="任务id" width="200" align="center">
      <template slot-scope="{row}">
        <span>{{ row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="desc" label="任务描述" min-width="300" align="center">
      <template slot-scope="{row}">
        <span>{{ row.desc }}</span>
      </template>
    </el-table-column>
    <el-table-column prop="status" label="任务状态" width="200" align="center">
      <template slot-scope="{row}">
        <span v-if="row.state < 1">未完成</span>
        <span v-else>已完成</span>
      </template>
    </el-table-column>
    <el-table-column prop="name" label="项目名称" width="200" align="center">
      <template slot-scope="{row}">
        <span>{{ row.title }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作" align="center" width="200" class-name="filter-item" type="primary" >
      <template slot-scope="{row}" >
        <el-button type="primary" :disabled="row.state==1"  size="mini" @click="changeState(row.id)">
          提交任务
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { myTask } from '@/api/group'
import { updateState } from '@/api/group'

export default {
  name: 'MyTask',
  data() {
    return {
      list: []
    }
  },
  created() {
    this.getMyTask()
  },
  methods: {
    getMyTask() {
      this.listLoading = true
      // eslint-disable-next-line no-undef
      myTask().then(response => {
        console.log(response.data)
        this.list = response.data
        this.listLoading = false
        console.log(this.list)
      })
    },
    changeState(id) {
      console.log(id)
      const myDate = this.list.find(item => item.id == id);
      myDate.state = 1;

      updateState(id).then(response => {
        console.log(response.data)
        if (response.data) {
          alert('修改成功')
        } else {
          alert('修改失败')
        }
      }).catch()
    }
  }
}
</script>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>
