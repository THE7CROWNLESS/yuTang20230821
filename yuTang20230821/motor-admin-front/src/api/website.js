import request from '@/utils/request'

// 轮播图

export function getCarousel(params) {
  return request({
    url: '/admin/banner/getBannerList',
    method: 'get',
    params
  })
}

export function addCarousel(data) {
  return request({
    url: '/admin/banner/addBanner',
    method: 'post',
    data
  })
}

export function editCarousel(data) {
  return request({
    url: '/admin/banner/editBanner',
    method: 'post',
    data
  })
}

export function deleteCarousel(params) {
  return request({
    url: '/admin/banner/deleteBanner',
    method: 'get',
    params
  })
}

// 产品管理

export function getProduction(params) {
  return request({
    url: '/admin/product/getProductList',
    method: 'get',
    params
  })
}

export function addProduction(data) {
  return request({
    url: '/admin/product/addProduct',
    method: 'post',
    data
  })
}

export function editProduction(data) {
  return request({
    url: '/admin/product/editProduct',
    method: 'post',
    data
  })
}

export function deleteProduction(params) {
  return request({
    url: '/admin/product/deleteProduct',
    method: 'get',
    params
  })
}

// 产品分类

export function getProductionCate(params) {
  return request({
    url: '/admin/productCate/getProductCateList',
    method: 'get',
    params
  })
}

export function addProductionCate(data) {
  return request({
    url: '/admin/productCate/addProductCate',
    method: 'post',
    data
  })
}

export function editProductionCate(data) {
  return request({
    url: '/admin/productCate/editProductCate',
    method: 'post',
    data
  })
}

export function deleteProductionCate(params) {
  return request({
    url: '/admin/productCate/deleteProductCate',
    method: 'get',
    params
  })
}

// 公司简介

export function getCompany(params) {
  return request({
    url: '/admin/company/contact',
    method: 'get',
    params
  })
}

export function editCompany(data) {
  return request({
    url: '/admin/company/contact',
    method: 'post',
    data
  })
}

// 在线留言

export function getLeaveMessage(params) {
  return request({
    url: '/admin/message/getMessageList',
    method: 'get',
    params
  })
}

// 文章分类 (目前包括解决方案、服务中心)

export function getArticleCate(params) {
  return request({
    url: '/admin/articleCate/getArticleCateList',
    method: 'get',
    params
  })
}

export function addArticleCate(data) {
  return request({
    url: '/admin/articleCate/addArticleCate',
    method: 'post',
    data
  })
}

export function editArticleCate(data) {
  return request({
    url: '/admin/articleCate/editArticleCate',
    method: 'post',
    data
  })
}

export function deleteArticleCate(params) {
  return request({
    url: '/admin/articleCate/deleteArticleCate',
    method: 'get',
    params
  })
}

// 文章列表 (目前包括解决方案、服务中心)

export function getArticle(params) {
  return request({
    url: '/admin/article/getArticleList',
    method: 'get',
    params
  })
}

export function addArticle(data) {
  return request({
    url: '/admin/article/addArticle',
    method: 'post',
    data
  })
}

export function editArticle(data) {
  return request({
    url: '/admin/article/editArticle',
    method: 'post',
    data
  })
}

export function deleteArticle(params) {
  return request({
    url: '/admin/article/deleteArticle',
    method: 'get',
    params
  })
}

