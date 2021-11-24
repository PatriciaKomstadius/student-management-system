#Lab 2 Java EE JPA
Queries with JPQL
- Added TEACHER ENTITY
- Added SUBJECT ENTITY
- Functions added:
  - Create teacher
  - Add one or more subjects for each teacher 
  - Create subject
  - Add one or more students for each subject 
  - Get list of all students, subjects and teacher with one endpoint
- Added SampleData to DB for testing purpose


##CREATE
- **CREATE a new teacher:**

Post body in JSON. firstName, lastName och email are mandatory.

Example:
```
{
"firstName": "Lollo",
"lastName" : "Bruno",
"phoneNumber" : 12345,
"email" : "lollo@school.com"
}
```

- **ADD a subject to a teacher:**

Enter teachers ID as a URL-param and post body with subject in JSON.

Example:
```
{
	"subject" : "Psychology"
}
 ```
>URL: http://localhost:8080/student-management-system/api/v1/teachers/addsubjectsforteacher/{id}  
_Enter ID number 10 for testing purpose._

- **ADD student to subject:**

Specify which subject by entering subject-ID in URL. Post body in JSON.

Example:
```
{
	"firstName" : "David",
	"lastName" : "Olsson",
	"email" : "david@mail.com"
}
 ```
>URL: http://localhost:8080/student-management-system/api/v1/subjects/addstudentstosubject/{id}  
_Enter ID number 1 for testing purpose._



##READ

**- GET All information about a subject _(including list of all enrolled students and teacher):_**

>URL: http://localhost:8080/student-management-system/api/v1/students/subjects/{subject}  
> _Enter subject {English} for testing purpose._

**- GET list of all teachers:**

>URL: http://localhost:8080/student-management-system/api/v1/teachers

_____________________________________________________________________________________________


#Lab 1 Java EE

I detta projekt har CRUD funktionalitet skapats för ett student managementsystem.
Felhanteringen returnerar svar i JSON.

****Instruktioner för att testa endpoints:****


*****CREATE*****

POST http://localhost:8080/student-management-system/api/v1/students

Obligatoriska fält: firstName, lastName och email.
```
{
	"firstName" : "NAMN",
	"lastName" : "NAMN",
	"email" : "MAIL@MAIL.com",
	"phoneNumber" : "1234567890"	
}
```


*****READ*****
- GET lastName
_Hämtar alla studenter med angett efternamn. Ange lastname som query parameter._
 http://localhost:8080/student-management-system/api/v1/students/lastname


- GET id
_Hämtar en student med angivet ID-nr. ID anges i URL:n._
http://localhost:8080/student-management-system/api/v1/students/1


- GET all
_Hämtar alla registrerade studenter._
http://localhost:8080/student-management-system/api/v1/students



*****UPDATE*****

_Uppdatera student med PUT._
http://localhost:8080/student-management-system/api/v1/students

_Ange ID i JSON-bodyn för att uppdatera uppgifter för registrerad student._
_Obligatoriska fält: firstName, lastName och email._
```
{  
	"firstName" : "NAMN",
	"lastName" : "NAMN",
	"email" : "MAIL@MAIL.com",
	"phoneNumber" : "0812345678",
	"id" : 1
}
```

*****DELETE*****

_För att ta bort en student ange ID. ID anges i URL:n._

http://localhost:8080/student-management-system/api/v1/students/1


**Exceptions**

Felhantering i koden hanteras med ExceptionMappers för
NotFound och BadRequest. Exceptions returneras i JSON-format.


