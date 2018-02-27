<template>
  <div>
    <Header/>
    <div>
      <Menu/>
      <div id="main-content">
        <router-view/>
      </div>
    </div>
  </div>
</template>

<script>
  import Menu from '@/components/menu'
  import Header from '@/components/header'

  export default {
    components: {Menu, Header}
  }
</script>
