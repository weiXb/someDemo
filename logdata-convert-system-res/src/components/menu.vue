<template>
  <div id="left-menu">
    <div v-for="menu in menus">

      <div v-if="menu.childNode && menu.childNode.length > 0">
        <div class=" menu-button l1-menu"
             :class="{onselect:menu.id == active && menu.url!='/main'}"
             @click="clickMenu(menu)"
        >
          {{menu.name}}
          <span v-if="!menu.nodeShow"
                class="right-arrow-icon">&gt;</span>
        </div>
      </div>
      <div v-else>
        <router-link :to="menu.url">
          <div class=" menu-button l1-menu"
               :class="{onselect:menu.id == active && menu.url!='/main'}"
               @click="clickMenu(menu)"
          >
            {{menu.name}}

          </div>
        </router-link>
      </div>

      <div v-for="child in menu.childNode" v-if="menu.childNode.length > 0 && menu.nodeShow">
        <router-link :to="child.childUrl">
          <div class="menu-button l2-menu"
               :class="{onselect:child.id == active}"
               @click="selectChild(child.id)"
          >
            {{child.childName}}
          </div>
        </router-link>
      </div>

    </div>
  </div>

</template>

<script>
  //菜单数据
  let data = [
    {
      'id': 1,
      'name': '首页',
      'url': '/main',
      'childNode': [],
      'nodeShow': false
    },
    {
      'id': 2,
      'name': '报文日志',
      'url': 'www.baidu.com',
      'childNode': [{
        'id': 201,
        'childName': '原始报文',
        'childUrl': 'pileup'
      }, {
        'id': 202,
        'childName': '遥信日志',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 203,
        'childName': '遥测日志',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 204,
        'childName': '电度日志',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 205,
        'childName': '鉴权、启停指令应答',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 206,
        'childName': '整车充电机实时数据',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 207,
        'childName': '下发计费模型返回',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 209,
        'childName': '计损离线交易记录',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 210,
        'childName': 'VIN码鉴权',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 211,
        'childName': '卡请求鉴权数据',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 212,
        'childName': '充电握手',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 213,
        'childName': '参数配置阶段',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 208,
        'childName': 'BMS信息',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 214,
        'childName': '充电机信息',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 215,
        'childName': '充电信息',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 216,
        'childName': 'BMS故障',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 217,
        'childName': '充电机故障',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 218,
        'childName': '充电结束阶段',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 219,
        'childName': '错误报文',
        'childUrl': 'www.baidu2.com'
      }, {
        'id': 220,
        'childName': '地锁状态',
        'childUrl': 'www.baidu2.com'
      }],
      'nodeShow': true
    },
    {
      'id': 3,
      'name': '报文解析',
      'url': 'www.baidu.com',
      'childNode': [{
        'id': 301,
        'childName': '报文日志1',
        'childUrl': 'www.baidu1.com'
      }, {
        'id': 302,
        'childName': '报文日志2',
        'childUrl': 'www.baidu2.com'
      }],
      'nodeShow': false
    }
  ];

  export default {
    name: 'left-menu',
    data() {
      return {
        menus: data,
        active: ''
      }
    },
    created() {
      for (let menu of data) {

        if (this.$route.path.startsWith(menu.url,1)) {
          this.active = menu.id;
          return;
        }
        for (let child of menu.childNode) {
          if (this.$route.path.startsWith(child.childUrl,1)) {
            this.active = child.id;
            return;
          }
        }

      }
    },
    methods: {
      clickMenu: function (menu) {
        if (menu.childNode && menu.childNode.length > 0) {
          menu.nodeShow = !menu.nodeShow;
        } else {
          this.selectChild(menu.id);
        }
      },
      selectChild: function (id) {
        this.active = id;
      }
    }
  }

</script>

<style lang="scss">
  @import "../style/scss/menu";
</style>
