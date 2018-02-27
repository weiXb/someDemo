<template>
  <div id="dialog-modal">
    <div class="dialog-panel" draggable="true">
      <span class="close-btn" @click="closeDialog">X</span>
      <span>
        <div class="title">原文：</div>
        <textarea>{{msg}}</textarea>
      </span>
      <span class="exchange-btn">
          <button @click="exchange">>>></button>
      </span>
      <span>
        <div class="title">转义：</div>
        <textarea readonly>{{exchangedMsg}}</textarea>
      </span>
    </div>
  </div>
</template>
<script>

  import Bus from '@/assets/bus';

  export default {
    name: 'dialog-panel',
    props: ['msg', 'showDialog'],
    data() {
      return {
        exchangedMsg: ''
      }
    },
    methods: {
      closeDialog: function () {
        Bus.$emit('closedialog', false);
      },
      exchange: function () {

        //todo发请求转换报文
        this.exchangedMsg = "转换后的报文";

      }
    }
  }
</script>

<style lang="scss">
  @import "../style/scss/dialog-panel";
</style>
