IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'BBDUkukhulaDB')
BEGIN
  CREATE DATABASE BBDUkukhulaDB;
END;
GO

USE BBDUkukhulaDB;
GO


-- create Ethnicities table

IF OBJECT_ID('[dbo].[Ethnicities]') IS NULL
CREATE TABLE [dbo].[Ethnicities]
(
	[EthnicityID] INT IDENTITY(1,1) NOT NULL,
	[Ethnicity] VARCHAR(20) NOT NULL,
  
	CONSTRAINT PK_EthnicityID
		PRIMARY KEY CLUSTERED ([EthnicityID]),
	CONSTRAINT [UNQ_Ethnicity] UNIQUE ([Ethnicity])
);
GO

-- create Roles table

IF OBJECT_ID('[dbo].[Roles]') IS NULL
CREATE TABLE [dbo].[Roles]
(
	[RoleID] INT IDENTITY(1,1) NOT NULL,
	[Role] VARCHAR(20) NOT NULL,
  
	CONSTRAINT PK_RoleID
		PRIMARY KEY CLUSTERED ([RoleID]),
	CONSTRAINT [UNQ_Role] UNIQUE ([Role])
);
GO

-- create Departments table

IF OBJECT_ID('[dbo].[Departments]') IS NULL
CREATE TABLE [dbo].[Departments]
(
	[DepartmentID] INT IDENTITY(1,1) NOT NULL,
	[DepartmentName] VARCHAR(20) NOT NULL,
  
	CONSTRAINT PK_DepartmentID
		PRIMARY KEY CLUSTERED ([DepartmentID]),
	CONSTRAINT [UNQ_Department] UNIQUE ([DepartmentID])
);
GO

-- create BursaryDetails table

IF OBJECT_ID('[dbo].[BursaryDetails]') IS NULL
CREATE TABLE [dbo].[BursaryDetails]
(
	[BursaryDetailsID] INT IDENTITY(1,1) NOT NULL,
	[Year] INT NOT NULL,
	[TotalAmount] MONEY NOT NULL,

	CONSTRAINT PK_BursaryDetailsID 
		PRIMARY KEY CLUSTERED ([BursaryDetailsID]),
	CONSTRAINT [UNQ_Year] UNIQUE([Year]),
	CONSTRAINT [CHK_Year] CHECK([Year] >= 2020),
	CONSTRAINT [CHK_TotalAmount] CHECK([TotalAmount] > 0)
);
GO

-- create Contacts table

IF OBJECT_ID('[dbo].[Contacts]') IS NULL
CREATE TABLE [dbo].[Contacts]
(
	[ContactID] INT IDENTITY(1,1) NOT NULL,
	[PhoneNumber] CHAR(10) NOT NULL,
	[Email] VARCHAR(100) NOT NULL,

	CONSTRAINT PK_ContactID
		PRIMARY KEY CLUSTERED ([ContactID]),
	CONSTRAINT [CHK_PhoneNumber] CHECK(ISNUMERIC(PhoneNumber) = 1)
);
GO

-- create Statuses table

IF OBJECT_ID('[dbo].[Statuses]') IS NULL
CREATE TABLE [dbo].[Statuses]
(
	[StatusID] INT IDENTITY(1,1) NOT NULL,
	[Status] CHAR(12) NOT NULL,
 
	CONSTRAINT PK_StatusID 
		PRIMARY KEY CLUSTERED ([StatusID])
);
GO

-- create IsActive table

IF OBJECT_ID('[dbo].[IsActive]') IS NULL
CREATE TABLE [dbo].[IsActive]
(
	[IsActiveID] INT IDENTITY(1,1) NOT NULL,
	[IsActiveStatus] CHAR(3) NOT NULL,
  
	CONSTRAINT PK_IsActiveID 
		PRIMARY KEY CLUSTERED ([IsActiveID])
);
GO

-- create Users table

IF OBJECT_ID('[dbo].[Users]') IS NULL
CREATE TABLE [dbo].[Users]
(
	[UserID] INT IDENTITY(1,1) NOT NULL ,
	[FirstName] VARCHAR(50) NOT NULL,
	[LastName] VARCHAR(50) NOT NULL,
	[ContactID] INT NOT NULL,
	[IsActiveID] INT NOT NULL,
  
	CONSTRAINT PK_UserID
		PRIMARY KEY CLUSTERED ([UserID]),
	CONSTRAINT FK_Users_ContactID 
		FOREIGN KEY ([ContactID]) 
		REFERENCES [dbo].[Contacts]([ContactID]),
	CONSTRAINT FK_Users_IsActiveID
		FOREIGN KEY ([IsActiveID])
		REFERENCES [dbo].[IsActive]([IsActiveID])
);
GO

