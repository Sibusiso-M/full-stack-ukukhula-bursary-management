CREATE TABLE [dbo].[StudentApplication]
(
  [ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [StudentID] INT NOT NULL,
  [Motivation] TEXT NOT NULL,
  [RejectionReason] TEXT NOT NULL DEFAULT 'N/A',
  [BursaryAmount] MONEY NOT NULL,
  [Status] VARCHAR(10) NOT NULL DEFAULT 'Review',
  [Date] DATE NOT NULL DEFAULT GETDATE()
)
GO


ALTER TABLE [dbo].[StudentApplication]
  ADD CONSTRAINT [FK_StudentApplicationStudentID] FOREIGN KEY([StudentID]) REFERENCES [dbo].[Student]
GO