-- this is an example of impossibly *bad* database design it is hard 
-- to imagine someone keeping their job after writing something like
-- this... yet these are structures from real databases I've worked
-- on and the applications held hundreds of gigabytes of data worth 
-- millions of dollars.
--
-- The purpose of this design is to create "impossible" to deal
-- with mappings for working with JPA
create table application (
  id integer auto_increment,
  startedDate date,
  editedDate date,
  submittedDate date,
  submitted bit,
  deleted bit,
  firstName varchar(255),
  middleNameOrMI varchar(255),
  lastName varchar(255),
  secondLastName varchar(255),
  nickName varchar(255),
  email varchar(255),
  countryOfResidence varchar(255),
  countryOfCitizenship varchar(255),
  countryOfCitizenship2 varchar(255),
  addressLine1 varchar(255),
  addressLine2 varchar(255),
  addressCity varchar(255),
  addressStateOrProvence varchar(255),
  addressCountry varchar(255),
  addressPostalCodeOrZipCode varchar(255),
  addressIsCanadian bit,
  addressIsInUK bit,
  addressPoliticalState varchar(255),
  mailingAddressLine1 varchar(255),
  mailingAddressLine2 varchar(255),
  mailingAddressCity varchar(255),
  mailingAddressStateOrProvence varchar(255),
  mailingAddressCountry varchar(255),
  mailingAddressPostalCodeOrZipCode varchar(255),
  mailingAddressIsCanadian bit,
  mailingAddressIsInUK bit,
  mailingAddressPoliticalState varchar(255),
  previousEmployer1Name varchar(255),
  previousEmployer1StartDate date,
  previousEmployer1EndDate date,
  previousEmployer1Title varchar(255),
  previousEmployer1SupervisorName varchar(255),
  previousEmployer1SupervisorPhone varchar(255),
  previousEmployer1SupervisorEmail varchar(255),
  previousEmployer1SupervisorOkayToContact bit,
  previousEmployer2Name varchar(255),
  previousEmployer2StartDate date,
  previousEmployer2EndDate date,
  previousEmployer2Title varchar(255),
  previousEmployer2SupervisorName varchar(255),
  previousEmployer2SupervisorPhone varchar(255),
  previousEmployer2SupervisorEmail varchar(255),
  previousEmployer2SupervisorOkayToContact bit,
  previousEmployer3Name varchar(255),
  previousEmployer3StartDate date,
  previousEmployer3EndDate date,
  previousEmployer3Title varchar(255),
  previousEmployer3SupervisorName varchar(255),
  previousEmployer3SupervisorPhone varchar(255),
  previousEmployer3SupervisorEmail varchar(255),
  previousEmployer3SupervisorOkayToContact bit,
  previousEmployer4Name varchar(255),
  previousEmployer4StartDate date,
  previousEmployer4EndDate date,
  previousEmployer4Title varchar(255),
  previousEmployer4SupervisorName varchar(255),
  previousEmployer4SupervisorPhone varchar(255),
  previousEmployer4SupervisorEmail varchar(255),
  previousEmployer4SupervisorOkayToContact bit,
  education1SchoolName varchar(255),
  education1StartDate varchar(255),
  education1EndDate varchar(255),
  education1DegreeeName varchar(255),
  education1Years float,
  education1GPA float,
  education2SchoolName varchar(255),
  education2StartDate varchar(255),
  education2EndDate varchar(255),
  education2DegreeeName varchar(255),
  education2Years float,
  education2GPA float,
  education3SchoolName varchar(255),
  education3StartDate varchar(255),
  education3EndDate varchar(255),
  education3DegreeeName varchar(255),
  education3Years float,
  education3GPA float,
  curriculumVitae blob,
  processBeginDate date,
  processBeginBy varchar(255),
  processApprovedBy varchar(255),
  processEndDate date,
  primary key(id)
);

create table candidate (
  id integer auto_increment,
  application_identification_number integer not null,
  candidateCreateDate date,
  planeTicket1Number varchar(255),
  planeTicket2Number varchar(255),
  planeTicket3Number varchar(255),
  initialOfferAmount float,
  finalOfferAmount float,
  offerAgentInitial varchar(255),
  offerAgentFinal varchar(255),
  offerLetter blob,
  positionName varchar(255),
  reportInDateMonth integer,
  reportInDateDay integer,
  reportInDateYear integer,
  reportedIn bit,
  primary key(id)
);

create table employee (
  id integer auto_increment,
  application_id integer not null,
  candi_id integer not null,
  service_year enum('first year','second year', 'third year', 'fourth year', 'fifth year', 'vested employee'),
  started date,
  terminated date,
  amount_of_stock integer,
  disciplineAction1 varchar(255),
  disciplineAction1Letter blob,
  disciplienAction2 varchar(255),
  disciplineActionTwoLetter blob,
  disciplineAction3 varchar(255),
  disciplineAction03Letter blob,
  manager varchar(255)
  primary key(id)
);

-- the amount of stock released for the employee
-- based on their start date and service year.
create table stock_allotments (
  last_started date,
  service_year varchar(255),
  percent_released varchar(255)
);

create table evaluation (
  id integer auto_increment,
  xml varchar(255),
  primary key(id)
);

create table vacation_request (
  id integer auto_increment,
  xml varchar(255),
  primary key(id)
);


