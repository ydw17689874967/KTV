<template xmlns:el-table="http://www.w3.org/1999/html">
  <div class="app-container">
  <el-table
    :data="list"
    style="width: 100%"
    >
    <el-table-column  label="小组序号" width="200" align="center">
      <template slot-scope="{row}">
        <span>{{ row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column
      v-loading="loading"
      align="center"
      label="头像"
      width="100"
      height="100"
      element-loading-text="请给我点时间！"
    >
      <template slot-scope="scope">
        <img v-if="scope.row.avatar != null && scope.row.avatar != ''" class="avatar_img" v-model="scope.row.avatar" :src="'http://localhost:8080/download/video/' + scope.row.avatar">
        <div v-else class="block"><el-avatar :size="60" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"></el-avatar></div>
      </template>
    </el-table-column>
    <el-table-column  label="小组名称" width="250"  align="center">
      <template slot-scope="{row}">
        <span>{{ row.name }}</span>
      </template>
    </el-table-column>
    <el-table-column  label="小组组长" width="300"  align="center">
      <template slot-scope="{row}">
        <span>{{ row.realName }}</span>
      </template>
    </el-table-column>
    <el-table-column  label="组长电话" width="250"  align="center">
      <template slot-scope="{row}">
        <span>{{ row.phoneNumber }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作" align="center" width="276" class-name="filter-item" type="primary" >
      <template slot-scope="{row}">
        <el-button type="primary" size="mini" @click="getMemberInfo(row.id);dialogVisible = true" >
          查看小组详情
        </el-button>
      </template>
    </el-table-column>
  </el-table>
    <el-dialog
      title="组员信息"
      :visible.sync="dialogVisible"
      width="50%">
      <el-table
      :data="list1">
        <el-table-column property="realName" label="姓名" width="150"></el-table-column>
        <el-table-column property="age" label="年龄" width="200"></el-table-column>
        <el-table-column property="phoneNumber" label="手机号"></el-table-column>
        <el-table-column property="email" label="邮箱"></el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>
import { allGroup, getMemberInfo } from '@/api/group'

export default {
  name: 'LookManageGroup',
  created() {
    this.allGroup()
  },
  data() {
    return {
      list: '',
      list1:'',
      listLoading: true,
      dialogVisible:false
    }
  },
  methods: {
    allGroup() {
      allGroup().then(response => {
        console.log(response.data)
        this.list = response.data
      })
    },
    getMemberInfo(id){
      console.log("groupId:"+id)
      getMemberInfo(id).then(response =>{
        console.log(response.data)
        this.list1=response.data
      })
    }
  }
}
</script>

<style>
.text {
  font-size: 16px;
  font-family: 微软雅黑;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 600px;
  height: 300px;
}

.avatar_img{
  width: 60px;
  height: 60px;
  border-radius: 50px;
}
</style>
