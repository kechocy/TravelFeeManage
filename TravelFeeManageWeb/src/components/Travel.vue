<template>
    <div class="travelContainer">
        <div class="inputBudget">
          <div class="budget">
            <el-form :model="budgetForm" :rules="rules" ref="budgetFormRef" label-width="100px" class="budgetForm">
              <el-form-item label="用户id" prop="userid">
                <el-input v-model="budgetForm.userid" size="small" :disabled="true"> <template #prefix><i class="el-input__icon el-icon-user"></i></template>></el-input>
              </el-form-item>
              <el-form-item label="交通预算" prop="traffic">
                <el-input v-model.number="budgetForm.traffic" size="small" clearable :disabled="isDisabled"> <template #prefix><i class="iconfont icon-huobi"></i></template>></el-input>
              </el-form-item>
              <el-form-item label="饮食预算" prop="food">
                <el-input v-model.number="budgetForm.food" size="small" clearable :disabled="isDisabled"> <template #prefix><i class="iconfont icon-huobi"></i></template>></el-input>
              </el-form-item>
              <el-form-item label="住宿预算" prop="accommodation">
                <el-input v-model.number="budgetForm.accommodation" size="small" clearable :disabled="isDisabled"> <template #prefix><i class="iconfont icon-huobi"></i></template>></el-input>
              </el-form-item>
              <el-form-item label="游玩预算" prop="play">
                <el-input v-model.number="budgetForm.play" size="small" clearable :disabled="isDisabled"> <template #prefix><i class="iconfont icon-huobi"></i></template>></el-input>
              </el-form-item>
              <el-form-item label="购物预算" prop="shopping">
                <el-input v-model.number="budgetForm.shopping" size="small" clearable :disabled="isDisabled"> <template #prefix><i class="iconfont icon-huobi"></i></template>></el-input>
              </el-form-item>
              <el-form-item label="其它预算" prop="other">
                <el-input v-model.number="budgetForm.other" size="small" clearable :disabled="isDisabled"> <template #prefix><i class="iconfont icon-huobi"></i></template>></el-input>
              </el-form-item>
              <el-form-item label="总预算">
                <el-input size="small" :disabled="true"> <template #prefix><i class="iconfont icon-huobi"></i>&nbsp;{{ total }}</template>></el-input>
              </el-form-item>
            </el-form>
            <div class="btns">
              <el-button type="info" @click="resetBudgetForm">重置</el-button>
              <el-button type="primary" @click="addBudget">新增</el-button>
            </div>
          </div>
        </div>
        <div class="main">
          <h1>旅游推荐线路</h1>
          <h2>{{ suggestion }}</h2>
          <h3>{{ title1 }}</h3>
          <p>&nbsp;&nbsp;{{ des1 }}</p>
          <h3>{{ title2 }}</h3>
          <p>&nbsp;&nbsp;{{ des2 }}</p>
          <h3>{{ title3 }}</h3>
          <p>&nbsp;&nbsp;{{ des3 }}</p>
          <div class="weather"><div id="he-plugin-standard"></div></div>
        </div>
    </div>
</template>

