import request from '@/utils/request'

export function getBossHomePageInfo(data) {
  return request({
    url: '/home/info',
    method: 'post',
    data
  })
}


export function getRoleId(data) {
  return request({
    url: '/home/role',
    method: 'post',
    data
  })
}

export function getOkProject() {
  return request({
    url: '/home/ok',
    method: 'post'
  })
}
