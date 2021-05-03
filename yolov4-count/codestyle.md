# 后端python
[来源：Python 编码规范(Google)](https://www.runoob.com/w3cnote/google-python-styleguide.html)

## 分号
不要在行尾加分号, 也不要用分号将两条命令放在同一行。

## 行长度
每行不超过80个字符

以下情况除外：

1. 长的导入模块语句
2. 注释里的URL

不要使用反斜杠连接行。

Python会将**圆括号,中括号和花括号中的行隐式的连接起来**, 你可以利用这个特点。如果需要，你可以在表达式外围增加一对额外的圆括号。
> ```python
> foo_bar(self, width, height, heightcolor='black', design=None,
>         x='foo', emphasis=None, highlight=0)
>
> if (width == 0 and height == 0 and 
>     color == 'red' and emphasis == 'strong'):
> ```

如果一个文本字符串在一行放不下，可以使用圆括号来实现隐式行连接：
> ```python
> x = ('这是一个非常长非常长非常长非常长 '
>     '非常长非常长非常长非常长非常长非常长的字符串')
> ```

在注释中，如果必要，将长的URL放在一行上。
> ```python
> Yes:
>     # See details at
>     # http://www.example.com/us/developer/documentation/api/content/v2.0/csv_file_name_extension_full_specification.html
> ```

> ```python
> No:
>     # See details at
>     # http://www.example.com/us/developer/documentation/api/content/\
>     # v2.0/csv_file_name_extension_full_specification.html
> ```

## 括号
宁缺毋滥的使用括号

除非是用于实现行连接，否则不要在返回语句或条件语句中使用括号。不过在元组两边使用括号是可以的。

> ```python
> Yes:
>     if x and y:
>         return foo
> ```

> ```python
> No:
>     if (x) and (y):
>        return (foo)
> ```

## 缩进
用4个空格来缩进代码

绝对不要用tab，也不要tab和空格混用。对于行连接的情况, 你应该要么垂直对齐换行的元素，或者使用4空格的悬挂式缩进(这时第一行不应该有参数)：

> ```python
> Yes:
>     # 与起始变量对齐
>     foo = long_function_name(var_one, var_two,
>                              var_three, var_four)
>
>     # 字典中与起始值对齐
>     foo = {
>         long_dictionary_key: value1 +
>                              value2,
>         ...
>     }
>
>     # 4 个空格缩进，第一行不需要
>     foo = long_function_name(
>         var_one, var_two, var_three,
>         var_four)
>
>     # 字典中 4 个空格缩进
>     foo = {
>         long_dictionary_key:
>             long_dictionary_value,
>         ...
>     }
> ```

> ```python
> No:
>     # 第一行有空格是禁止的
>     foo = long_function_name(var_one, var_two,
>         var_three, var_four)
>
>     # 2 个空格是禁止的
>     foo = long_function_name(
>       var_one, var_two, var_three,
>       var_four)
>
>     # 字典中没有处理缩进
>     foo = {
>         long_dictionary_key:
>             long_dictionary_value,
>             key2:
>             ...
>     }
> ```

## 空格
按照标准的排版规范来使用标点两边的空格

括号内不要有空格
> ```python
> spam(ham[1], {eggs: 2}, [])
> ```

不要在逗号、分号、冒号前面加空格，但应该在它们后面加（除了在行尾）
> ```python
> if x == 4:
>     print x, y
> ```

参数列表，索引或切片的左括号前不应加空格
> ```python
> dict['key'] = list[index]
> ```

在二元操作符两边都加上一个空格，比如赋值`=`，比较`==, <, >, !=, <>, <=, >=, in, not in, is, is not`，布尔`and, or, not`
> ```python
> x == 1
> ```

当`=`用于指示**关键字参数**或**默认参数值**时，不要在其两侧使用空格
> ```python
> def complex(real, imag=0.0):
>     return magic(r=real, i=imag)
> ```

不要用空格来垂直对齐多行间的标记，因为这会成为维护的负担（适用于：`,`、`#`、`=`等）
> ```python
> No:
>     foo       = 1000  # 注释
>     long_name = 2     # 注释不需要对齐
>
>     dictionary = {
>         "foo"      : 1,
>         "long_name": 2,
>         }
> ```

## 字符串
避免在循环中用`+`和`+=`操作符来累加字符串。由于字符串是不可变的，这样做会创建不必要的临时对象，并且导致二次方而不是线性的运行时间。作为替代方案，你可以使用`append()`将每个子串加入列表，然后在循环结束后用`''.join()`连接列表。
> ```python
> items = ['<table>']
> for last_name, first_name in employee_list:
>     items.append('<tr><td>%s, %s</td></tr>' % (last_name, first_name))
> items.append('</table>')
> employee_table = ''.join(items)
> ```

在同一个文件中，保持使用字符串引号的一致性。使用单引号`'`或者双引号`"`之一用以引用字符串，并**在同一文件中沿用**。在字符串内可以使用另外一种引号。
> ```python
> Python('Why are you hiding your eyes?')
> Narrator('"Good!" thought a happy Python reviewer.')

## 导入格式
每个导入应该独占一行。

导入总应该放在文件顶部，位于模块注释和文档字符串之后，模块全局变量和常量之前。导入应该按照从最通用到最不通用的顺序分组：
1. 标准库导入
2. 第三方库导入
3. 应用程序指定导入

每种分组中，应该根据每个模块的完整包路径按字典序排序，忽略大小写。
> ```python
> import foo
> from foo import bar
> from foo.bar import baz
> from foo.bar import Quux
> from Foob import ar
> ```

## 命名
- `module_name`
- `package_name`
- `ClassName`
- `method_name`
- `ExceptionName`
- `function_name`
- `GLOBAL_VAR_NAME`
- `instance_var_name`
- `function_parameter_name`
- `local_var_name`

Python之父Guido推荐的规范

Type|Public|Internal
|----|-----|-----|
Modules|lower_with_under|_lower_with_under
Packages|lower_with_under|
Classes|CapWords|_CapWords
Exceptions|CapWords|	
Functions|lower_with_under()|_lower_with_under()
Global/Class Constants|CAPS_WITH_UNDER|_CAPS_WITH_UNDER
Global/Class Variables|lower_with_under|_lower_with_under
Instance Variables|lower_with_under|_lower_with_under (protected) or __lower_with_under (private)
Method Names|lower_with_under()|_lower_with_under() (protected) or __lower_with_under() (private)
Function/Method Parameters|lower_with_under|
Local Variables|lower_with_under|	

## Main
即使是一个打算被用作脚本的文件，也应该是可导入的。并且简单的导入不应该导致这个脚本的主功能（main functionality）被执行，这是一种副作用。主功能应该放在一个main()函数中。

在Python中，pydoc以及单元测试要求模块必须是可导入的。你的代码应该在执行主程序前总是检查`if __name__ == '__main__'`，这样当模块被导入时主程序就不会被执行。
> ```python
> def main():
>     ...
>
> if __name__ == '__main__':
>     main()