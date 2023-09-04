import request from '@/utils/request'

export function getRoleList(params) {
  return request({
    url: '/admin/user/roleListJson',
    method: 'get',
    params
  })
}

export function getAuthorityJson(data) {
  return request({
    url: '/admin/user/getJson',
    method: 'post',
    data
  })
}

export function addRole(data) {
  return request({
    url: '/admin/user/addRole',
    method: 'post',
    data
  })
}


export function editRole(data) {
  return request({
    url: '/admin/user/editRole',
    method: 'post',
    data
  })
}

export function deleteRole(data) {
  return request({
    url: '/admin/user/delRole',
    method: 'post',
    data
  })
}


