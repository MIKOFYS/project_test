#创建管理员表
CREATE TABLE administrator(administrator_id VARCHAR(10) PRIMARY KEY,name VARCHAR(10),sex VARCHAR(2),password VARCHAR(32));

#创建学生表
CREATE TABLE student(student_id VARCHAR(10) PRIMARY KEY,name VARCHAR(10),sex VARCHAR(10),phone VARCHAR(15),email VARCHAR(30),picture VARCHAR(60),class_id VARCHAR(10),password VARCHAR(32),hashcode VARCHAR(32),forget_password_flag INT);

#创建班级表
CREATE TABLE class(class_id VARCHAR(10) PRIMARY KEY,grade_id VARCHAR(10),major_id VARCHAR(10));

#创建年级表
CREATE TABLE grade(grade_id VARCHAR(10) PRIMARY KEY);

#创建学院表
CREATE TABLE dept(dept_id VARCHAR(10) PRIMARY KEY,dept_name VARCHAR(10));

#创建专业表
CREATE TABLE major(major_id VARCHAR(10) PRIMARY KEY,major_name VARCHAR(10),dept_id VARCHAR(10),CONSTRAINT major_dept FOREIGN KEY(dept_id) REFERENCES dept(dept_id) ON UPDATE CASCADE ON DELETE CASCADE);

#创建老师表
CREATE TABLE teacher(teacher_id VARCHAR(10) PRIMARY KEY,name VARCHAR(10),sex VARCHAR(10),phone VARCHAR(15),email VARCHAR(30),picture VARCHAR(60),dept_id VARCHAR(10),password VARCHAR(32),hashcode VARCHAR(32),forget_password_flag INT,CONSTRAINT teacher_dept FOREIGN KEY(dept_id) REFERENCES dept(dept_id) ON UPDATE CASCADE ON DELETE CASCADE);

#创建课程表
CREATE TABLE course(course_id VARCHAR(10) PRIMARY KEY,name VARCHAR(10),credit INT,period INT);

#教学班
CREATE TABLE teach_class(teach_class_id VARCHAR(10) PRIMARY KEY,course_id VARCHAR(10),teacher_id VARCHAR(10),CONSTRAINT teachclass_course FOREIGN KEY(course_id) REFERENCES course(course_id) ON UPDATE CASCADE ON DELETE CASCADE);

#学生-课程
CREATE TABLE student_course(student_id VARCHAR(10),course_id VARCHAR(10),score DOUBLE,PRIMARY KEY(student_id,course_id),CONSTRAINT student_course_student FOREIGN KEY(student_id) REFERENCES student(student_id),CONSTRAINT student_course_course FOREIGN KEY(course_id) REFERENCES course(course_id) ON UPDATE CASCADE ON DELETE CASCADE);

#学生-教学班表
CREATE TABLE student_teach_class(student_id VARCHAR(10),teach_class_id VARCHAR(10),PRIMARY KEY (student_id,teach_class_id));

#教学班-排课时间表
CREATE TABLE teach_class_time_room(teach_class_id VARCHAR(32),time VARCHAR(100),room VARCHAR(32));

#课程-题库表
CREATE TABLE course_question_bank(course_id VARCHAR(10),paper_id VARCHAR(10));

#试卷表
CREATE TABLE Paper_bank(paper_id VARCHAR(10),directory VARCHAR(30));

#学生已经修过的课程表
CREATE TABLE student_have_taken_course(student_id VARCHAR(10),course_id VARCHAR(10));

#学生选课时的选课记录表
CREATE TABLE student_selected_course(student_id VARCHAR(10),course_id VARCHAR(10));

#学生在教学班所做试卷记录表
CREATE TABLE paper_in_teach_class(teach_class_id VARCHAR(10),student_id VARCHAR(10),paper_id VARCHAR(10),score DOUBLE,directory VARCHAR(30),time VARCHAR(30));