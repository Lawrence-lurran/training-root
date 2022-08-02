# training-root
# 使用规范
* 源码库规范

	1、不要提交源码编译后的二进制成果物到'github'！

  2、`.bak` `Thumbs.db` `output` `.idea` `.svn`等不要上传`github`！
    
	3、nodejs的引用包，如`node_moudle`目录下的包不要上传`svn`或`gitlab`！
	
* [日志提交规范]

    `格式`
    ```sh
    [<type>]: <subject>
    ```
    `type 类别`
    ```sh
    add：增加新功能（feature）
    fix：修补bug
    docs：文档（documentation）
    style：格式（不影响代码运行的改动）
    refactor：重构（即不是新增功能，也不是修改bug的代码变动）
    test：增加测试
    chore：构建过程或辅助工具的变动
    ```
    `样例`
    ```sh
    [add]: 增加获取样本填报id功能
    ```