-- create Universities

IF OBJECT_ID('[dbo].[Universities]') IS NULL
CREATE TABLE [dbo].[Universities]
(
	[UniversityID] INT IDENTITY(1,1) NOT NULL,
	[UniversityName] VARCHAR(100) NOT NULL,
	[IsActiveID] INT NOT NULL,

	CONSTRAINT PK_UniversityID
		PRIMARY KEY CLUSTERED ([UniversityID]),
	CONSTRAINT FK_University_IsActiveID
		FOREIGN KEY([IsActiveID])
		REFERENCES [dbo].[IsActive]([IsActiveID])
);
GO

-- create UserRole

IF OBJECT_ID('[dbo].[UserRole]') IS NULL
CREATE TABLE [dbo].[UserRole]
(
	[UserID] INT NOT NULL,
	[RoleID] INT NOT NULL,


	CONSTRAINT PK_UserID_RoleID
		PRIMARY KEY CLUSTERED ([UserID], [RoleID]),
	CONSTRAINT FK_UserRole_UserID
		FOREIGN KEY ([UserID]) 
		REFERENCES [dbo].[Users]([UserID]),
	CONSTRAINT FK_UserRole_RoleID
		FOREIGN KEY([RoleID])
		REFERENCES [dbo].[Roles]([RoleID])
);
GO
-- create UniversityApplication table

IF OBJECT_ID('[dbo].[UniversityApplications]') IS NULL
CREATE TABLE [dbo].[UniversityApplications]
(
	[UniversityApplicationID] INT IDENTITY(1,1) NOT NULL,
	[UniversityID] INT NOT NULL,
	[StatusID] INT NOT NULL,
	[Motivation] TEXT NOT NULL,
	[Date] DATE NOT NULL DEFAULT GETDATE(),
	[ReviewerID_UserID] INT NOT NULL,
	[ReviewerComment] TEXT NOT NULL DEFAULT 'N/A',

	CONSTRAINT PK_UniversityApplicationID 
		PRIMARY KEY CLUSTERED ([UniversityApplicationID]),
	CONSTRAINT [FK_UniversityApplication_UniversityID] 
		FOREIGN KEY([UniversityID]) 
		REFERENCES [dbo].[Universities]([UniversityID]),
	CONSTRAINT [FK_UniversityApplication_StatusID] 
		FOREIGN KEY([StatusID]) 
		REFERENCES [dbo].[Statuses]([StatusID]),
	CONSTRAINT [CHK_DateBeforeOrToday] CHECK([Date] <= CAST(GETDATE() as date)),
	CONSTRAINT [CHK_DateAfter2019] CHECK([Date] >= CAST('01/06/2019' as date)),
	CONSTRAINT [FK_UniversityApplication_ReviewerID] 
		FOREIGN KEY([ReviewerID_UserID]) 
		REFERENCES [dbo].[Users]([UserID])
);
GO

-- create UniversityStaff table

IF OBJECT_ID('[dbo].[UniversityStaff]') IS NULL
CREATE TABLE [dbo].[UniversityStaff]
(
	[UniversityStaffID] INT IDENTITY(1,1) NOT NULL,
	[UserID] INT NOT NULL,
	[UniversityID] INT NOT NULL,
	[DepartmentID] INT NOT NULL,

	CONSTRAINT PK_UniversityStaffID
		PRIMARY KEY CLUSTERED ([UniversityStaffID]),
	CONSTRAINT FK_UniversityStaff_UserID
		FOREIGN KEY ([UserID]) 
		REFERENCES [dbo].[Users]([UserID]),
	CONSTRAINT FK_UniversityStaff_UniversityID
		FOREIGN KEY([UniversityID])
		REFERENCES [dbo].[Universities]([UniversityID]),
	CONSTRAINT FK_UniversityStaff_DepartmentID
		FOREIGN KEY([DepartmentID])
		REFERENCES [dbo].[Departments]([DepartmentID])
)
GO

-- create UniversityAllocation table

IF OBJECT_ID('[dbo].[UniversityAllocation]') IS NULL
CREATE TABLE [dbo].[UniversityAllocation]
(
	[UniversityAllocationID] INT IDENTITY(1,1) NOT NULL,
	[UniversityID] INT NOT NULL,
	[Amount] MONEY NOT NULL,
	[BursaryDetailsID] INT NOT NULL,

	CONSTRAINT PK_UniversityAllocationID 
		PRIMARY KEY CLUSTERED ([UniversityAllocationID]),
	CONSTRAINT FK_UniversityAllocation_UniversityID
		FOREIGN KEY ([UniversityID])
		REFERENCES [dbo].[Universities]([UniversityID]),
	CONSTRAINT FK_UniversityAllocation_BursaryDetailsID
		FOREIGN KEY ([BursaryDetailsID])
		REFERENCES [dbo].[BursaryDetails]([BursaryDetailsID])
);
GO

