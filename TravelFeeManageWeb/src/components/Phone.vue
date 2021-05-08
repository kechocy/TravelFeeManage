<template>
    <div class="phoneContainer">
        <div class="mainContainer">
            <h1 class="title">手机归属地在线查询</h1>
            <el-form :inline="true" :rules="rules" :model="phoneForm" ref="phoneFormRef" size="small" class="phoneForm">
                <el-form-item label="地名查询邮编" prop="phone">
                    <el-input v-model.number="phoneForm.phone" placeholder="请输入手机号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button size="small" type="primary" @click="onSubmit">查询</el-button>
                </el-form-item>
            </el-form>
            <template>
                <el-table
                class="table"
                border
                :data="tableData">
                <el-table-column
                    prop="province"
                    label="省份">
                </el-table-column>
                <el-table-column
                    prop="city"
                    label="城市"
                    width="130">
                </el-table-column>
                <el-table-column
                    prop="sp"
                    label="服务商"
                    width="130">
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
      phoneForm: {
        phone: ''
      },
      rules: {
        phone: [
          { type: 'number', message: '电话号码必须为数字' }
        ]
      },
      tableData: [{
        province: '',
        city: '',
        sp: ''
      }]
    }
  },
  methods: {
    onSubmit () {
      this.$refs.phoneFormRef.validate((valid) => {
        if (valid) {
          var _this = this
          this.$http.get('phone/getPlace', {
            params: {
              number: this.phoneForm.phone
            }
          })
            .then(function (response) {
              var obj = JSON.parse(response.data.response)
              if (obj.code === 0) {
                _this.tableData.splice(0, 1)// 删除原来数据
                _this.tableData.push({ province: obj.data.province, city: obj.data.city, sp: obj.data.sp })
              }
            })
            .catch(function (error) {
              console.log(error)
            })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>
<style lang="less" scoped>
.phoneContainer{
    background-image: url("../assets/img/phone.jpg");
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
