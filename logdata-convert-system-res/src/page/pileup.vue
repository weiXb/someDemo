<template>
  <div>
    <SearchBtn :page="page"/>
    <div class="list-content">
      <div class="list-title">
        <span class="list-cell list-title-cell">id</span>
        <span class="list-cell list-title-cell">logTime</span>
        <span class="list-cell list-title-cell">logData</span>
      </div>

      <div v-if="page.records.length>0">
        <div v-for="record in page.records" class="list-body">
          <span class="list-cell list-body-cell">{{record.id}}</span>
          <span class="list-cell list-body-cell">{{record.logTime}}</span>

          <span class="list-cell list-body-cell pointer"
                @click="msgDetail(record.logData)"
                :title="record.logData">{{record.logData}}</span>
        </div>
      </div>
      <div v-else-if="isLoading">
        正在加载数据
      </div>
      <div v-else>
        暂无数据
      </div>

    </div>
    <PageBtns :page="page"/>
    <Dialog :msg="msgdata" :showDialog="showDialog" v-if="showDialog"/>
  </div>

</template>

<script>

  import PageBtns from '@/components/pageButtons'
  import SearchBtn from '@/components/searchInput'
  import Dialog from '@/components/dialog'
  import Bus from '@/assets/bus';

  export default {
    name: 'main-content',
    data() {
      return {
        page: {
          id: '',
          current: 1,
          size: 10,
          records: ''
        },
        isLoading: false,
        msgdata: '',
        showDialog: false
      }
    },
    created() {
      this.searchData(this.page);
      let $this = this;
      Bus.$on('querypileup', function (data) {
        $this.searchData(data);
      }).$on('closedialog', function (data) {
        $this.showDialog = data;
      });
    },
    beforeDestroy() {
      Bus.$off('querypileup').$off('closedialog');
    },
    methods: {
      searchData: function (data) {
        this.isLoading = true;
        this.$api.post('/log/pile-up', data, r => {
          this.page = Object.assign({}, data, r);
          this.isLoading = false;
        });
      },
      msgDetail: function (msg) {
        this.showDialog = true;
        this.msgdata = msg;
      }
    },
    components: {PageBtns, SearchBtn, Dialog}

  }
</script>

<style lang="scss">
  @import "../style/scss/pileup";
</style>
