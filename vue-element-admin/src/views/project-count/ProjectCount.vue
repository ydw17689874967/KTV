<template>
  <div class="chart-container">
    <div :id="id" :class="className" style="width: 100%;height: 100%"/>
  </div>

</template>

<script>
import echarts from 'echarts'
import resize from '@/components/Charts/mixins/resize'
import { getCountData } from '@/api/projectCount'

export default {
  name: 'projectCount',
  mixins: [resize],
  userDataTimes: [],
  userDataCounts: [],
  projectDataTimes: [],
  projectDataCounts: [],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    id: {
      type: String,
      default: 'chart'
    }
  },
  data() {
    return {
      chart: null
    }
  },
  created() {
  },
  mounted() {
    getCountData().then(response => {
      this.userDataCounts = response.data.userDataCounts
      this.userDataTimes = response.data.userDataTimes
      this.projectDataCounts = response.data.projectsDataCounts
      this.projectDataTimes = response.data.projectsDataTimes
      this.initChart()
    })
  },
  beforeDestroy() {
    if (!this.myChart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      var _this=this;
      this.chart = echarts.init(document.getElementById(this.id))

      this.chart.setOption({
        title: {
          text: '数据统计图'
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['项目申请量', '人员注册量'],
          selectedMode: 'single', // 单选
          selected: {
            项目申请量: true,
            人员注册量: false
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.projectDataTimes
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '项目申请量',
            type: 'line',
            stack: '总量',
            data: this.projectDataCounts
          },
          {
            name: '人员注册量',
            type: 'line',
            stack: '总量',
            data: this.userDataCounts
          }
        ]
      })
      console.log(this.chart.getOption())
      this.chart.on("legendselectchanged", obj => {
        var options = this.chart.getOption();
        options.xAxis[0].data = obj.name == "项目申请量" ? this.projectDataTimes : this.userDataTimes;
        this.chart.setOption(options, true);
      });
    }
  }
}
</script>

<style scoped>
.chart-container {
  position: relative;
  width: 100%;
  height: calc(100vh - 84px);
}
</style>
