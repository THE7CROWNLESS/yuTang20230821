import request from "@/utils/request";

export function addCommand(data) {
  return request({
    url: "/admin/machine/command",
    method: "post",
    data
  });
}
