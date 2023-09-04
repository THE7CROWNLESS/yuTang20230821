import request from '@/utils/request'

export function getOrder(params) {
  return request({
    url: '/admin/order/getOrderList',
    method: 'get',
    params
  })
}
