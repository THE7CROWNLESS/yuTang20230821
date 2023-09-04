import request from '@/utils/request'

export function getMeal(params) {
  return request({
    url: '/admin/package/getPackageList',
    method: 'get',
    params
  })
}

export function addMeal(params) {
  return request({
    url: '/admin/package/addPackage',
    method: 'post',
    params
  })
}


export function editMeal(params) {
  return request({
    url: '/admin/package/editPackage',
    method: 'post',
    params
  })
}

export function deleteMeal(params) {
  return request({
    url: '/admin/package/deletePackage',
    method: 'get',
    params
  })
}


