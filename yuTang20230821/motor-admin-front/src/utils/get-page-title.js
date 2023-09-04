import defaultSettings from "@/settings";

const title = defaultSettings.title || "转多多智能养殖平台管理系统";

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`;
  }
  return `${title}`;
}
