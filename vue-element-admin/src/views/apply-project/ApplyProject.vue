<template>
  <div class="content">
    <el-form style="width: 700px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
             class="demo-ruleForm"
    >
      <el-form-item label="审核标题" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
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
      <el-form-item label="项目类型" prop="options">
        <el-select v-model="ruleForm.type" placeholder="请选择">
          <el-option
            v-for="item in ruleForm.options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="预计花费" prop="itemCost">
        <el-input v-model.number="ruleForm.itemCost"></el-input>
      </el-form-item>
      <el-form-item label="预计盈利" prop="itemProfit">
        <el-input v-model.number="ruleForm.itemProfit"></el-input>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { apply } from '@/api/project'

export default {
  data() {
    return {
      ruleForm: {
        title: '',
        desc: '',
        type: '',
        itemCost: '',
        itemProfit: '',
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
          apply({
            title: this.ruleForm.title,
            desc: this.ruleForm.desc,
            type: this.ruleForm.type,
            itemCost: this.ruleForm.itemCost,
            itemProfit: this.ruleForm.itemProfit
          }).then(date => {
            if (date.state == 0) {
              this.resetForm('ruleForm')
              this.success()
            } else {
              this.error()
            }
          }).catch(e => {
            console.log(e)
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.setDefaultType()
    },
    success() {
      this.$message({
        message: '申请发送成功',
        type: 'success'
      })
    },
    error() {
      this.$message({
        message: '申请发送失败',
        type: 'error'
      })
    },
    setDefaultType() {
      this.ruleForm.type = this.ruleForm.options[0].value
    }

  },
  created() {
    this.setDefaultType()
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
