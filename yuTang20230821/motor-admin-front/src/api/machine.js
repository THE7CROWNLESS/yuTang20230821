import request from "@/utils/request";

export function getMachines(params) {
  return request({
    url: "/admin/machine/getMachineList",
    method: "get",
    params
  });
}

export function changeMachineType(params) {
  return request({
    url: "/admin/machine/editMachine",
    method: "post",
    data: params
  });
}

export function handleShip(params) {
  return request({
    url: "/admin/machine/delivery",
    method: "post",
    data: params
  });
}

export function getLogs(params) {
  return request({
    url: "/admin/machine/log",
    method: "get",
    params
  });
}

export function deleteMachine(params) {
  return request({
    url: "/admin/machine/deleteMachine",
    method: "get",
    params
  });
}

export function getMachineDetail(params) {
  return request({
    url: "/admin/machine/detail",
    method: "get",
    params
  });
}

export function getOxyElectric(params) {
  return request({
    url: "/admin/machine/electric",
    method: "get",
    params
  });
}

export function getOxyLog(params) {
  return request({
    url: "/admin/machine/getOxygenLog",
    method: "get",
    params
  });
}
