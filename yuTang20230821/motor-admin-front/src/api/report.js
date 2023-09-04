

import request from '@/utils/request'

  export function getReport(params) {
    return request({
      url: '/admin/report/getReportList',
      method: 'get',
      params
    })
  }