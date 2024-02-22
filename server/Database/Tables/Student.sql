CREATE TABLE [dbo].[Student]
(
  [ID] INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  [UserID] INT NOT NULL,
  [IDNumber] CHAR(13) NOT NULL,
  [GenderID] INT NOT NULL,
  [EthnicityID] INT NOT NULL,
  [UniversityID] INT NOT NULL,
  [DepartmentID] INT NOT NULL
)
GO

ALTER TABLE [dbo].[Student]
ADD CONSTRAINT [FK_StudentUserID] FOREIGN KEY ([UserID]) REFERENCES [dbo].[User]
GO

ALTER TABLE [dbo].[Student]
ADD CONSTRAINT [FK_StudentGender] FOREIGN KEY ([GenderID]) REFERENCES [dbo].[Gender]
GO

ALTER TABLE [dbo].[Student]
ADD CONSTRAINT [FK_StudentEthnicity] FOREIGN KEY ([EthnicityID]) REFERENCES [dbo].[Ethnicity]
GO

ALTER TABLE [dbo].[Student]
ADD CONSTRAINT [FK_StudentUniversity] FOREIGN KEY ([UniversityID]) REFERENCES [dbo].[University]
GO

ALTER TABLE [dbo].[Student]
ADD CONSTRAINT [FK_StudentDepartment] FOREIGN KEY ([DepartmentID]) REFERENCES [dbo].[Department]
GO