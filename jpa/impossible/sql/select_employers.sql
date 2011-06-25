select id
,  previousEmployer1Name as company
,  previousEmployer1StartDate as start
,  previousEmployer1EndDate as end
,  previousEmployer1Title as title
,  previousEmployer1SupervisorName as supervisor
,  previousEmployer1SupervisorPhone as phone
,  previousEmployer1SupervisorEmail as email
,  previousEmployer1SupervisorOkayToContact as okay
from application where id =:id

union

select id
,  previousEmployer2Name as company
,  previousEmployer2StartDate as start
,  previousEmployer2EndDate as end
,  previousEmployer2Title as title
,  previousEmployer2SupervisorName as supervisor
,  previousEmployer2SupervisorPhone as phone
,  previousEmployer2SupervisorEmail as email
,  previousEmployer2SupervisorOkayToContact as okay
from application where id =:id

union

select id
,  previousEmployer3Name as company
,  previousEmployer3StartDate as start
,  previousEmployer3EndDate as end
,  previousEmployer3Title as title
,  previousEmployer3SupervisorName as supervisor
,  previousEmployer3SupervisorPhone as phone
,  previousEmployer3SupervisorEmail as email
,  previousEmployer3SupervisorOkayToContact as okay
from application where id =:id

union

select id
,  previousEmployer4Name as company
,  previousEmployer4StartDate as start
,  previousEmployer4EndDate as end
,  previousEmployer4Title as title
,  previousEmployer4SupervisorName as supervisor
,  previousEmployer4SupervisorPhone as phone
,  previousEmployer4SupervisorEmail as email
,  previousEmployer4SupervisorOkayToContact as okay
from application where id =:id

