Data Broker User Guide
----------------------

configDB: Input data url , mimeType and content in format 
  http://localhost:8080/configDB?jsonUrl={"url":"http://www.imageemotion.org/testImages_artphoto.zip"}&jsonMapping={"MIME_TYPE":"mime_type","CONTENT":"content"}&jsonPosition={"MIME_TYPE":"1", "CONTENT":"2"}
pullData: Input data jsonScript using POST method
  http://localhost:8080/pullData
  e.g., Body
  {"pattern":"exci*"}