Data Broker Developer Guide
---------------------------

Data broker exposed two API configDB and pullData.

configDB: call by Deployer E6 
* Zipbroker received url ,mimeType and content (JSON format) and set environment variable.

pullData: call by Model connector with script(JSON string) to retrieve data.
* Retrieved input stream and unzip it.
* Iterate on stream as per pattern(script).
* Used protobuf api and convert unzipped filtered input stream to byte array.
* Send response(byte array) to pullData.