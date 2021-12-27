<template>
  <div class="home_page">
    <el-row>
      <el-col :xs="24" :sm="24" :lg="24">
        <div v-if="roleId === 1">
          <boss-header-data></boss-header-data>
        </div>
        <div v-else-if="roleId === 2">
          <xmjl-header-data></xmjl-header-data>
        </div>
        <div v-else-if="roleId === 3">
          <kj-header-data></kj-header-data>
        </div>
        <div v-else-if="roleId === 4">
          <xzzz-header-data></xzzz-header-data>
        </div>
        <div v-else-if="roleId === 5">
          <zy-header-data></zy-header-data>
        </div>
      </el-col>
      <el-col :offset="1" :xs="20" :sm="20" :lg="22" >
        <el-divider/>
        <div style="text-align: center;
    font-size: 26px;
    margin-bottom: 15px;
    font-family: fangsong;">
          公司成绩榜
        </div>
        <el-card class="box-card">
          <TransactionTable></TransactionTable>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import BossHeaderData from "@/views/home-page/components/BossHeaderData"
import XmjlHeaderData from "@/views/home-page/components/XmjlHeaderData"
import KjHeaderData from "@/views/home-page/components/KjHeaderData"
import XzzzHeaderData from "@/views/home-page/components/XzzzHeaderData";
import ZyHeaderData from "@/views/home-page/components/ZyHeaderData";
import {getRoleId} from "@/api/homePageInfo";
import state from "@/store/modules/user";
import TransactionTable from "@/views/home-page/components/TransactionTable";


export default {
  name: "HomePage",
  components: {
    TransactionTable,
    ZyHeaderData,
    XzzzHeaderData,
    BossHeaderData,
    XmjlHeaderData,
    KjHeaderData
  },
  data(){
    return {
      roleId:0,
      list:{}
    }
  },
  created() {
    getRoleId({username:state.state.name}).then(response => {
        this.roleId = response.data;
    })
  }
}
</script>

<style scoped>
  .home_page{
    padding: 0px 10px;
  }

  .box-card {
    width: 100%;
  }
</style>
