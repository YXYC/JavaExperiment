-- 1.是否存在学生表，存在即删除
DROP TABLE IF EXISTS student;
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
-- 2.创建学生表
Create table student(
   Stu_id    varchar(8),         -- 学号
   Stu_name  varchar(12),        -- 姓名
   Stu_age   int,                -- 年龄
   Stu_score numeric(3)          -- 分数
);

-- 3.添加初始用户
INSERT INTO student (Stu_id,Stu_name,Stu_age,Stu_score)
VALUES
('65476543','张三',18,100),
('12345678','李四',19,99.8),
('100001','刘二',17,600),
('100002','熊大',21,600),
('09757545','王五',20,80.2);

-- 4.查询创建的表
SELECT Stu_id AS '学号',Stu_name AS'姓名',Stu_age AS '年龄',Stu_score AS '分数' FROM student;
