---------------------
TITLE:
---------------------
Private Coding School Structure

---------------------
DESCRIPTION:
---------------------
This is a command prompt (console) application and simulates design aspects of a private coding school.
It connects to the local MySQL database and has the following functionality:

1.	It is a able print the following lists of data from the local database:
	• A list of all the students.
	• A list of all the trainers.
	• A list of all the assignments.
	• A list of all the courses.
	• All the students per course.
	• All the trainers per course.
	• All the assignments per course.
	• All the assignments per student.
	• A list of students that belong to more than one courses.

2.	It is able take input data from the console and insert new entries to the local database as follows:
                    • Insert a new Trainer. 
                    • Insert a new Assignment.
                    • Insert a new Student.
                    • Assign a Student to a Course.
                    • Assign a Trainer to a Course.
                    • Assign an Assignment to a Student per Course.

---------------------
PRESUPPOSITIONS:
---------------------
1.	The cardinality of the relationship between Trainers and Courses has been assumed to be Many to Many.
2.	The cardinality of the relationship between Students and Assignments has been assumed to be Many to Many.

---------------------
VERSION:
---------------------
1.0

---------------------
AUTHOR:
---------------------
Dimitrios Bouzoukas

---------------------
CONTACT:
---------------------
dimbouzoukas@gmail.com
