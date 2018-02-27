<template>
  <div id="search-input">
    <input type="text" maxlength="20"
           v-model="page.id"
           @keyup.enter="search"/>

    <Datepicker :page="page"/>

    <button @click="search">搜索</button>
  </div>
</template>

<script>

  import Bus from '@/assets/bus';
  import Datepicker from '@/components/datetimepicker';

  export default {
    name: 'search-input',
    props: ['page'],
    methods: {
      search() {
        this.page.pages = 0;
        this.page.current = 1;
        this.page.records = [];
        Bus.$emit("querypileup", this.page);
      }
    },
    components: {Datepicker}
  }
</script>

<style lang="scss">
  @import "../style/scss/search-input";
</style>
