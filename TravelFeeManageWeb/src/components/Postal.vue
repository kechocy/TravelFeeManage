<template>
    <div class="postalContainer">
        <div class="mainContainer">
            <h1 class="title">邮政编码在线查询</h1>
            <el-form :inline="true" :model="postalForm" size="small" class="postalForm">
                <el-form-item label="地名查询邮编">
                    <el-input v-model="postalForm.place" placeholder="请输入地名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="primary" @click="onSubmitByPlace">查询</el-button>
                </el-form-item>
                <br/>
                <el-form-item label="邮编查询地名">
                    <el-input v-model="postalForm.code" placeholder="请输入邮编"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="primary" @click="onSubmitByCode">查询</el-button>
                </el-form-item>
            </el-form>
            <template>
                <el-table
                class="table"
                border
                :data="tableData">
                <el-table-column
                    prop="resultPlace"
                    label="地名">
                </el-table-column>
                <el-table-column
                    prop="resultCode"
                    label="邮编"
                    width="100">
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
      postalForm: {
        place: '',
        code: ''
      },
      tableData: [{
        resultPlace: '',
        resultCode: ''
      }]
    }
  },
  methods: {
    onSubmitByPlace () {
      var _this = this
      this.$http.get('postal/getByPlace', {
        params: {
          place: this.postalForm.place
        }
      })
        .then(function (response) {
          var obj = JSON.parse(response.data.response)
          if (obj.success === '0') { _this.$message.error('暂无数据') } else if (obj.success === '1') {
            _this.tableData.splice(0, 1)// 删除原来数据
            _this.tableData.push({ resultPlace: obj.result.lists[0].areanm, resultCode: obj.result.lists[0].postcode })
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    onSubmitByCode () {
      var _this = this
      this.$http.get('postal/getByCode', {
        params: {
          code: this.postalForm.code
        }
      })
        .then(function (response) {
          var obj = JSON.parse(response.data.response)
          if (obj.success === '0') { _this.$message.error('暂无数据') } else if (obj.success === '1') {
            _this.tableData.splice(0, 1)// 删除原来数据
            _this.tableData.push({ resultPlace: obj.result.lists[0].areanm, resultCode: obj.result.lists[0].postcode })
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    }
  }
}
</script>
<style lang="less" scoped>
.postalContainer{
    background-image: url("../assets/img/postal.jpg");
    background-repeat: no-repeat;
    background-size: 100% 100%;
    height: calc(100% - 61px);
    width: 100%;
    position: relative;
    .mainContainer{
        height: 500px;
        width: 800px;
        text-align: center;
        background-color: rgba(245, 240, 240, 0.8);
        border-radius: 5px;
        -webkit-box-shadow:0px 4px 4px #ada4a4 ;
        -moz-box-shadow:0px 4px 4px #ada4a4 ;
        box-shadow:0px 4px 4px #ada4a4 ;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%,-50%);
        .title{
            margin: 30px 0px 50px 0px;
        }
        .table{
            transform: translate(50%,40%);
            width: 400px;
            height: 100px;
        }
    }
}
</style>
