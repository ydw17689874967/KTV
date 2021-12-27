import request from '@/utils/request'

export function getCountData(query) {
  return request({
    url: '/echarts/userAndProject',
    method: 'get',
    params: query,
  })
}


