import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */
import componentsRouter from './modules/components'
import chartsRouter from './modules/charts'
import tableRouter from './modules/table'
import nestedRouter from './modules/nested'

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
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
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
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path:'/',
    component:Layout,
    redirect: '/page',
    children: [{
      path: 'page',
      component:() => import('@/views/home-page/HomePage'),
      name:'homePage',
      meta:{
        title:'首页',
        icon: 'documentation',
        affix: true
      }
    }]
  },
  {
    path: '/profile',
    component: Layout,
    redirect: '/profile/index',
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/profile/index'),
        name: 'Profile',
        meta: { title: 'Profile', icon: 'user', noCache: true }
      }
    ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [

  {
    path: '/management',
    component: Layout,
    redirect: '/management/staff',
    meta: {
      icon: 'people',
      roles: ['op:memberManage'] // you can set roles in root nav
    },
    children: [
      {
        path: 'staff',
        component: () => import('@/views/staff-management/StaffManagement'),
        name: 'staffManagement',
        meta: {
          title: '员工管理',
          roles: ['op:memberManage'] // or you can only set roles in sub nav
        }
      }
    ]
  },



  {
    path: '/projectCount',
    component: Layout,
    redirect: '/projectCount/detail',
    meta: {
      icon: 'chart',
      roles: ['op:checkProjectCount'] // you can set roles in root nav
    },
    children: [
      {
        path: 'detail',
        component: () => import('@/views/project-count/ProjectCount'),
        name: 'projectCount',
        meta: {
          title: '项目统计',
          roles: ['op:checkProjectCount'] // or you can only set roles in sub nav
        }
      }
    ]
  },



  {
    path: '/untreated',
    component: Layout,
    redirect: '/untreated/project',
    meta: {
      icon: 'el-icon-folder-delete',
      roles: ['op:lookUntreated'] // you can set roles in root nav
    },
    children: [
      {
        path: 'project',
        component: () => import('@/views/untreated-project/UntreatedProject'),
        name: 'untreatedProject',
        meta: {
          title: '未处理的审批项目',
          roles: ['op:lookUntreated']
        }
      }
    ]
  },

  {
    path: '/treated',
    component: Layout,
    redirect: '/treated/project',
    meta: {
      icon: 'el-icon-folder-checked',
      roles: ['op:lookTreated']
    },
    children: [
      {
        path: 'project',
        component: () => import('@/views/treated-project/TreatedProject'),
        name: 'treatedProject',
        meta: {
          title: '处理过的审批项目',
          roles: ['op:lookTreated']
        }
      }
    ]
  },

  {
    path: '/lookGroup',
    component: Layout,
    redirect: '/lookGroup/groupMember', // 查看小组成员  权限:组长
    meta: {
      icon: 'people',
      roles: ['op:lookGroupMember']
    },
    children: [
      {
        path: 'groupMember',
        component: () => import('@/views/look-group-member/LookGroupMember'),
        name: 'lookGroupMember',
        meta: {
          title: '查看小组成员',
          roles: ['op:lookGroupMember']
        }
      }
    ]
  },

  {
    path: '/lookManage',
    component: Layout,
    redirect: '/lookManage/manageGroup', // 查看管理管理小组
    meta: {
      icon: 'people',
      roles: ['op:lookManageGroup']
    },
    children: [
      {
        path: 'manageGroup',
        component: () => import('@/views/look-manage-group/LookManageGroup'),
        name: 'lookManageGroup',
        meta: {
          title: '查看管理小组',
          roles: ['op:lookManageGroup']
        }
      }
    ]
  },

  {
    path: '/my',
    component: Layout,
    redirect: '/my/task',
    meta: {
      icon: 'tab',
      roles: ['op:myTask']
    },
    children: [
      {
        path: 'task',
        component: () => import('@/views/my-task/MyTask'),
        name: 'task',
        meta: {
          title: '我的任务',
          roles: ['op:myTask']
        }
      }
    ]
  },

  {
    path: '/myself',
    component: Layout,
    redirect: '/myself/info',
    meta: {
      title: '个人中心',
      icon: 'el-icon-user',
      roles: ['op:lookMe']
    },
    children: [
      {
        path: 'info',
        component: () => import('@/views/myself-info/MyselfInfo'),
        name: 'myselfInfo',
        meta: {
          title: '个人信息',
          roles: ['op:lookMe']
        }
      },
      {
        path: 'changepw',
        component: () => import('@/views/myself-info/ChangePssword'),
        name: 'changePssword',
        meta: {
          title: '修改密码',
          roles: ['op:lookMe']
        }
      }
    ]
  },

  {
    path: '/viewApproved',
    component: Layout,
    redirect: '/viewApproved/items',
    meta: {
      icon: 'el-icon-s-promotion',
      roles: ['op:lookApproval']
    },
    children: [
      {
        path: 'items',
        component: () => import('@/views/view-approvedItems/ViewApprovedItems'),
        name: 'viewApproved',
        meta: {
          title: '查看审批项目',
          roles: ['op:lookProject']
        },
      }
    ]
  },

  {
    path: '/view',
    component: Layout,
    redirect: '/view/project',
    meta: {
      icon: 'el-icon-s-flag',
      title: '查看工程',
      roles: ['op:lookProject']
    },
    children: [
      {
        path: 'project',
        hidden: true,
        component: () => import('@/views/view-project/index'),
        redirect: '/view/project/ListProject',
        name: 'viewProject',
        meta: {
          roles: ['op:lookProject']
        },
        children: [
          {
            path: 'ListProject',
            hidden: true,
            component: () => import('@/views/view-project/ViewProject'),
            name: 'listProject',
            meta: {
              title: '工程列表',
              roles: ['op:lookProject'],
              activeMenu:"/view"
            }
          },
          {
            path: 'DeatilProject',
            hidden: true,
            component: () => import('@/views/view-project/ViewDetail'),
            name: 'deatilProject',
            meta: {
              title: '工程详情',
              roles: ['op:lookProject'],
              activeMenu:"/view"
            }
          },
          {
            path: 'UpdateProject',
            hidden: true,
            component: () => import('@/views/view-project/UpdateProject'),
            name: 'updateProject',
            meta: {
              title: '工程详情',
              roles: ['op:lookProject'],
              activeMenu:"/view"
            }
          }
        ]
      }
    ]
  },

  {
    path: '/apply',
    component: Layout,
    redirect: '/apply/project',
    meta: {
      icon: 'el-icon-edit-outline',
      roles: ['op:submit']
    },
    children: [
      {
        path: 'project',
        component: () => import('@/views/apply-project/ApplyProject'),
        name: 'applyProject',
        meta: {
          title: '申请项目',
          roles: ['op:submit']
        }
      }
    ]
  },

  {
    path: '/permission',
    component: Layout,
    redirect: '/permission/page',
    alwaysShow: true, // will always show the root menu
    name: 'Permission',
    meta: {
      title: '权限',
      icon: 'lock',
      roles: ['admin:teach', 'admin:exam'] // you can set roles in root nav
    },
    children: [
      {
        path: 'page',
        component: () => import('@/views/permission/page'),
        name: 'PagePermission',
        meta: {
          title: '页面权限',
          roles: ['admin:teach'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'directive',
        component: () => import('@/views/permission/directive'),
        name: 'DirectivePermission',
        meta: {
          title: 'Directive Permission'
          // if do not set roles, means: this page does not require permission
        }
      },
      {
        path: 'role',
        component: () => import('@/views/permission/role'),
        name: 'RolePermission',
        meta: {
          title: '角色',
          roles: ['admin:exam']
        }
      }
    ]
  },

  {
    path: '/icon',
    component: Layout,
    redirect: '/icon/index',
    meta: {
      icon: 'lock',
      roles: ['admin:teach', 'admin:exam']
    },
    children: [
      {
        path: 'index',
        component: () => import('@/views/icons/index'),
        name: 'Icons',
        meta: { title: 'Icons', icon: 'icon', noCache: true, roles: ['admin:teach'] }
      }
    ]
  },

  /** when your routing map is too long, you can split it into small modules **/
  componentsRouter,
  chartsRouter,
  nestedRouter,
  tableRouter,

  {
    path: '/example',
    component: Layout,
    redirect: '/example/list',
    name: 'Example',
    alwaysShow: true,
    meta: {
      title: '例子',
      icon: 'el-icon-s-help',
      roles: ['admin:job', 'admin:talk']
    },
    children: [
      {
        path: 'create',
        component: () => import('@/views/example/create'),
        name: 'CreateArticle',
        meta: { title: '创建文章', icon: 'edit', roles: ['admin:job'] }
      },
      {
        path: 'edit/:id(\\d+)',
        component: () => import('@/views/example/edit'),
        name: 'EditArticle',
        meta: { title: '编辑文章', noCache: true, activeMenu: '/example/list' },
        hidden: true
      },
      {
        path: 'list',
        component: () => import('@/views/example/list'),
        name: 'ArticleList',
        meta: { title: '文章列表', icon: 'list', roles: ['admin:talk'] }
      }
    ]
  },

  {
    path: '/tab',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/tab/index'),
        name: 'Tab',
        meta: { title: 'Tab', icon: 'tab' }
      }
    ]
  },

  {
    path: '/error',
    component: Layout,
    redirect: 'noRedirect',
    name: 'ErrorPages',
    meta: {
      title: 'Error Pages',
      icon: '404'
    },
    children: [
      {
        path: '401',
        component: () => import('@/views/error-page/401'),
        name: 'Page401',
        meta: { title: '401', noCache: true }
      },
      {
        path: '404',
        component: () => import('@/views/error-page/404'),
        name: 'Page404',
        meta: { title: '404', noCache: true }
      }
    ]
  },

  {
    path: '/error-log',
    component: Layout,
    children: [
      {
        path: 'log',
        component: () => import('@/views/error-log/index'),
        name: 'ErrorLog',
        meta: { title: 'Error Log', icon: 'bug' }
      }
    ]
  },

  {
    path: '/excel',
    component: Layout,
    redirect: '/excel/export-excel',
    name: 'Excel',
    meta: {
      title: 'Excel',
      icon: 'excel'
    },
    children: [
      {
        path: 'export-excel',
        component: () => import('@/views/excel/export-excel'),
        name: 'ExportExcel',
        meta: { title: 'Export Excel' }
      },
      {
        path: 'export-selected-excel',
        component: () => import('@/views/excel/select-excel'),
        name: 'SelectExcel',
        meta: { title: 'Export Selected' }
      },
      {
        path: 'export-merge-header',
        component: () => import('@/views/excel/merge-header'),
        name: 'MergeHeader',
        meta: { title: 'Merge Header' }
      },
      {
        path: 'upload-excel',
        component: () => import('@/views/excel/upload-excel'),
        name: 'UploadExcel',
        meta: { title: 'Upload Excel' }
      }
    ]
  },

  {
    path: '/zip',
    component: Layout,
    redirect: '/zip/download',
    alwaysShow: true,
    name: 'Zip',
    meta: { title: 'Zip', icon: 'zip' },
    children: [
      {
        path: 'download',
        component: () => import('@/views/zip/index'),
        name: 'ExportZip',
        meta: { title: 'Export Zip' }
      }
    ]
  },

  {
    path: '/pdf',
    component: Layout,
    redirect: '/pdf/index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/pdf/index'),
        name: 'PDF',
        meta: { title: 'PDF', icon: 'pdf' }
      }
    ]
  },
  {
    path: '/pdf/download',
    component: () => import('@/views/pdf/download'),
    hidden: true
  },

  {
    path: '/theme',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/theme/index'),
        name: 'Theme',
        meta: { title: 'Theme', icon: 'theme' }
      }
    ]
  },

  {
    path: '/clipboard',
    component: Layout,
    children: [
      {
        path: 'index',
        component: () => import('@/views/clipboard/index'),
        name: 'ClipboardDemo',
        meta: { title: 'Clipboard', icon: 'clipboard' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://github.com/PanJiaChen/vue-element-admin',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
