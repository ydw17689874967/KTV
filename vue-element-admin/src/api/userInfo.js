import request from '@/utils/request'

export function getRoleUserInfo(data) {
  return request({
    url: 'view/roleUserInfo',
    method: 'post',
    data
  })
}

export function getUserInfoById(data) {
  return request({
    url: 'view/userInfo',
    method: 'post',
    data
  })
}


export function updateUserInfoBy(data) {
  return request({
    url: 'view/updateUserInfo',
    method: 'post',
    data
  })
}

export function addUserInfoBy(data) {
  return request({
    url: 'view/addUserInfo',
    method: 'post',
    data
  })
}

export function deleteStaff(data) {
  return request({
    url: 'view/updateUserState',
    method: 'post',
    data
  })
}
