# SC2002

Academic victims

Class Diagram: https://drive.google.com/file/d/15pSz6I1CwTE3Syg5YPZCTxLL35eUMB9z/view?usp=sharing
Sequence Diagram: https://drive.google.com/file/d/1ZPB-S3n-ltpsRg66vPfY-xy4h-XSnRVn/view?usp=sharing
Sequence Diagram(alternative): https://lucid.app/lucidchart/34800e0d-b5b7-46e3-824d-ea31c8394199/edit?viewport_loc=-30%2C41%2C1352%2C851%2C0_0&invitationId=inv_55a8077d-9c02-4029-95d7-76b96ddfd8c1


LOGIC FLOW
__________


1. Check role of user
2. Login ( with NRIC and password ), (using while loop check back with csv  file to see if user matches)
3. Extract from csv their, NRIC, name, marital status, password
4. Create current person object, (Using different constructors based on role)
5. Display options for execution


CLASSES
________

Applicant