import request from '@/utils/request'

export function getFrontUsers(params) {
  return request({
    url: '/admin/member/getMemberList',
    method: 'get',
    params
  })
}
