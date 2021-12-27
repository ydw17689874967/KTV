import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/vue-element-admin/article/list',
    method: 'get',
    params: query
  })
}

export function fetchArticle(id) {
  return request({
    url: '/vue-element-admin/article/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {
  return request({
    url: '/vue-element-admin/article/pv',
    method: 'get',
    params: { pv }
  })
}

export function createArticle(data) {
  return request({
    url: '/vue-element-admin/article/create',
    method: 'post',
    data
  })
}

export function updateArticle(data) {
  return request({
    url: '/vue-element-admin/article/update',
    method: 'post',
    data
  })
}

export function fetchDetail(data) {
  return request({
    url: '/personal/detail',
    method: 'post',
    data
  })
}


export function fetchApproved(data) {
  return request({
    url: '/approval/findapprovalp',
    method: 'post',
    data
  })
}

export function chaXun(data) {
  return request({
    url: '/approval/cha',
    method: 'post',
    // params: {search}
    data
  })

}

