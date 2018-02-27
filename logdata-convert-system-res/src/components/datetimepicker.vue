<template>
  <vue-datepicker-local v-model="timeRange"
                        format="YYYY-MM-DD HH:mm:ss"
                        :local="local"
                        show-buttons
                        @confirm="selectedDate"/>
</template>

<script>
  import VueDatepickerLocal from 'vue-datepicker-local'

  export default {
    components: {
      VueDatepickerLocal
    },
    props:['page'],
    data() {
      return {
        timeRange: [],
        local: {
          dow: 1, // Sunday is the first day of the week
          hourTip: '选择小时', // tip of select hour
          minuteTip: '选择分钟', // tip of select minute
          secondTip: '选择秒', // tip of select second
          yearSuffix: '', // suffix of head
          monthsHead: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'], // months of head
          months: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'], // months of panel
          weeks: ['一', '二', '三', '四', '五', '六', '日'], // weeks,
          cancelTip: '取消', // text for cancel button for daterange picker
          submitTip: '确定' // text for submit button for daterange picker
        }
      }
    },
    methods: {
      selectedDate(date) {
        this.timeRange = date;
        this.page.startTime = date[0].Format("yyyy-MM-dd HH:mm:ss");
        this.page.endTime = date[1].Format("yyyy-MM-dd HH:mm:ss");
      }
    }
  }
</script>
<style>
  .datepicker__button-cancel, .datepicker__button-select {
    width: 60px;
  }
</style>
