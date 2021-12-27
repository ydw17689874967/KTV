import request from '@/utils/request'

export function selectTreated(data) {
  console.log('data=' + data)
  return request({
    url: '/treated/project',
    method: 'post',
    params: data
  })
}
