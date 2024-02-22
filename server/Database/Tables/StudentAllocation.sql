CREATE TABLE [dbo].[StudentAllocation]
(
  [ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [StudentID] INT NOT NULL,
  [Amount] MONEY NOT NULL,
  [Year] INT NOT NULL DEFAULT YEAR(GETDATE()),
);
GO

ALTER TABLE [dbo].[StudentAllocation]
  ADD CONSTRAINT [FK_StudentAllocationStudentID]
      FOREIGN KEY ([StudentID])
      REFERENCES [dbo].[Student]
GO