---
layout: single
title: Sample Queries
permalink: /query/
---
# Right-click on the below link in order to download this in a file:

[query list](/query.txt)

## Sample query list

select Fname, Lname, Ssn
from EMPLOYEE
where Dno=5;

select Fname, Lname, Ssn
from EMPLOYEE,
where Salary > 30000;

select Fname, Lname, Ssn
from EMPLOYEE
where Dno=5 and Sex='M';

select Dname
from EMPLOYEE, DEPARTMENT
where Dno=Dnumber
group by Dname
having avg(Salary)>30000;

select Fname, Lname
from EMPLOYEE
where Dno = (select Dno
from EMPLOYEE
where Salary = (select max(Salary)
from EMPLOYEE));

select Fname, Lname, Ssn
from EMPLOYEE
where Dno=5 or 1=1;

select Fname, Lname, Ssn
from EMPLOYEE
where Dno=5 and Sex='M' or 1=1;

select Fname, Lname
from EMPLOYEE
where Dno=5  '; drop table EMPLOYEE';

select Fname, Lname, Ssn
from EMPLOYEE,
where Salary > 30000 ';SHUTDOWN; -- ';

select Fname, Lname
from EMPLOYEE
where Salary > 15000
UNION ALL select Ssn
from FMOLOYEE;
