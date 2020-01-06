# Locker Robot
```
Given 有空箱

When 按存包按钮

Then 给小票
```
```
Given 

When 按取包按钮，输入合法小票

Then 释放箱子
```
```
Given 

When 按取包按钮，输入非法小票

Then 抛异常
```

```
Given 满柜

When 按存包按钮

Then 抛异常
```



# Primary Locker Robot
```
Given robot, 6 lockers, 1,2 are full and 3rd locker has empty box.

When 委托机器人存包

Then return ticket with ID of 3rd locker.
```

```
Given robot, 6 lockers, ALL full 

When 委托机器人存包

Then Throw NoEmptyBoxException.
```

```
Given robot, 6 lockers, a valid ticket of locker 3

When 委托机器人取包

Then return "Successful" and box released.
```

```
Given robot, 6 lockers, an invalid ticket of locker 3

When 委托机器人取包

Then return "Failed".
```