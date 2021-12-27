import request from '@/utils/request'

export function selectUntreated(data) {
  console.log('data=' + data)
  return request({
    url: '/untreated/project',
    method: 'post',
    params: data
  })
}

export function passUntreated(data) {
  console.log('data=' + data)
  return request({
    url: '/untreated/pass/state',
    method: 'post',
    params: data
  })
}

export function refusalUntreated(data) {
  console.log('data=' + data)
  return request({
    url: '/untreated/refusal/state',
    method: 'post',
    params: data
  })
}

export function selectVideo(data) {
  console.log('data=' + data)
  return request({
    url: '/untreated/video',
    method: 'post',
    params: data
  })
}

