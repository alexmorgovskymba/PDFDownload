**INTRO**

This is a basic PDF Mass Download application.

Say you have a bunch of URLs like https://www.pdfsaresocool/pdfcollection/thecoolestcontent-#-goodstuff.pdf where # is a number between 1 and 1000, for example.

You want to download all the PDFs in one swift shot.

Don't laugh, I know most of the code looks like it's from the last decade, but Java is Java.

There are no regex, generics, multi-threading, proper logging, exception handling is at a minimum, and I basically disregarded efficiency concerns, so this is not an epitome of a Big-O masterpiece.

There are no tests, no mocks, no fancy constructs, so if this kind of thing doesn't float your
boat, feel free to hit the X button.  But if you need a simple way to download a bunch of PDF files without having to 
manually click each URL in your browser and save each PDF individually, here you go.

All the formalities which I mentioned, and others, I will take care of later, but for now, let's get moving.

**USAGE**

**Populate the config.properties file**

Example:

url = https://www.pdfsaresocool/pdfcollection/thecoolestcontent-#-goodstuff.pdf
min = 1
max = 1000
numberOfDigits = 4                    

Notes:

**url** is the source URL with the # token for the pdf index

**min** is the starting index

**max** is the ending index

**numberOfDigits** is the number of digits in the index.  Say you need to represent 1 as 0001, then you would set
this to 4

**Clean Build the JAR**

mvn clean package

**Run the program**

java -cp target/pdfdownload-1.0-SNAPSHOT.jar com.agm.pdfdownload.PDFDownload

**NOTES**

The PDF's will be will be saved to the pdf directory.

To remove the pdf directory, run mvn clean.

Every time you run the program, it will re-create the pdf directory, so take note: if you need to back up the pdf directory, do so before running the program.

**END**

Thanks for visiting!
