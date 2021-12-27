<template>
  <div class="div1">
    <el-card style="border: 1px white solid" id="elcar">
      <div class="div2">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="头像">
            <el-image
              v-if="form.avatar !== '' && form.avatar != null"
              style="width: 90px;height: 90px"
              :src="'http://localhost:8080/download/image/'+form.avatar"
              fit="fill"></el-image>
            <el-image
              v-else
              style="width: 90px;height: 90px"
              src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
              fit="fill"></el-image>
          </el-form-item>
          <el-form-item label="用户名">
            <span>{{form.username}}</span>
          </el-form-item>
          <el-form-item label="密码">
            <span>{{form.password}}</span>
          </el-form-item>
          <el-form-item label="创建时间">
            <span>{{form.createTime}}</span>
          </el-form-item>
          <el-form-item label="更新时间">
            <span>{{form.updateTime}}</span>
          </el-form-item>
          <el-form-item label="年龄">
            <span>{{form.age}}</span>
          </el-form-item>
          <el-form-item label="性别">
            <span v-if="form.sex == 0">女</span>
            <span v-if="form.sex == 1">男</span>
          </el-form-item>
          <el-form-item label="邮箱">
            <span>{{form.email}}</span>
          </el-form-item>
          <el-form-item label="真实姓名">
            <span>{{form.realName}}</span>
          </el-form-item>
          <el-form-item label="电话号码">
            <span>{{form.phoneNumber}}</span>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>
<script>
import { fetchDetail } from '@/api/article'

export default {
  name: 'MyselfInfo',
  data() {
    return {
      form: {
        avatar: '',
        username: '',
        password: '',
        createTime: '',
        updateTime: '',
        age: '',
        email: '',
        realName: '',
        phoneNumber: ''
      }
    }
  },
  created() {
    this.getDetail()
  },
  methods: {
    getDetail() {
      fetchDetail().then(response => {
        this.form = response.data
      })
    }
  }
}
</script>

<style>
  #elcar span {
    font-size: large;
    font-weight: bold;
  }
  .div1 {
    margin: 0px 30px;
  }
  .div2 {

  }
</style>
