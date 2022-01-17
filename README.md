# MyGarbageClassify
学程序几年了，第一次用github。
不知道README写啥，简单说明一下就好。
也不清楚啥啥啥GLP GNU之类的开源协议，考完试再好好了解一下。反正我这个屑的代码也没啥价值，全部开源算了。用，都可以用。
也别说是我写的，我太菜了，说出来丢人......
# java文件夹下的类文件说明(按字母顺序)
## AboutItemAdapter
[我的]页面下有ListView,这个类用来做它的适配器的
## AboutListItem
[我的]页面下Listview的每一项,包含一个图标和文字
## ActivityCollector
有一个集合,将每个运行的activity放进来,便于一次性结束所有activity.网络状态广播调控强制退出登录的时候用
## BaseActivity
activity的基类,继承自AppCompatActivity. 其他活动类都继承自这个类,用于强制下线的演示
## ContactMe
联系页面.这里进行了写剪切板的操作,复制相关信息.以及通过Intent跳转到拨号界面
## DownloadImage
下载图片的服务.用于演示后台服务.第44行是下载资源链接,可以自行更改
## Exam
进入做题测验的页面,有一个ListView选择题目
## ExamPage
正式做题页面. 劫持返回键,不许返回,除非直接退出回到桌面. 题库也在这里
## ExamResult
显示做题结果,并给出评价语
## FirstLead
首次进入app时进入一个引导页面.很多app都会用这种方式, 比如Telegram
## Login
登录略
## Main
主页面,一个viewpager和底部导航栏
## NavigationAdapter
主页面底部导航栏的适配器
## NetworkReceiver
网络状态改变的广播接收器
## NetworkUtil
检测网络是否有连接,连上wifi,数据,蓝牙都算有连接
## ProblemFeedback
反馈问题.其实是读取手机通讯录,并将反馈内容以短信的形式发给通讯录中任意一个人(纯属恶趣味,别真发啊)
## Register
注册,将信息写入数据库
## Search
搜索垃圾界面.通过爬网页的方式返回垃圾类型.如果有返回,那么结果一定在那个页面的第400行.
当然以后这个网页可能有更新,就不一定还在400行,这个方法就得重新实现了.
得到结果以后,以通知的形式告诉用户.如果没有结果,通知就可以点击,跳转到网页搜索
## Splash
略
## UserProtocal
用户协议,声明一下这个app是姐做的,没有作弊.最底下有张西行寺幽幽子使剑的图,可以下载原图.后台服务功能的演示
## WebSearch
如果数据库搜不到是什么垃圾,就网页搜索
# 其他几个文件夹说明
## database
存放账户信息和搜索历史的两个数据库
## instruction_fragment
主页面底部导航中间那个.垃圾分类指南(划掉)指北的几个碎片,和翻页的适配器
## lead_fragment
首次进入app时的引导页面显示的几页内容
## mainFragment
主页面底部导航栏控制的三个页面的碎片
