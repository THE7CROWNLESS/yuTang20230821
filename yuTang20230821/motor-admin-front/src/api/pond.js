

import request from '@/utils/request'

  export function getPond(params) {
    return request({
      url: '/admin/pond/getPondList',
      method: 'get',
      params
    })
  }