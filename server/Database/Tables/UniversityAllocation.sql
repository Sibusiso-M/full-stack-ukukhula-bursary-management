CREATE TABLE [dbo].[UniversityAllocation]
(
  [ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [UniversityID] INT NOT NULL,
  [Amount] MONEY NOT NULL,
  [BursaryDetailsID] INT
);
GO

ALTER TABLE [dbo].[UniversityAllocation]
  ADD CONSTRAINT [FK_UniversityAllocationUniversityID]
      FOREIGN KEY ([UniversityID])
      REFERENCES [dbo].[University]
GO

ALTER TABLE [dbo].[UniversityAllocation]
   ADD CONSTRAINT [FK_UniversityAllocationBursaryDetailsID]
        FOREIGN KEY ([BursaryDetailsID])
      REFERENCES [dbo].[BursaryDetails]
GO