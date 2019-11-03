## 统一返回结构
```json
{
    "status":"200",
    "message":"123",
    "data":"响应数据"
}
```

## 报修接口
> 提交后群发邮件

##### 请求方式： POST
##### URL：  /repair

> repairRegion: 校区 (0 - 老校区 1-新校区)
>
> repairDormitory: 宿舍
>
> repairContact: 联系方式（ QQ || 微信 || 电话号码）
>
> repairDescription: 问题简单描述
##### 请求参数示例
```json
{
    "repairRegion": "0",
    "repairDormitory": "2#123",
    "repairContact": "电话18379790727",
    "repairDescription": "问题简单描述"
}
```
##### 响应示例
> repairId: 单号
>
> repairNumber: 阅读量
>
> repairDate: 下单时间

```json
{
    "status": 200,
    "message": "提交成功",
    "data": {
        "repairId": 36,
        "repairRegion": 0,
        "repairDormitory": "2#123",
        "repairContact": "电话：18379790727",
        "repairNumber": 0,
        "repairDescription": "111",
        "repairDate": "2019-11-03 05:57:41"
    }
}
```

## 查询进度订单
> 返回查阅人数

##### 请求方式： GET
##### URL：  /repair/{单号}
##### 响应示例

```json
{
    "status": 200,
    "message": "查询成功",
    "data": {
        "repairId": 31,
        "repairRegion": 0,
        "repairDormitory": "2#123",
        "repairContact": "电话：18379790727",
        "repairNumber": 1,
        "repairDescription": "111",
        "repairDate": "2019-11-02 11:23:08"
    }
}
```

## 查询所有单子
##### 请求方式： GET
##### URL：  /repairs
##### 响应示例
```json
{
    "status": 200,
    "message": "查询成功",
    "data": [
        {
            "repairId": 1,
            "repairRegion": 0,
            "repairDormitory": "2#404",
            "repairContact": "18379790727",
            "repairNumber": 1,
            "repairDescription": "重装系统",
            "repairDate": "2019-10-25 12:22:47"
        },
        {
            "repairId": 36,
            "repairRegion": 0,
            "repairDormitory": "2#123",
            "repairContact": "电话：18379790727",
            "repairNumber": 0,
            "repairDescription": "111",
            "repairDate": "2019-11-03 05:57:41"
        }
    ]
}
```

## 查看所有接收邮箱
##### 请求方式： GET
##### URL：  /mails
##### 响应示例
```json
{
    "status": 200,
    "message": "查询成功",
    "data": [
        {
            "mailId": 1,
            "mailName": "name",
            "mailMail": "cqiang102@foxmail.com",
            "mailDate": "2019-10-25 12:27:48"
        },
        {
            "mailId": 2,
            "mailName": "name2",
            "mailMail": "cqiang102@foxmail.com",
            "mailDate": "2019-10-25 12:27:48"
        }
    ]
}
```
## 查看接收邮箱 ，根据邮箱
##### 请求方式： GET
##### URL：  /mail/{邮箱}
##### 响应示例
```json
{
    "status": 200,
    "message": "查询成功",
    "data": {
        "mailId": 1,
        "mailName": "name",
        "mailMail": "cqiang102@foxmail.com",
        "mailDate": "2019-10-25 12:27:48"
    }
}
```
## 删除接收邮箱
##### 请求方式： DELETE
##### URL：  /mail/{邮箱}
##### 响应示例
> data: 删除数量

```json
{
    "status": 200,
    "message": "删除成功",
    "data": 1
}
```

## 添加接收邮箱
##### 请求方式： POST
##### URL：  /mail
##### 请求参数示例
```json
{
	"mailName":"tets",
	"mailMail":"test"
}
```
##### 响应示例
```json
{
    "status": 200,
    "message": "提交成功",
    "data": {
        "mailId": 4,
        "mailName": "tets",
        "mailMail": "test",
        "mailDate": "2019-11-03 06:09:11"
    }
}
```
