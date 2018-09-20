## ZYMK

一款采用MVP-contract的仿《知音漫客》漫画APP。

mvp+rxjava+retrofit+okhttp+greendao+glide+gson+jsoup+eventbus+butterknife

博客：[https://www.jianshu.com/u/1dc736dffcd3](https://www.jianshu.com/u/1dc736dffcd3)

个人页：[http://wzmyyj.top/2018/08/22/android_3/](http://wzmyyj.top/2018/08/22/android_3/)

APk百度云下载：

链接：[https://pan.baidu.com/s/1Gp5vcoUx7NEr8n17nM8wwg](https://pan.baidu.com/s/1Gp5vcoUx7NEr8n17nM8wwg) 密码：b6zq

#### 最新更新（2018.06.19）
1. 由常规MVP改为Google推荐的MVP-Contract模式。将原本的presenter层每个类抽象出接口，并将这些接口和对应的view的接口，放在同一个contract接口里（接口里放两个接口）。
- 抽象出p层接口使v层依赖于p层接口（父类）。依赖倒转原则。
- 使用contract好处是p层与v层相互调用关系在一个文件里一目了然。

2. 修改了一些命名规范，类名，方法名等。

3. 修改了一些类实现的方法，修复一个小bug。



#### 效果图：



![a](https://upload-images.jianshu.io/upload_images/3262738-458c938a01e30e0a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)



![b](https://upload-images.jianshu.io/upload_images/3262738-8e5cdbfd8b1002b9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)



![p](https://upload-images.jianshu.io/upload_images/3262738-01656f1ed477c2e3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)



![b](https://upload-images.jianshu.io/upload_images/3262738-3666b561548ab60b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)



![d](https://upload-images.jianshu.io/upload_images/3262738-46e77fe88ce2d05c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)



![e](https://upload-images.jianshu.io/upload_images/3262738-59ef61e1c92014d8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)



![f](https://upload-images.jianshu.io/upload_images/3262738-8d6230878da72de7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)



![g](https://upload-images.jianshu.io/upload_images/3262738-2c6fea325e8b6f33.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)



![s](https://upload-images.jianshu.io/upload_images/3262738-da02b8b7a7dc43ad.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/340)



#### 项目地址：

[https://github.com/wzmyyj/ZYMK](https://github.com/wzmyyj/ZYMK)


#### 免责声明：

本程序所有资源全部来源于网络爬虫,且规避了付费的漫画，所有的漫画在网络可以免费阅读的。本程序仅供学习参考使用，严禁任何商业用途。欢迎各位进行技术讨论和交流。如果侵犯到任何人的利益，可以联系作者QQ：2018987032。进行协商，进行漫画的下架。

