# Small holiday request manager

##### Technologies used
* backend : Spring Boot
* frontend: Angular-Materials
* authentication: Google OAuth2 & Spring Security

##### How it works
When accessing the application, the user is asked to log-in using a google account.
If it's his first time, the main informations from his google profile (name, email address, id) are saved in database, allowing to link calendar entries to his profile.
Authenticated user can then access it's calendar and create new vacancy requests. Those will be marked as pending until an admin approves (or rejects) them.

##### Todo
* add an admin control panel to allow requests management (the REST service is already working)
