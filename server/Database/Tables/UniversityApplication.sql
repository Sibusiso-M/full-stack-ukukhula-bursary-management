CREATE TABLE [dbo].[UniversityApplication]
(
  [ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [UniversityID] INT NOT NULL,
  [Motivation] TEXT NOT NULL,
  [Status] VARCHAR(10) NOT NULL DEFAULT 'Review',
  [RejectionReason] TEXT NOT NULL DEFAULT 'N/A',
)
GO

ALTER TABLE [dbo].[UniversityApplication]
ADD CONSTRAINT [FK_ApplicationUniversityID] FOREIGN KEY([UniversityID]) REFERENCES [dbo].[University]
GO