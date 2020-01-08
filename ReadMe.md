# Locker
![alt text](./img/locker.png)
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

```
Given locker with two box used

When press save button

Then return ticket with boxId==2
```

```
Given 2 lockers locker1 and locker2, 1 valid ticket of locker1

When press get button and input ticket to locker2

Then throw InvalidTicketException
```

```$xslt
# traverse hashMap every time to find empty box is time consuming
# we should use a stack to cache Id of empty box
# need to implemment this optimization and fullfill all previous tests

change expect boxId==21 for the test before last test
```

```$xslt
# ticket should not be resused
Given locker, ticket1 used, ticket2 unused

When press get button and input ticke1

Then throw InvalidTicketException
```

# Primary Locker Robot
![alt text](./img/locker-robot.png)
```
# check robot save 
Give robot, 1 locker, 

When ask robot save package

Then return ticket
```

```
# check robot get
Give robot, 1 locker, 1 valid ticket

When ask robot get package

Then robot should release the specific box accroding to ticket
```

```
# to check save order
Given robot, 3 lockers, 1st full and 2nd locker has empty box.

When 委托机器人存包

Then return ticket with ID of 2nd locker.
```

```
# to check get order
Given robot, 2 lockers, valid ticket of 2nd locker.

When 委托机器人存包

Then release target box.
```

```
# GREEN TEST
Given robot, 2 lockers, ALL full 

When 委托机器人存包

Then Throw NoEmptyBoxException.
```
#### Security Check

```
Given locker, ticket

When modify boxId on ticket

Then Throw InvalidTicketException
```


## Questions & Thoughts
- Should we keep unnecessary(green) test transfer from task to keep logic completeness?
- Refactor may leads to test fail, should we modify tests or implement trickily.
- TDD brings security issue, for instance public method only used by test may
accidentally used by other class. (accessor method only?)
 