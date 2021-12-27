import request from '@/utils/request'

export function apply(data) {
  //console.log('===========' + data)
  return request({
    url: '/project/apply',
    method: 'post',
    data
  })
}

export function updateVideo(data) {
  console.log('===========' + data)
  return request({
    url: '/project-video/updateSome',
    method: 'post',
    data
  })
}

export function getList(data) {
  //console.log('===========' + data)
  return request({
    url: '/project/getList',
    method: 'post',
    data
  })
}



export function detailProject(data) {
  console.log('===========' + data)
  return request({
    url: '/project/getDetail',
    method: 'post',
    data
  })
}

export function submitProject(data) {
  console.log('===========' + data)
  return request({
    url: '/project/updateProject',
    method: 'post',
    data
  })
}

export function getProDetail(id) {
  console.log('===========' + id)
  return request({
    url: '/project-video/getProDetail',
    method: 'post',
    params: { id }
  })
}

export function submitProjectVideo(data) {
  console.log('===========' + data)
  return request({
    url: '/project/video',
    method: 'post',
    data
  })
}