<script>
export default {
  data () {
    return {
      isDisabled: false,
      suggestion: '',
      title1: '',
      title2: '',
      title3: '',
      des1: '',
      des2: '',
      des3: '',
      budgetForm: {
        userid: '',
        traffic: 0,
        food: 0,
        accommodation: 0,
        play: 0,
        shopping: 0,
        other: 0
      },
      CONFIG: {
        layout: '1',
        width: 450,
        height: 150,
        background: '5',
        dataColor: '000000',
        borderRadius: '5',
        key: '18aa00ec53944df3b84b3443f5f9cc0f'
      },
      rules: {
        traffic: [
          { required: true, message: '金额不能为空' },
          { type: 'number', message: '金额必须为数字' }
        ],
        food: [
          { required: true, message: '金额不能为空' },
          { type: 'number', message: '金额必须为数字' }
        ],
        accommodation: [
          { required: true, message: '金额不能为空' },
          { type: 'number', message: '金额必须为数字' }
        ],
        play: [
          { required: true, message: '金额不能为空' },
          { type: 'number', message: '金额必须为数字' }
        ],
        shopping: [
          { required: true, message: '金额不能为空' },
          { type: 'number', message: '金额必须为数字' }
        ],
        other: [
          { required: true, message: '金额不能为空' },
          { type: 'number', message: '金额必须为数字' }
        ]
      }
    }
  },
  created () {
    this.getWeather()
  },
  computed: {
    total: function () {
      return (this.budgetForm.traffic + this.budgetForm.food + this.budgetForm.accommodation + this.budgetForm.play + this.budgetForm.shopping + this.budgetForm.other)
    }
  },
  mounted () {
    this.initUserid()
  },
  methods: {
    getWeather () {
      window.WIDGET = {
        CONFIG: this.CONFIG
      };
      (function (d) {
        var c = d.createElement('link')
        c.rel = 'stylesheet'
        c.href = 'https://widget.heweather.net/standard/static/css/he-standard.css?v=1.4.0'
        var s = d.createElement('script')
        s.src = 'https://widget.heweather.net/standard/static/js/he-standard.js?v=1.4.0'
        var sn = d.getElementsByTagName('script')[0]
        sn.parentNode.insertBefore(c, sn)
        sn.parentNode.insertBefore(s, sn)
      })(document)
    },
    initUserid () {
      this.budgetForm.userid = window.sessionStorage.getItem('userid')
    },
    resetBudgetForm () {
      this.$refs.budgetFormRef.resetFields()
      this.budgetForm.userid = window.sessionStorage.getItem('userid')
      this.isDisabled = false
    },
    addBudget () {
      this.$refs.budgetFormRef.validate(async valid => {
        if (!valid) return
        if (this.total === 0) { return this.$message.error('总预算不得为0') } else {
          const { data: res } = await this.$http.post('trip/add/budget', this.budgetForm)
          if (res.meta.status === 500) { return this.$message.error('数据库错误') } else if (res.meta.status === 200) {
            this.$message.success('添加成功')
            this.isDisabled = true
            // const { data: startCityWeather } = await this.$http.get('https://devapi.qweather.com/v7/weather/3d?key=' + this.weatherKey + '&location=' + this.startCityCode)
            // console.log(startCityWeather)
            // this.startCityWeather = startCityWeather.data.fxLink
            if (this.total > 0 && this.total <= 500) {
              this.suggestion = '镇江---南京'
              this.title1 = '1.总统府'
              this.des1 = '总统府是国民政府中央所在地，也见证着太平天国与两江总督衙门的兴衰'
              this.title2 = '2.侵华日军南京大屠杀遇难同胞纪念馆'
              this.des2 = '这里记录了南京一段屈辱的历史——南京大屠杀，每个中国人都不该忘记'
              this.title3 = '3.夫子庙-秦淮风光带'
              this.des3 = '庙市和夜景合二为一，这里始终是南京最繁华的地方之一，美称"十里珠帘"'
              this.CONFIG.city = 'CN101190101'
              this.getWeather()
            } else if (this.total < 1000) {
              this.suggestion = '镇江---苏州'
              this.title1 = '1.平江路'
              this.des1 = '这里有各式特色小店，手摇船也是一大特色'
              this.title2 = '2.苏州博物馆'
              this.des2 = '建筑大师贝聿铭设计建成的博物馆'
              this.title3 = '3.周庄古镇'
              this.des3 = '真实意义上的“小桥流水人家”'
              this.CONFIG.city = 'CN101190401'
              this.getWeather()
            } else if (this.total < 2000) {
              this.suggestion = '镇江---杭州'
              this.title1 = '1.西溪国家湿地公园'
              this.des1 = '坐船漫游芦苇荡、寻迹《非诚勿扰》取景处'
              this.title2 = '2.杭州宋城'
              this.des2 = '大型歌舞演出《宋城千古情》是其一绝'
              this.title3 = '3.南浔古镇'
              this.des3 = '己有750多年历史，名胜古迹众多，文化底蕴深厚，是观光游览的最佳胜地'
              this.CONFIG.city = 'CN101210101'
              this.getWeather()
            } else if (this.total < 3000) {
              this.suggestion = '镇江---上海'
              this.title1 = '1.外滩'
              this.des1 = '外滩是到上海旅游的首站，外滩建筑群是上海的名片，夜景不可错过'
              this.title2 = '2.田子坊'
              this.des2 = '上海滩最有味道的弄堂，时尚地标，是文艺小年轻的首选地'
              this.title3 = '3.南京路步行街'
              this.des3 = '大上海繁华的商业街，名品汇聚的购物街区，这里的夜晚五光十色'
              this.CONFIG.city = 'CN101020100'
              this.getWeather()
            } else if (this.total < 5000) {
              this.suggestion = '镇江---西安'
              this.title1 = '1.西安城墙'
              this.des1 = '在六百多年历史的古城墙上骑车漫步，俯瞰十三朝古都'
              this.title2 = '2.陕西历史博物馆'
              this.des2 = '被誉为“华夏珍宝库”和“中华文明的瑰丽殿堂” ，浓缩了中华民族的历史精华'
              this.title3 = '3.华清宫'
              this.des3 = '中国最古老的皇家园林之一，据说曾经是是杨贵妃沐浴的地方'
              this.CONFIG.city = 'CN101110101'
              this.getWeather()
            } else {
              this.suggestion = '镇江---北京'
              this.title1 = '1.故宫'
              this.des1 = '中国乃至世界上保存最完整，规模最大的木质结构古建筑群，在这里感受中华文明的历代传承'
              this.title2 = '2.八达岭长城'
              this.des2 = '史称天下九塞之一，是万里长城的精华，在明长城中，独具代表性'
              this.title3 = '3.天坛'
              this.des3 = '明清两代皇帝祭祀天地之神和祈祷五谷丰收的地方'
              this.CONFIG.city = 'CN101010100'
              this.getWeather()
            }
          }
        }
      })
    }
  }
}
</script>
<style lang='less' scoped>
.travelContainer{
  background-image: url("../assets/img/background.jpg");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  height: calc(100% - 61px);
  width: 100%;
  .inputBudget{
    background-color: rgba(245, 240, 240, 0.8);
    float: left;
    width: 400px;
    height: 560px;
    margin: 60px 60px 0px 80px;
    position: relative;
    border-radius: 4px;
    -webkit-box-shadow:0px 3px 3px #ada4a4 ;
    -moz-box-shadow:0px 3px 3px #ada4a4 ;
    box-shadow:0px 3px 3px #ada4a4 ;
    .budgetForm{
      .el-form-item{
        margin-bottom: 14px;
      }
      width: 250px;
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-55%,-55%);
    }
    .btns{
      display: flex;
      justify-content: center;
      transform: translate(0%,480px);
      .el-button{
        margin: 0px 20px;
        width: 100px;
      }
    }
  }
  .main{
    float: right;
    background-color: rgba(235, 231, 231, 0.8);
    width: 780px;
    height: 540px;
    padding: 10px 30px;
    margin: 60px 80px 0px 0px;
    border-radius: 4px;
    -webkit-box-shadow:0px 3px 3px #ada4a4 ;
    -moz-box-shadow:0px 3px 3px #ada4a4 ;
    box-shadow:0px 3px 3px #ada4a4 ;
  }
  .weather{
    position: absolute;
    width: 450px;
    float: right;
    top: 150px;
    right: 160px;
  }
}

</style>
