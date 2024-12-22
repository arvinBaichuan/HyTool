# 开发、构建、调试、运行相关说明


 
## 注意事项



**本项目为了简化开发，使用了Lombok，请确认您的IDE中是否安装了该插件**

## 首先这是一个maven工程



和普通maven工程一样，
但是，**有几个jar在maven中央仓库是没有的，**
所以这几个jar需要先install到本地repo，
所在的路径：src/main/lib
需要将它们maven install到本地repository
也可以通过HyTool已经集成的maven插件自动install，
**自动install方法：** IDEA->maven->Lifecycle->clean
**手动install命令示例：**

### 安装darcula到本地仓库命令示例



```
mvn install:install-file -Dfile=E:\IdeaWorkspace\HyTool\src\main\lib\darcula.jar -DgroupId=com.darcula -DartifactId=darcula-lnf -Dpackaging=jar -Dversion=1.0 -DgeneratePom=true -DcreateChecksum=true
```



## 这还是一个基于IntelliJ GUI Designer的工程



需要且仅能在IntelliJ里面打开GUI Form文件来进行界面的拖拽设计

**!!还需要将IntelliJ设置一下!!**：
![IntelliJ GUI Designer设置] 选择“**Java source code**”

## 调试运行



唯一入口类：
com.baichuan.hy.tool.App

## 如何build



maven build即可

## 如何打包



然后maven package打包即可

## 如何将打包后的文件进一步打包成exe



- 使用Launch4j将mvn package后的主入口jar：HyTool-1.0.jar打包成HyTool.exe;
  具体方法请参考Launch4j官网Doc：http://launch4j.sourceforge.net/docs.html
- 使用SetupFactory将package后的/lib连同上面的HyTool.exe打包成安装文件