-- create Students table

IF OBJECT_ID('[dbo].[Students]') IS NULL
CREATE TABLE [dbo].[Students]
(
	[StudentID] INT IDENTITY(1,1) NOT NULL,
	[UserID] INT NOT NULL,
	[IDNumber] CHAR(13) NOT NULL,
	[EthnicityID] INT NOT NULL,
	[UniversityID] INT NOT NULL,
	[DepartmentID] INT NOT NULL

	CONSTRAINT PK_StudentID
		PRIMARY KEY CLUSTERED ([StudentID]),
	CONSTRAINT FK_Students_UserID
		FOREIGN KEY ([UserID]) 
		REFERENCES [dbo].[Users]([UserID]),
	CONSTRAINT FK_Students_EthnicityID
		FOREIGN KEY ([EthnicityID]) 
		REFERENCES [dbo].[Ethnicities]([EthnicityID]),
	CONSTRAINT FK_Students_UniversityID
		FOREIGN KEY ([UniversityID]) 
		REFERENCES [dbo].[Universities]([UniversityID]),
	CONSTRAINT FK_Students_DepartmentID
		FOREIGN KEY ([DepartmentID]) 
		REFERENCES [dbo].[Departments]([DepartmentID])

);
GO

-- create StudentApplications table

IF OBJECT_ID('[dbo].[StudentApplications]') IS NULL
CREATE TABLE [dbo].[StudentApplications]
(
	[StudentApplicationID] INT IDENTITY(1,1) NOT NULL,
	[StudentID] INT NOT NULL,
	[Motivation] TEXT NOT NULL,
	[BursaryAmount] MONEY NOT NULL,
	[StatusID] INT NOT NULL,
	[Reviewer_UserID] INT NOT NULL,
	[ReviewerComment] TEXT NOT NULL DEFAULT 'N/A',
	[Date] DATE NOT NULL DEFAULT GETDATE(),
	[UniversityStaffID] INT NOT NULL,

	CONSTRAINT PK_StudentApplicationID 
		PRIMARY KEY CLUSTERED ([StudentApplicationID]),
	CONSTRAINT FK_StudentApplications_StudentID
		FOREIGN KEY ([StudentID]) 
		REFERENCES [dbo].[Students]([StudentID]),
	CONSTRAINT [CHK_BursarAmountNotNegative] CHECK([BursaryAmount] > 0),
	CONSTRAINT [CHK_BursarAmountLessThanMax] CHECK([BursaryAmount] <= 125000),
	CONSTRAINT FK_StudentApplications_StatusID
		FOREIGN KEY ([StatusID]) 
		REFERENCES [dbo].[Statuses]([StatusID]),
	CONSTRAINT FK_StudentApplications_Reviewer_UserID
		FOREIGN KEY ([Reviewer_UserID]) 
		REFERENCES [dbo].[Users]([UserID]),
	CONSTRAINT FK_StudentApplications_UniversityStaffID
		FOREIGN KEY ([UniversityStaffID]) 
		REFERENCES [dbo].[UniversityStaff]([UniversityStaffID]),
	CONSTRAINT [CHK_DateBeforeOrToday] CHECK([Date] <= CAST(GETDATE() as date)),
	CONSTRAINT [CHK_DateAfter2019] CHECK([Date] >= CAST('01/01/2020' as date))
);
GO

-- create StudentApplicationDocuments table

IF OBJECT_ID('[dbo].[StudentApplicationDocuments]') IS NULL
CREATE TABLE [dbo].[StudentApplicationDocuments]
(
	[StudentApplicationDocumentsID] INT IDENTITY(1,1) NOT NULL,
	[StudentApplicationID] INT NOT NULL,
	[CertifiedIdCopy] VARCHAR(250) NOT NULL,
	[AcademicTranscript] VARCHAR(250) NOT NULL,
	[CurriculumVitae] VARCHAR(250) NOT NULL,

	CONSTRAINT FK_StudentApplicationDocuments_StudentApplicationID
		FOREIGN KEY ([StudentApplicationID]) 
		REFERENCES [dbo].[StudentApplications]([StudentApplicationID])
);
GO

