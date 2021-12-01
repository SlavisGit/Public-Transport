
IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[TICKET]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [TICKET]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[TRAVEL]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [TRAVEL]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[DESTINATION]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [DESTINATION]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[STATUS]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [STATUS]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[TRANSPORT]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [TRANSPORT]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[TRAVEL_TYPE]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [TRAVEL_TYPE]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[USER]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [USER]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[USER_TYPE]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [USER_TYPE]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[CASHIER]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [CASHIER]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[STATION]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [STATION]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[COMPANY]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [COMPANY]
END
GO

IF EXISTS (SELECT * 
			FROM sys.objects 
			WHERE object_id = OBJECT_ID(N'[ADMINISTRATOR]') 
				AND type in (N'U'))
BEGIN
	DROP TABLE [ADMINISTRATOR]
END
GO


CREATE TABLE ADMINISTRATOR 
( 
	id				INT NOT NULL IDENTITY(1, 1), 
	firstName		NVARCHAR(64) NOT NULL,
	lastName		NVARCHAR(64) NOT NULL,

CONSTRAINT PK_ADMINISTRATOR_ID PRIMARY KEY (id)
)
GO

CREATE TABLE COMPANY 
( 
	id				INT NOT NULL IDENTITY(1, 1), 
	[name]			NVARCHAR(64) NOT NULL,
	[address]		NVARCHAR(64) NOT NULL,
	adminId		INT NOT NULL,

CONSTRAINT PK_COMPANY_ID PRIMARY KEY (id)
)
GO

SELECT * FROM COMPANY

ALTER TABLE COMPANY ADD CONSTRAINT FK_COMPANY_ADMIN_ID FOREIGN KEY(adminId) 
REFERENCES ADMINISTRATOR(id)
GO

CREATE TABLE STATION 
( 
	id				INT NOT NULL IDENTITY(1, 1), 
	[name]			NVARCHAR(64) NOT NULL,
	[address]		NVARCHAR(64) NOT NULL,
	workTimeStart	TIME NOT NULL,
	workTimeEnd		TIME NOT NULL,
	adminId			INT NOT NULL,

CONSTRAINT PK_STATION_ID PRIMARY KEY (id)
)
GO

ALTER TABLE STATION ADD CONSTRAINT FK_STATION_ADMIN_ID FOREIGN KEY(adminId) 
REFERENCES ADMINISTRATOR(id)
GO

CREATE TABLE CASHIER 
( 
	id				INT NOT NULL IDENTITY(1, 1), 
	firstName		NVARCHAR(64) NOT NULL,
	lastName		NVARCHAR(64) NOT NULL,
	ucn				NVARCHAR(16) NOT NULL,
	honorarium		FLOAT NOT NULL,
	stationId		INT NOT NULL,

CONSTRAINT PK_CASHIER_ID PRIMARY KEY (id)
)
GO

ALTER TABLE CASHIER ADD CONSTRAINT FK_CASHIER_STATION_ID FOREIGN KEY(stationId) 
REFERENCES STATION(id)
GO

CREATE TABLE USER_TYPE 
( 
	id				INT NOT NULL IDENTITY(1, 1), 
	userType		NVARCHAR(64) NOT NULL,

CONSTRAINT PK_USER_TYPE_ID PRIMARY KEY (id)
)
GO

CREATE TABLE [USER]
( 
	id				INT NOT NULL IDENTITY(1, 1), 
	username		NVARCHAR(64) NOT NULL,  
	[password]		NVARCHAR(64) NOT NULL,
	admintId		INT,
	cashierId		INT,
	stationId		INT,
	companyId		INT,
	userTypeId		INT,

CONSTRAINT PK_USER_ID PRIMARY KEY (id)
)
GO

SELECT * FROM [USER]

ALTER TABLE [USER] ADD CONSTRAINT FK_USER_ID FOREIGN KEY(admintId) 
REFERENCES ADMINISTRATOR(id)
GO
ALTER TABLE [USER] ADD CONSTRAINT FK_USER_CASHIER_ID FOREIGN KEY(cashierId) 
REFERENCES CASHIER(id)
GO
ALTER TABLE [USER] ADD CONSTRAINT FK_USER_STATION_ID FOREIGN KEY(stationId) 
REFERENCES STATION(id)
GO
ALTER TABLE [USER] ADD CONSTRAINT FK_USER_COMPANY_ID FOREIGN KEY(companyId) 
REFERENCES COMPANY(id)
GO
ALTER TABLE [USER] ADD CONSTRAINT FK_USER_USER_TYPE_ID FOREIGN KEY(userTypeId) 
REFERENCES USER_TYPE(id)
GO



