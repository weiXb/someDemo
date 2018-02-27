<template>
  <div id="app">
    <div v-if="isLogin">
      <Index/>
    </div>
    <div v-else>
      <router-view/>
    </div>
  </div>
</template>

<script>
  import Index from "@/components/index"
  import Bus from '@/assets/bus'

  export default {
    name: 'App',
    data() {
      return {
        isLogin: Bus.isLogin
      }
    },
    components: {Index}
  }
</script>

<style lang="scss">
  @import "./style/style";
</style>
