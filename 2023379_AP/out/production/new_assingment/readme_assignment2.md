Assignment-2

**GENERIC PROGRAMMING**
A generic class is there named Feedback 
which takes course, rating and comment for that course and then it is called by 
professor of that particular course can access the feedback for its course

The feedbacks are anonymous 

Student after registered for the course can add feedback where it asks
for student feedback for which course it want to give
and then professor can access that via your_feedback()


**OBJECT CLASS**
tha TA class only able to see which student enrolled in their class
and TA class use "String email, String password, String name, Object b"
where it checks if b is instance of Course


**EXCEPTION HANDLING**
custom exception classes like InvalidLoginException
and CourseFullException to handle specific problems

InvalidLoginException happens when a user types the wrong email or password

CourseFullException is used when a student tries to sign up for a course that 
doesn't have any available spots.

I used try-catch blocks to handle these exceptions
If something goes wrong, it catches the exception and provides
a clear message to the user

