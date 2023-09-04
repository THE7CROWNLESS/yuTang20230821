import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

/* Layout */
import Layout from "@/layout";

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true
  },

  //   {
  //     path: "/404",
  //     component: () => import("@/views/404"),
  //     hidden: true
  //   },
  {
    path: "/profile",
    component: Layout,
    redirect: "/profile/index",
    hidden: true,
    children: [
      {
        path: "index",
        component: () => import("@/views/profile/index"),
        name: "Profile",
        meta: { title: "基本资料", icon: "user", noCache: true }
      }
    ]
  },
  {
    path: "/",
    component: Layout,
    redirect: "/user",
    meta: {
      title: "用户管理",
      icon: "el-icon-s-custom"
    },
    children: [
      {
        path: "user",
        name: "Users",
        component: () => import("@/views/user/index"),
        meta: {
          title: "后台管理员",
          icon: "el-icon-s-custom"
        }
      },
      {
        path: "authority",
        name: "AuthorityIndex",
        component: () => import("@/views/user/authority"),
        meta: {
          title: "权限管理",
          icon: "el-icon-s-management"
        }
      },
      {
        path: "role",
        name: "RoleIndex",
        component: () => import("@/views/user/role"),
        meta: {
          title: "角色管理",
          icon: "user"
        }
      },
      {
        path: "front",
        name: "FrontIndex",
        component: () => import("@/views/user/front"),
        meta: {
          title: "前台会员",
          icon: "el-icon-user"
        }
      }
    ]
  },

  //   {
  //     path: '/pond',
  //     component: Layout,
  //     redirect: '/pond/index',
  //     name: 'Pond',
  //     meta: {
  //       title: '鱼塘管理',
  //       icon: 'el-icon-s-management'
  //     },
  //     children: [
  //       {
  //         path: 'index',
  //         name: 'PondIndex',
  //         component: () => import('@/views/pond/index'),
  //         meta: {
  //           title: '鱼塘管理',
  //           icon: 'el-icon-s-management'
  //         }
  //       }
  //     ]
  //   },

  {
    path: "/machine",
    component: Layout,
    redirect: "/machine/manage",
    name: "Machine",
    meta: {
      title: "设备管理",
      icon: "el-icon-monitor"
    },
    children: [
      {
        path: "manage",
        name: "MachineManage",
        component: () => import("@/views/machine/index"),
        meta: {
          title: "设备管理",
          icon: "el-icon-cpu"
        }
      },
      {
        path: "log/:id(\\d+)",
        component: () => import("@/views/machine/log"),
        name: "MachineLog",
        meta: {
          title: "操作日志",
          noCache: true,
          activeMenu: "/machine/manage"
        },
        hidden: true
      }
    ]
  },

  {
    path: "/console",
    component: Layout,
    redirect: "/console/index",
    name: "Console",
    meta: {
      title: "控制台",
      icon: "el-icon-connection"
    },
    children: [
      {
        path: "index",
        name: "ConsoleIndex",
        component: () => import("@/views/console/index"),
        meta: {
          title: "控制台",
          icon: "el-icon-connection"
        }
      }
    ]
  },

  {
    path: "/report",
    component: Layout,
    redirect: "/report/index",
    name: "Report",
    meta: {
      title: "报告日志",
      icon: "el-icon-document"
    },
    children: [
      {
        path: "index",
        name: "ReportIndex",
        component: () => import("@/views/report/index"),
        meta: {
          title: "报告日志",
          icon: "el-icon-document"
        }
      }
    ]
  },

  {
    path: "/meal",
    component: Layout,
    redirect: "/meal/index",
    name: "Meal",
    meta: {
      title: "套餐管理",
      icon: "el-icon-tickets"
    },
    children: [
      {
        path: "index",
        name: "MealIndex",
        component: () => import("@/views/meal/index"),
        meta: {
          title: "套餐管理",
          icon: "el-icon-tickets"
        }
      },
      {
        path: "add",
        component: () => import("@/views/meal/add"),
        name: "MealAdd",
        meta: {
          title: "新增套餐",
          noCache: true,
          activeMenu: "/meal/add"
        },
        hidden: true
      },
      {
        path: "edit/:id(\\d+)",
        component: () => import("@/views/meal/edit"),
        name: "MealEdit",
        meta: {
          title: "编辑套餐",
          noCache: true,
          activeMenu: "/meal/edit"
        },
        hidden: true
      }
    ]
  },

  {
    path: "/order",
    component: Layout,
    redirect: "/order/index",
    name: "Order",
    meta: {
      title: "订单管理",
      icon: "el-icon-s-order"
    },
    children: [
      {
        path: "index",
        name: "OrderIndex",
        component: () => import("@/views/order/index"),
        meta: {
          title: "订单管理",
          icon: "el-icon-s-order"
        }
      }
    ]
  },

  {
    path: "/service",
    component: Layout,
    redirect: "/service/index",
    name: "Service",
    meta: {
      title: "维修服务",
      icon: "el-icon-first-aid-kit"
    },
    children: [
      {
        path: "index",
        name: "ServiceIndex",
        component: () => import("@/views/service/index"),
        meta: {
          title: "维修服务",
          icon: "el-icon-first-aid-kit"
        }
      }
    ]
  },

  {
    path: "/feedback",
    component: Layout,
    redirect: "/feedback/index",
    name: "Feedback",
    meta: {
      title: "留言反馈",
      icon: "el-icon-message"
    },
    children: [
      {
        path: "index",
        name: "FeedbackIndex",
        component: () => import("@/views/feedback/index"),
        meta: {
          title: "留言反馈",
          icon: "el-icon-message"
        }
      }
    ]
  },

  {
    path: "/website",
    component: Layout,
    redirect: "/website/index",
    name: "Website",
    meta: {
      title: "前台内容管理",
      icon: "el-icon-s-home"
    },
    children: [
      {
        path: "carousel",
        name: "Carousel",
        component: () => import("@/views/website/carousel"),
        meta: {
          title: "轮播图管理",
          icon: "el-icon-picture"
        }
      },
      {
        path: "company",
        name: "Company",
        component: () => import("@/views/website/company"),
        meta: {
          title: "公司简介",
          icon: "el-icon-office-building"
        }
      },
      {
        path: "promo",
        name: "Promo",
        component: () => import("@/views/website/promo"),
        meta: {
          title: "公司宣传视频",
          icon: "el-icon-video-camera"
        }
      },
      {
        path: "customer",
        name: "Customer",
        component: () => import("@/views/website/customer"),
        meta: {
          title: "合作客户",
          icon: "el-icon-suitcase"
        }
      },

      {
        path: "productCate",
        name: "ProductCate",
        component: () => import("@/views/website/productCate"),
        meta: {
          title: "产品分类",
          icon: "el-icon-s-grid"
        }
      },
      {
        path: "production",
        name: "Production",
        component: () => import("@/views/website/production"),
        meta: {
          title: "产品列表",
          icon: "el-icon-files"
        }
      },

      {
        path: "programCate",
        name: "ProgramCate",
        component: () => import("@/views/website/programCate"),
        meta: {
          title: "方案分类",
          icon: "el-icon-menu"
        }
      },
      {
        path: "program",
        name: "Program",
        component: () => import("@/views/website/program"),
        meta: {
          title: "方案列表",
          icon: "el-icon-magic-stick"
        }
      },

      {
        path: "serviceCate",
        name: "ServiceCate",
        component: () => import("@/views/website/serviceCate"),
        meta: {
          title: "服务分类",
          icon: "el-icon-menu"
        }
      },
      {
        path: "service",
        name: "Service",
        component: () => import("@/views/website/service"),
        meta: {
          title: "服务列表",
          icon: "el-icon-document-copy"
        }
      },
      //   {
      //     path: 'leaveMessage',
      //     name: 'LeaveMessage',
      //     component: () => import('@/views/website/leaveMessage'),
      //     meta: {
      //       title: '在线留言',
      //       icon: 'el-icon-message'
      //     }
      //   },
      {
        path: "shopping",
        name: "Shopping",
        component: () => import("@/views/website/shopping"),
        meta: {
          title: "在线购买",
          icon: "el-icon-shopping-cart-full"
        }
      }
    ]
  },
  // {
  //   path: '/example',
  //   component: Layout,
  //   redirect: '/example/table',
  //   name: 'Example',
  //   meta: { title: 'Example', icon: 'el-icon-s-help' },
  //   children: [
  //     {
  //       path: 'table',
  //       name: 'Table',
  //       component: () => import('@/views/table/index'),
  //       meta: { title: 'Table', icon: 'table' }
  //     },
  //     {
  //       path: 'tree',
  //       name: 'Tree',
  //       component: () => import('@/views/tree/index'),
  //       meta: { title: 'Tree', icon: 'tree' }
  //     }
  //   ]
  // },

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       name: 'Menu2',
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  // 404 page must be placed at the end !!!
  {
    path: "*",
    redirect: "/404",
    hidden: true
  }
];

const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({
      y: 0
    }),
    routes: constantRoutes
  });

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}

export default router;
