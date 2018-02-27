<template>
  <div class="page-btn-content">
    <div v-if="pageNums.length > 0 ">
      <span class="page-btn" @click="gotoFirst">首页</span>
      <span class="page-btn"
            v-for="num in pageNums"
            :class="{onselect:currentPage == num }"
            @click="selectPage(num)"
      >{{num}}</span>
      <span class="page-btn" @click="gotoLast">尾页</span>
    </div>
  </div>
</template>

<script>
  import Bus from '@/assets/bus';

  export default {
    name: 'page-btn',
    props: ['page'],
    data() {
      return {
        showPrePageNums: 10,
        currentPage: 1
      }
    },
    computed: {
      pageNums: function () {
        if (!this.page) return;

        let current = this.page.current;
        let totalPages = this.page.pages;

        this.currentPage = current;
        let rangeArr = (start, end) => Array(end - start + 1).fill(0).map((val, index) => index + start);

        if (totalPages < this.showPrePageNums * 2)
          return rangeArr(1, totalPages);

        if (current <= this.showPrePageNums)
          return rangeArr(1, this.showPrePageNums * 2);

        if (totalPages - current <= this.showPrePageNums)
          return rangeArr(totalPages - this.showPrePageNums * 2 + 1, totalPages);

        return rangeArr(current - this.showPrePageNums + 1, current + this.showPrePageNums)

      }
    },
    methods: {
      selectPage: function (num) {
        this.page.current = num;
        Bus.$emit("querypileup", this.page);
      },
      gotoFirst: function () {
        this.selectPage(1);
      },
      gotoLast: function () {
        this.selectPage(this.page.pages);
      }
    }
  }
</script>

<style lang="scss">
  @import "../style/scss/page-btns";
</style>
