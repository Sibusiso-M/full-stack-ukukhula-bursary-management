CREATE TABLE [dbo].[StudentDocuments]
(
  [ID] INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
  [ApplicationID] INT NOT NULL,
  [IdCopy] VARBINARY(MAX) NOT NULL,
  [AcademicTranscript] VARBINARY(MAX) NOT NULL,
  [CurriculumVitae] VARBINARY(MAX) NOT NULL,
)
GO

ALTER TABLE [dbo].[StudentDocuments]
  ADD CONSTRAINT [FK_StudentDocumentsApplication]
      FOREIGN KEY ([ApplicationID])
      REFERENCES [dbo].[StudentDocuments]
GO