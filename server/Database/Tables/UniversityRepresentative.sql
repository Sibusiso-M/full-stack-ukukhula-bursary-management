CREATE TABLE [dbo].[UniversityRepresentative]
(
  [ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [UserID] INT NOT NULL,
  [UniversityID] INT NOT NULL,
  [DepartmentID] INT NOT NULL,
)
GO

ALTER TABLE [dbo].[UniversityRepresentative]
    ADD  CONSTRAINT [FK_UniversityRepresentativeUser]
      FOREIGN KEY ([UserID])
      REFERENCES [dbo].[User]
GO

ALTER TABLE [dbo].[UniversityRepresentative]
  ADD CONSTRAINT [FK_UniversityRepresentativeDepartment] 
      FOREIGN KEY ([DepartmentID]) 
      REFERENCES [dbo].[Department]
GO

ALTER TABLE [dbo].[UniversityRepresentative]
    ADD CONSTRAINT [FK_UniversityRepresentativeUniversity]
      FOREIGN KEY ([UniversityID])
      REFERENCES [dbo].[University]
GO
