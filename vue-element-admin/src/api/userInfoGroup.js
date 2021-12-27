import request from '@/utils/request'

export function getUserInfoOfGroup(data) {
  console.log('===========' + data)
  return request({
    url: '/userGroup/userInfo',
    method: 'post',
    data
  })
}

export function addTask(data) {
  console.log('===========' + data)
  return request({
    url: '/userGroup/addTask',
    method: 'post',
    data
  })
}

