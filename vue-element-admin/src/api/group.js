import request from '@/utils/request'

export function getList() {
  return request({
    url: '/lookGroup/groupMember',
    method: 'post'
  })
}


export function myTask() {
  return request({
    url: '/my/task',
    method: 'post'
  })
}

export function allGroup() {
  return request({
    url: '/lookManage/manageGroup',
    method: 'post'
  })
}

export function getState(id) {
  return request({
    url: '/lookGroup/task',
    method: 'post',
    params: { id }
  })
}

export function updateState(id) {
  return request({
    url: '/my/complete',
    method: 'post',
    params: { id }
  })
}

export function getMemberInfo(id) {
  return request({
    url: '/lookManage/memberInfo',
    method: 'post',
    params : { id }
  })
}
