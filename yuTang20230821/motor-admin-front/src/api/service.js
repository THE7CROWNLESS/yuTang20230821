import request from '@/utils/request'

export function getService(params) {
  return request({
    url: '/admin/service/getServiceList',
    method: 'get',
    params
  })
}
