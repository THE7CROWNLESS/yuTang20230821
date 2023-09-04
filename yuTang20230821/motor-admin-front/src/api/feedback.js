import request from '@/utils/request'

export function getFeedback(params) {
  return request({
    url: '/admin/feeback/getFeebackList',
    method: 'get',
    params
  })
}
