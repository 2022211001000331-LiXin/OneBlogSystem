import request from '@/utils/request'

// 获取情感分析看板数据
export function getSentimentDashboard() {
  return request({
    url: '/cms/sentiment/dashboard',
    method: 'get'
  })
}