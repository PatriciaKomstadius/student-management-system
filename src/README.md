****Lab 1 Java EE****
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



