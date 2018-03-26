# android_debugdata_webtool
android debug database SharedPreference 查看并修改手机数据库和共享参数的工具，优化了amitshekhar工程的好多细节功能和代码

http://itgowo.com

QQ:1264957104

Email:lujianchao@itgowo.com

## 前辈版本 Github：https://github.com/amitshekhariitbhu/Android-Debug-Database
大家可以去膜拜一下前辈

因为一次使用中发现国外前辈写的服务和接口无法满足我的使用，主要是用的GET请求方式，再编码后url过于长，超出范围就会数据丢失，所以我改成了POST请求的，并且重写了Server逻辑，从Socket拿到流再解析http报文，再到业务处理，同时增加了文件管理功能，相对前辈作品来说这个bug少，功能强，有问题欢迎提问，最好放到issues里，直接问也会回答但是issues也是人气的表现，谢谢各位啦！

## 懒人模式，1.0.4版只需要一行代码就可以了，compile就会自动延时1.2秒开启，也保留了旧版手动开启，第二次开启会自动关闭第一次服务；
## 自动识别开发者使用的三方Json库并使用，同时用反射找Json工具类，一如既往不引入第三方，不管你用的是fastjson还是Gson,都能自动使用，目前只支持两个最常用的，优先使用fastjson。

## 优化与改进

#### 1.增加多线程处理请求
#### 2.手动解析http报文数据，增加HttpRequest类
#### 3.web端get请求改成post请求，满足大数据量需求
#### 4.服务端增加post处理能力，支持大数据量传输
#### 5.增加文件管理功能（接口已完成）
#### 6.更改布局和页面逻辑，数据库和共享参数分开，文件管理功能目前简单实现了目录浏览和文件下载功能
#### 7.增加跨域请求Options处理，解决跨域问题
#### 8.统一规范：返回值Response类，请求Request类，接口规范GET请求为资源请求，options请求为跨域说明，POST请求为数据交互
#### 9.POST请求统一用action表示意向操作
#### 10.本支持库为了尽量少的引用第三方，降低耦合性，由使用者定义json工具

## Server：android app(纯java和android原生代码实现http报文解析操作等）

## web：JQuery、BootStrap和DataTables框架，资源放到app内部，无需联网也可访问

# 使用方法

#### 1:可以将webToolLibrary.aar文件放到工程lib库里直接依赖，亦可以下载工程单独把lib放到项目里（ compile project(':webtoollibrary')）

#### 2.依赖

```
    buildscript {
        repositories {
            jcenter()
            *****
        }
        *****
    }
```

maven依赖
```
  <dependency>
    <groupId>com.itgowo</groupId>
    <artifactId>android-WebDebugTool</artifactId>
    <version>1.0.5</version>
    <type>pom</type>
  </dependency>
```
Gradle依赖
```
  compile 'com.itgowo:android-WebDebugTool:1.0.6'
  debugcompile 'com.itgowo:android-WebDebugTool:1.0.6'  //推荐
  
  Android Gradle plugin 3.0或更高
      debugApi 'com.itgowo:android-WebDebugTool:1.0.6'  //推荐
      api 'com.itgowo:android-WebDebugTool:1.0.6'
```
#### 3.初始化

可以在App启动后任意需要时刻初始化，不手动或手动触发较晚，都会在2秒内自动自动初始化，如果开发者自己手动启动且在application的onCreat()中，自动启动将失效。
###最简单的是，下面都不配置代码，是会自动启动服务器的。

第一个参数为context，第二个参数为端口，第三个参数为线程模式（是否是多线程），第四个为服务器状态监听。
```
public static void initialize(Context context, int mPortNumber, boolean isMultMode, onDebugToolListener mOnDebugToolListener) {
```

```
  DebugDataTool.initialize(this, 8088, false, new onDebugToolListener() {

              @Override
              public void onSystemMsg(final String mS) {
                //系统信息，主要是一些提示性信息，例如服务器地址
              }

              @Override
              public String onObjectToJson(Object mObject) {
              //对象转换为json文本，本支持库为了尽量少的因为第三方，降低耦合性，由使用者定义json工具，可以用Gson或者fastjson等，demo使用的是fastjson。
                  return JSON.toJSONString(mObject);
              }

              @Override
              public <T> T onJsonStringToObject(String mJsonString, Class<T> mClass) {
              //文本转换为Json对象，本支持库为了尽量少的因为第三方，降低耦合性，由使用者定义json工具，可以用Gson或者fastjson等，demo使用的是fastjson。
                  return JSON.parseObject(mJsonString, mClass);
              }

              @Override
              public void onGetRequest(String mRequest, final HttpRequest mHttpRequest) {
              //服务器收到请求，文件请求只打印请求path，数据接口交互HTTPRequest包含报文全部信息
              }


              @Override
              public void onResponse(final String mResponse) {
              //服务器返回给页面的信息，只有跨域请求Options和POST数据交互时才会触发，文件下载不触发。
              }

              @Override
              public void onError(final String mTip, final Throwable mThrowable) {
              //捕捉到的服务器异常
          });
        
```        


#### 4.说明

app启动后日志会打印手机网路地址（请在wifi下测试，模拟器无效），用浏览器打开对应地址就可以正常使用了，如果偶尔数据请求不到刷新页面即可，可能是某个js文件加载失败造成的，demo的屏幕打印了部分日志，方便测试。

## API

### Database 数据库

[getDbList](/API/getDbList.md)

[getTableList](/API/getTableList.md)

[getDataFromDbTable](/API/getDataFromDbTable.md)

[addDataToDb](/API/addDataToDb.md)

[deleteDataFromDb](/API/deleteDataFromDb.md)

[updateDataToDb](/API/updateDataToDb.md)

[query](/API/query.md)


### SharedPreferences 共享参数

[getSpList](/API/getSpList.md)

[getDataFromSpFile](/API/getDataFromSpFile.md)

[addDataToSp](/API/addDataToSp.md)

[deleteDataFromSp](/API/deleteDataFromSp.md)

[updateDataToSp](/API/updateDataToSp.md)


### FileManager 文件管理

[getFileList](/API/getFileList.md)

[downloadFile](/API/downloadFile.md)

[deleteFile](/API/deleteFile.md)


## 图片示例（web界面功能开发中，样式有待调整）

![ image](https://github.com/hnsugar/android-debugdata-webtool/blob/master/img1.png)

![ image](https://github.com/hnsugar/android-debugdata-webtool/blob/master/img2.png)

![ image](https://github.com/hnsugar/android-debugdata-webtool/blob/master/img3.png)

![ image](https://github.com/hnsugar/android-debugdata-webtool/blob/master/img4.png)

![ image](https://github.com/hnsugar/android-debugdata-webtool/blob/master/img5.png)

![ image](https://github.com/hnsugar/android-debugdata-webtool/blob/master/img6.png)

![ image](https://github.com/hnsugar/android-debugdata-webtool/blob/master/img7.png)


