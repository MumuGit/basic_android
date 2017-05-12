# basic_android
**意帮Android基础工程**

- [数据图表](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/fragment/FirstFragment.java) 现有基于[HelloCharts](https://github.com/lecho/hellocharts-android)，但是更推荐[MPAndroidChart](https://github.com/PhilJay/MPAndroidChart),因图表样式更多。
- [极光推送](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/App.java) [极光快速集成文档](http://docs.jiguang.cn/jpush/client/Android/android_guide/)
- [文件上传、查看文件列表、查看网络文件信息](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/activity/FileActivity.java) 针对[又拍云](http://docs.upyun.com/api/form_api/)，使用又拍云的[Java SDK](https://github.com/upyun/java-sdk),而非Andorid SDK，因Andorid SDK只具备文件上传功能
- [QQ、微信、新浪微博分享](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/fragment/SecondFragment.java) [友盟分享集成文档](http://dev.umeng.com/social/android/quick-integration#3)
- [Excel文件导出](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/activity/ExportExcelActivity.java)  添加依赖`compile 'net.sourceforge.jexcelapi:jxl:2.6.12'`，未找到github源码
- [png、jpg、gif、webp图片格式转换](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/activity/ImageTransferActivity.java) 基本原理为把图片加载为bitmap，然后以不同格式导出。如需压缩，则需根据需求确定分辨率、压缩质量两个参数。[转换工具](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/util/SaveBitmapUtil.java)已初步封装
- [高德地图定位](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/activity/LocationActivity.java) 基于[高德地图Demo](http://lbs.amap.com/api/android-sdk/download/)，[集成文档](http://lbs.amap.com/api/android-sdk/guide/create-project/android-studio-create-project)
- [高德地图路径规划](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/activity/RestRouteShowActivity.java)
- [高德地图语音导航](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/activity/RouteNaviActivity.java)
- [二维码、条形码生成](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/activity/QRCodeGenerateActivity.java) 基于开源项目[BGAQRCode](https://github.com/bingoogolapple/BGAQRCode-Android),[条形码生成](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/util/RxBarCode.java)已独立封装
- [二维码、条形码扫描](https://github.com/kamaslau/basic_android/blob/master/app/src/main/java/com/ybslux/android/activity/QRCodeScanActivity.java)
- [微信支付、支付宝支付（待上传）]()
