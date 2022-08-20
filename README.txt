程序依赖于jsoup.jar包
兄弟们点点star

使用步骤：
1. 在user.txt中写入账户密码（支持多人打卡，但不能写有多余空行）
2. 利用抓包工具例如fiddler，抓取打卡时所提交的json数据，复制到message.txt
3. 然后就可以运行程序了

注意：
如果(打卡页面发生改变||或者打卡地点改变)，需要重新抓包，更新message.txt
JDK版本过高可能会出现错误，建议使用JDK1.8

小建议：
您可以把程序打包成可执行程序，一键运行，或者部署到服务器，定时为你打卡


如何抓取i南航打卡报文？
http://t.csdn.cn/GY3lH
如何将Java程序打包成exe可执行程序？
1. 打包成jar包（记得连带依赖包jsoup）
http://t.csdn.cn/s6UvI
2. 打包成exe
https://blog.csdn.net/m0_37701381/article/details/104163877
如何将jar包部署到Linux服务器，定时执行？
http://t.csdn.cn/QOvSI
