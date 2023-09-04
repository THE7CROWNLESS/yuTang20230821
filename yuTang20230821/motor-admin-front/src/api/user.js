import request from "@/utils/request";

// export function login(data) {
//   return request({
//     url: '/vue-admin-template/user/login',
//     method: 'post',
//     data
//   })
// }

export function editAuthor(data) {
  return request({
    url: "/admin/user/edit",
    method: "post",
    data
  });
}

export function login(data) {
  return request({
    url: "/admin/login/login",
    method: "post",
    data
  });
}

export function getInfo(token) {
  return request({
    url: "/vue-admin-template/user/info",
    method: "get",
    params: { token }
  });
}

export function logout() {
  return request({
    url: "/vue-admin-template/user/logout",
    method: "post"
  });
}

//后台管理员
export function getManagerList(params) {
  return request({
    url: "/admin/user/userListJson",
    method: "get",
    params
  });
}

export function addManager(data) {
  return request({
    url: "/admin/user/addUser",
    method: "post",
    data
  });
}
export function editManager(data) {
  return request({
    url: "/admin/user/editUser",
    method: "post",
    data
  });
}
export function deleteManager(params) {
  return request({
    url: "/admin/user/deleteUser",
    method: "get",
    params
  });
}
export function getGroup() {
  return request({
    url: "/admin/user/getGroup",
    method: "get"
  });
}

// 前台会员

export function getFrontUsers(params) {
  return request({
    url: "/admin/member/getMemberList",
    method: "get",
    params
  });
}

// 权限
export function getAuthorityList() {
  return request({
    url: "/admin/user/getTableData",
    method: "get"
  });
}

export function addAuthority(data) {
  return request({
    url: "/admin/user/addRule",
    method: "post",
    data
  });
}

export function editAuthority(data) {
  return request({
    url: "/admin/user/editRule",
    method: "post",
    data
  });
}

export function deleteAuthority(params) {
  return request({
    url: "/admin/user/delRule",
    method: "get",
    params
  });
}

//角色
export function getRoleList(params) {
  return request({
    url: "/admin/user/roleListJson",
    method: "get",
    params
  });
}

export function getAuthorityJson(data) {
  return request({
    url: "/admin/user/getJson",
    method: "post",
    data
  });
}

export function addRole(data) {
  return request({
    url: "/admin/user/addRole",
    method: "post",
    data
  });
}

export function editRole(data) {
  return request({
    url: "/admin/user/editRole",
    method: "post",
    data
  });
}

export function deleteRole(data) {
  return request({
    url: "/admin/user/delRole",
    method: "post",
    data
  });
}
