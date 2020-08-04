* [1. git branch 创建分支](#1-git-branch-创建分支)
* [2. git merge合并分支](#2-git-merge合并分支)
* [3. git rebase 合并分支](#3-git-rebase-合并分支)
* [4. 回退历史版本](#4-回退历史版本)
* [5. 撤回变更](#5-撤回变更)
* [6. 整理修改记录](#6-整理修改记录)

### 1. git branch 创建分支

```shell
$ git branch bugFix;
$ git checkout bugFix; # 切换到分支
```

删除分支

```shell
git branch -d feature1
```





### 2. git merge合并分支

```shell
$ git branch bugFix
$ git checkout bugFix
$ git commit
$ git checkout master
$ git commit
$ git merge bugFix
```

![1574300223131](F:\工作\记录\06.代码管理工具\01.Git\Git--常用指令.assets\1574300223131.png)
<<<<<<< HEAD

<img  src="F:/工作2/记录/码云/images/1574300223131.png"/>
=======

>>>>>>> master

### 3. git rebase 合并分支

与merge有所不同。

```shell
$ git branch bugFix
$ git checkout bugFix
$ git commit
$ git checkout master
$ git commit
$ git checkout bugFix
$ git rebase master
```

![1574299918790](F:\工作\记录\06.代码管理工具\01.Git\Git--常用指令.assets\1574299918790.png)

2. 多次分支

   git rebase master bugFix # 将bugFix分支合并到master分支上

   ```shell
   $ git rebase bugFix side
   $ git rebase side another
   $ objective
   $ show solution
   $ reset --forSolution
   $ git rebase master bugFix
   $ git rebase bugFix side
   $ git rebase side another
   $ git rebase another master
   ```

   ![1576380929056](F:\工作\记录\码云\images\1576380929056.png)

### 4. 回退历史版本

```shell
$ git checkout c4 #c4 提交的名字
$ git checkout HEAD^
$ git checkout HEAD~4
```

it branch -f master HEAD~3



### 5. 撤回变更

有两种，一种适用于本地的撤回变更，一种是用于远程提交的时候。

```shell
$ git reset HEAD~1 # 用于本地变更
```

![1576377487427](F:\工作\记录\06.代码管理工具\01.Git\Git--常用指令.assets\1576377487427.png)



```shell
$ git revert HEAD # 用于远程变更
```



![1576377565791](F:\工作\记录\06.代码管理工具\01.Git\Git--常用指令.assets\1576377565791.png)

### 6. 整理修改记录

1. `git cherry-pick C2 C4`

   将C2和C4上的提交记录转移到master分支上。

   ![1576377840273](F:\工作\记录\06.代码管理工具\01.Git\Git--常用指令.assets\1576377840273.png)

2. 交互式rebase 指的是使用带参数 `--interactive` 的 rebase 命令 简写为 `-i`

   如果你在命令后增加了这个选项, Git 会打开一个 UI 界面并列出将要被复制到目标分支的备选提交记录，它还会显示每个提交记录的哈希值和提交说明，提交说明有助于你理解这个提交进行了哪些更改。

   ```shell
   使用场景：
   
   当你知道你所需要的提交记录（**并且**还知道这些提交记录的哈希值）时, 用 cherry-pick 再好不过了 —— 没有比这更简单的方式了。
   
   但是如果你不清楚你想要的提交记录的哈希值呢? 幸好 Git 帮你想到了这一点, 我们可以利用交互式的 rebase —— 如果你想从一系列的提交记录中找到想要的记录, 这就是最好的方法了
   ```

git commit --amend 修改

git tag v1 c1 打标签 后可以通过标签移动HEAD



