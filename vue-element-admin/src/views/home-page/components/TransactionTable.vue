<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="项目名称" width="200">
      <template slot-scope="scope">
        {{ scope.row.title }}
      </template>
    </el-table-column>
    <el-table-column label="项目类型" width="180" align="center">
      <template slot-scope="scope">
        <el-tag>
          {{ scope.row.type }}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column label="成本" width="180" align="center">
        <template slot-scope="scope">
          ¥{{ scope.row.itemCost }}
        </template>
    </el-table-column>
    <el-table-column label="收益" width="180" align="center">
      <template slot-scope="scope">
          ¥{{ scope.row.itemProfit }}
      </template>
    </el-table-column>
    <el-table-column label="创建时间" width="300" align="center">
      <template slot-scope="scope">
          {{ scope.row.createTime }}
      </template>
    </el-table-column>
    <el-table-column label="状态" min-width="100" align="center">
      <template slot-scope="{row}">
        <el-tag type="success">
          已完成
        </el-tag>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import {getOkProject} from "@/api/homePageInfo";

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        pending: 'danger'
      }
      return statusMap[status]
    },
    orderNoFilter(str) {
      return str.substring(0, 30)
    }
  },
  data() {
    return {
      list: null
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getOkProject().then(response => {
        this.list = response.data;
      })
    }
  }
}
</script>
