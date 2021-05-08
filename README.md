

# TravelFeeManage

使用spring boot开发的简单旅游开支管理接口，使用vue开发前端页面

`TravelFeeManageWeb`目录下存放前端代码，`TravelFeeManageWs`目录下存放旅行开支管理后端接口代码

此项目来源于学校《服务计算概论》课程的作业，要求面向服务开发一款旅游开支管理接口，项目内容较为简单，主要是为了初学spring boot时能够理清项目结构

问题描述：
> 开发一个提供旅行规划的应用软件，其中要用到自己开发的一个旅行开支管理的Web服务和一个由第三方公司提供天气预报的服务


## 配置数据库
数据库的相关信息在`src\main\resources\application.yml`文件下，可对应修改对应数据库名、用户名、密码等信息

```yml
  datasource:
    url: jdbc:mysql://localhost:3306/travelFeeManage?characterEncoding=utf-8
    username: root
    password: 123456
```

### 数据库中的表

1. `user`表用于存放用户信息

| 字段 | 类型 | 描述 |
| :-: | :-: | :-: |
| id | int | 用户id（主键,自增） |
| username | varchar | 用户名（不为空，小于10个字符） |
| password | varchar | 密码（不为空，6<字符数<20） |

2. `budget`表用于存放预算信息

| 字段 | 类型 | 描述 |
| :-: | :-: | :-: |
| id | int | 预算id（主键,自增） |
| traffic | float | 交通预算（不为空） |
| food | float | 餐饮预算（不为空） |
| accommodation | float | 住宿预算（不为空） |
| play | float | 游玩预算（不为空） |
| shopping | float | 购物预算（不为空） |
| other | float | 其它预算（不为空） |
| total | float | 总预算（不为空） |
| userid | int | 对应用户的预算（不为空,外键） |

3. `spend`表用于存放花费信息

| 字段 | 类型 | 描述 |
| :-: | :-: | :-: |
| id | int | 消费id（主键,自增） |
| matter | varchar | 消费事项（不为空） |
| spend | float | 消费金额（不为空） |
| budgetid | int | 对应预算id（不为空,外键） |

## 附加

在原来界面基础上增加了几个界面，包括对邮编、火车时刻表、手机归属地的查询，调用接口源自网络，可能失效