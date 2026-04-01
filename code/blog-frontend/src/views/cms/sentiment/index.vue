<template>
    <div class="app-container" style="background-color: #f0f2f5; padding: 20px; min-height: calc(100vh - 84px);">
      <!-- 顶部数据概览 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card shadow="always" :body-style="{ padding: '20px' }">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold"><i class="el-icon-pie-chart"></i> 评论情感分布占比</span>
            </div>
            <div ref="pieChart" style="height: 350px;"></div>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card shadow="always" :body-style="{ padding: '20px' }">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold"><i class="el-icon-trend-charts"></i> 近一周情感变化趋势</span>
            </div>
            <div ref="lineChart" style="height: 350px;"></div>
          </el-card>
        </el-col>
      </el-row>
  
      <!-- 底部关键词分析 -->
      <el-row :gutter="20" style="margin-top: 20px;">
        <el-col :span="24">
          <el-card shadow="always">
            <div slot="header" class="clearfix">
              <span style="font-weight: bold"><i class="el-icon-warning-outline"></i> 负面评论关键词 TOP10 (AI 实时提取)</span>
            </div>
            <div v-show="!noData" ref="barChart" style="height: 400px;"></div>
            <div v-show="noData" style="height: 400px; display: flex; justify-content: center; align-items: center; color: #909399;">
              <el-empty description="暂无负面评论数据，继续保持哦！"></el-empty>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </template>
  
  <script>
  import * as echarts from 'echarts';
  import { getSentimentDashboard } from "@/api/cms/sentiment";
  
  export default {
    name: "Sentiment",
    data() {
      return {
        noData: false,
        myCharts: {
          pie: null,
          line: null,
          bar: null
        }
      };
    },
    mounted() {
      this.initCharts();
      this.handleData();
      // 响应式处理
      window.addEventListener("resize", this.resizeCharts);
    },
    beforeDestroy() {
      window.removeEventListener("resize", this.resizeCharts);
    },
    methods: {
      initCharts() {
        this.myCharts.pie = echarts.init(this.$refs.pieChart);
        this.myCharts.line = echarts.init(this.$refs.lineChart);
        this.myCharts.bar = echarts.init(this.$refs.barChart);
      },
      resizeCharts() {
        this.myCharts.pie && this.myCharts.pie.resize();
        this.myCharts.line && this.myCharts.line.resize();
        this.myCharts.bar && this.myCharts.bar.resize();
      },
      handleData() {
        getSentimentDashboard().then(res => {
          const { pieData, trendData, cloudData } = res.data;
          
          this.renderPie(pieData);
          this.renderLine(trendData);
          
          if (cloudData && cloudData.length > 0) {
            this.noData = false;
            this.renderBar(cloudData);
          } else {
            this.noData = true;
          }
        });
      },
      renderPie(data) {
        this.myCharts.pie.setOption({
          tooltip: { trigger: 'item', formatter: '{a} <br/>{b} : {c} ({d}%)' },
          legend: { orient: 'vertical', left: 'left', data: data.map(i => i.name) },
          color: ['#91cc75', '#fac858', '#ee6666', '#73c0de'], // 绿、黄、红
          series: [{
            name: '情感倾向',
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            data: data,
            emphasis: {
              itemStyle: { shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0, 0, 0, 0.5)' }
            }
          }]
        });
      },
      renderLine(data) {
        this.myCharts.line.setOption({
          tooltip: { trigger: 'axis' },
          legend: { data: ['正面', '负面'] },
          grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
          xAxis: { type: 'category', boundaryGap: false, data: data.map(i => i.day) },
          yAxis: { type: 'value' },
          series: [
            { name: '正面', type: 'line', smooth: true, color: '#91cc75', data: data.map(i => i.positive) },
            { name: '负面', type: 'line', smooth: true, color: '#ee6666', data: data.map(i => i.negative) }
          ]
        });
      },
      renderBar(data) {
        // 这里的词云用横向柱状图展示，更符合后台管理系统的严肃风格
        const sortedData = data.sort((a, b) => a.value - b.value);
        this.myCharts.bar.setOption({
          tooltip: { trigger: 'axis', axisPointer: { type: 'shadow' } },
          grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
          xAxis: { type: 'value' },
          yAxis: { type: 'category', data: sortedData.map(i => i.name) },
          series: [{
            name: '出现频次',
            type: 'bar',
            data: sortedData.map(i => i.value),
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                { offset: 0, color: '#ff9a9e' },
                { offset: 1, color: '#fecfef' }
              ])
            },
            label: { show: true, position: 'right' }
          }]
        });
      }
    }
  };
  </script>
  
  <style scoped>
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
  </style>