CREATE TABLE [STATUS] 
( 
	id				INT NOT NULL IDENTITY(1, 1), 
	[status]		NVARCHAR(32) NOT NULL,

CONSTRAINT PK_STATUS_ID PRIMARY KEY (id)
)
GO

CREATE TABLE TRANSPORT 
( 
	id				  INT NOT NULL IDENTITY(1, 1), 
	transportType	  NVARCHAR(64) NOT NULL,

CONSTRAINT PK_TRANSPORT_ID PRIMARY KEY (id)
)
GO

CREATE TABLE DESTINATION 
( 
	id				  INT NOT NULL IDENTITY(1, 1), 
	destination	  NVARCHAR(64) NOT NULL,

CONSTRAINT PK_DESTINATION_ID PRIMARY KEY (id)
)
GO


CREATE TABLE TRAVEL_TYPE 
( 
	id				  INT NOT NULL IDENTITY(1, 1), 
	travelType		  NVARCHAR(64) NOT NULL,

CONSTRAINT PK_TRAVEL_TYPE_ID PRIMARY KEY (id)
)
GO

SELECT * FROM ADMINISTRATOR

CREATE TABLE TRAVEL 
( 
	id					INT NOT NULL IDENTITY(1, 1),                                                                                                                             
	travelTypeId		INT NOT NULL,
	destinationId		INT NOT NULL, 
	transportTypeId		INT NOT NULL,
	dataTo				DATE NOT NULL,
	dataFrom			DATE NOT NULL,
	countPlaces			INT NOT NULL,
	limitation			INT NOT NULL,
	companyId			INT NOT NULL,
	stationId			INT NOT NULL,

CONSTRAINT PK_TRAVEL_ID PRIMARY KEY (id)
)
GO

ALTER TABLE TRAVEL ADD CONSTRAINT FK_TRAVEL_TRAVEL_TYPE_ID FOREIGN KEY(travelTypeId) 
REFERENCES TRAVEL_TYPE(id)
GO

ALTER TABLE TRAVEL ADD CONSTRAINT FK_TRAVEL_DESTINATION_ID FOREIGN KEY(destinationId) 
REFERENCES DESTINATION(id)
GO

ALTER TABLE TRAVEL ADD CONSTRAINT FK_TRAVEL_TRANSPORT_ID FOREIGN KEY(transportTypeId) 
REFERENCES TRANSPORT(id)
GO

ALTER TABLE TRAVEL ADD CONSTRAINT FK_TRAVEL_COMPANY_ID FOREIGN KEY(companyId) 
REFERENCES COMPANY(id)
GO

ALTER TABLE TRAVEL ADD CONSTRAINT FK_TRAVEL_STATION_ID FOREIGN KEY(stationId) 
REFERENCES STATION(id)
GO

CREATE TABLE TICKET 
( 
	id					INT NOT NULL IDENTITY(1, 1), 
	travelId			INT NOT NULL, 
	cashierId			INT NOT NULL,
	price				FLOAT NOT NULL,
	statusTypeId		INT NOT NULL,

CONSTRAINT PK_TICKET_ID PRIMARY KEY (id)
)
GO

ALTER TABLE TICKET ADD CONSTRAINT FK_TICKET_TRAVEL_ID FOREIGN KEY(travelId) 
REFERENCES TRAVEL(id)
GO

ALTER TABLE TICKET ADD CONSTRAINT FK_TICKET_CASHIER_ID FOREIGN KEY(cashierId) 
REFERENCES CASHIER(id)
GO

ALTER TABLE TICKET ADD CONSTRAINT FK_TICKET_STATUS_ID FOREIGN KEY(statusTypeId) 
REFERENCES [STATUS](id)
GO

BEGIN TRY
	BEGIN TRANSACTION

		INSERT INTO USER_TYPE(userType) VALUES ('Admin')

		INSERT INTO USER_TYPE(userType) VALUES ('Cashier')

		INSERT INTO USER_TYPE(userType) VALUES ('Company')

		INSERT INTO USER_TYPE(userType) VALUES ('Station')
	COMMIT TRANSACTION
END TRY
BEGIN CATCH
	ROLLBACK TRANSACTION
	RETURN
END CATCH
GO

BEGIN TRY
	BEGIN TRANSACTION

		INSERT INTO ADMINISTRATOR(firstName, lastName) VALUES ('Ivan', 'Petkov')

		INSERT INTO [USER] (username, [password], admintId, userTypeId) VALUES ('aaa', '123', 1, 1)

		INSERT INTO STATION([name], [address],workTimeStart, workTimeEnd, adminId) VALUES ('VarnaStation', 'Varna', '08:10' ,'18:50', 1)

	COMMIT TRANSACTION
END TRY
BEGIN CATCH
	ROLLBACK TRANSACTION
	RETURN
END CATCH
GO

SELECT * FROM [USER]
GO

SELECT * FROM STATION
GO
