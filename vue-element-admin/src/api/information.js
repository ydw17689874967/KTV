import request from '@/utils/request'

export function list() {
  return request({
    url: '/personal/detail',
    method: 'post'
  })
}

export function detail(data) {
  console.log('====' + data)
  return request({
    url: '/personal/detail',
    method: 'post',
    data: data
  })
}

export function updatePassword(data) {
  return request({
    url: '/personal/modify',
    method: 'post',
    data
  })
}

export function updateStatus(id) {
  return request({
    url: '/approval/modifyStatus',
    method: 'get',
    params: {id}
  })
}
