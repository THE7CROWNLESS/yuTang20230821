import request from '@/utils/request'

export function getAuthorityList() {
  return request({
    url: '/admin/user/getTableData',
    method: 'get',
  })
}

export function addAuthority(data) {
    return request({
      url: '/admin/user/addRule',
      method: 'post',
      data
    })
  }
  
  
  export function editAuthority(data) {
    return request({
      url: '/admin/user/editRule',
      method: 'post',
      data
    })
  }
  
  export function deleteAuthority(params) {
    return request({
      url: '/admin/user/delRule',
      method: 'get',
      params
    })
  }
  