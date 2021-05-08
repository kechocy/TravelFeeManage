<template>
    <div class="trainContainer">
        <div class="mainContainer">
            <el-form :inline="true" :model="trainForm" size="small" class="trainForm">
                <h1 class="title">全国火车时刻表在线查询</h1>
                <el-form-item label="按列车车次查询">
                    <el-input v-model="trainForm.num" placeholder="车次" style="width: 210px">
                        <template #prepend>
                            <el-select v-model="trainForm.name" placeholder="车型" style="width: 80px">
                                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item >
                    <el-date-picker v-model="trainForm.date1" type="date" placeholder="选择日期" style="width: 140px"></el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="primary" @click="onSubmitByTrainID">查询</el-button>
                </el-form-item>
                <br/>
                <el-form-item label="按始终地点查询">
                    <el-input v-model="trainForm.startCity" placeholder="出发地" style="width: 100px"></el-input>
                </el-form-item>
                <el-form-item >
                    <el-input v-model="trainForm.endCity" placeholder="目的地" style="width: 100px"></el-input>
                </el-form-item>
                <el-form-item >
                    <el-date-picker v-model="trainForm.date2" type="date" placeholder="选择日期" style="width: 140px"></el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="primary" @click="onSubmitByCityName">查询</el-button>
                </el-form-item>
            </el-form>
            <template>
                <el-table
                border
                :data="tableData"
                height="300"
                style="width: 80%">
                <el-table-column
                    prop="trainNum"
                    label="车号"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="startCity"
                    label="起始站"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="endCity"
                    label="终到站"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="startTime"
                    label="发车时间"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="endTime"
                    label="到达时间"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="totalTime"
                    label="历时">
                </el-table-column>
                </el-table>
            </template>
        </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      trainForm: {
        name: '',
        num: '',
        date1: '',
        startCity: '',
        endCity: '',
        date2: ''
      },
      options: [{
        value: 'G',
        label: 'G'
      }, {
        value: 'K',
        label: 'K'
      }, {
        value: 'T',
        label: 'T'
      }],
      tableData: [{
        trainNum: '',
        startCity: '',
        endCity: '',
        startTime: '',
        endTime: '',
        totalTime: ''
      }]
    }
  },
  methods: {
    onSubmitByTrainID () {
      this.$message.error('暂未提供此功能')
    },
    onSubmitByCityName () {
      if (this.trainForm.date2 === '') { this.$message.error('日期不能为空') } else {
        var _this = this
        const date = this.dateFormat(this.trainForm.date2).split('-')
        this.$http.get('train/getByCityName', {
          params: {
            startCity: this.trainForm.startCity,
            endCity: this.trainForm.endCity,
            year: date[0],
            mon: date[1],
            day: date[2]
          }
        })
          .then(function (response) {
            var obj = JSON.parse(response.data.response)
            if (obj.data.length === 0) { _this.$message.error('暂无数据') } else {
              _this.tableData.splice(0, _this.tableData.length)
              for (var i = 0; i < obj.data.length; i++) {
                _this.tableData.push({ trainNum: obj.data[i].车号, startCity: obj.data[i].起始站, endCity: obj.data[i].终到站, startTime: obj.data[i].发车时间, endTime: obj.data[i].到达时间, totalTime: obj.data[i].历时 })
              }
            }
          })
          .catch(function (error) {
            console.log(error)
          })
      }
    },
    dateFormat (dateData) {
      var y = dateData.getFullYear()
      var m = dateData.getMonth() + 1
      m = m < 10 ? ('0' + m) : m
      var d = dateData.getDate()
      d = d < 10 ? ('0' + d) : d
      const time = y + '-' + m + '-' + d
      return time
    }
  }
}
</script>
<style lang="less" scoped>
.trainContainer{
    background-image: url("../assets/img/train.jpg");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    height: calc(100% - 61px);
    width: 100%;
    position: relative;
    .mainContainer{
        height: 80%;
        width: 60%;
        background-color: rgba(245, 240, 240, 0.8);
        border-radius: 5px;
        -webkit-box-shadow:0px 4px 4px #ada4a4 ;
        -moz-box-shadow:0px 4px 4px #ada4a4 ;
        box-shadow:0px 4px 4px #ada4a4 ;
        text-align: center;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
        .el-table{
            margin: 10px auto;
        }
    }
}
</style>
