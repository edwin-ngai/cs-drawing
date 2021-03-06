# cs-drawing
1. ****HOW TO RUN****

IMPORTANT: The program using Java8. Please make sure Java8 has been installed in your environment.

Windows: (assume current folder name is "drawing")
1. gradlew distzip
   above command will build the program, and generate a zip file in ./build/distribution/, which containing a running script.
2. unzip build\distributions\drawing.zip
   unzip the file to where you prefe, above command will unzip the file in current folder.
3. drawing\bin\drawing.bat
   run the batch file.


*nix systems: (assume current folder name is "drawing")
1. ./gradle distzip
   above command will build the program, and generate a zip file in ./build/distribution/, which containing a running script.
2. unzip build\distributions\drawing.zip
   unzip the file to where you prefe, above command will unzip the file in current folder.
3. ./drawing/bin/drawing
   run the batch file
   
 This program contains a gradle executable, so that you can use it to run various gradle tasks. For example, using "gradlew test" in Windows, or "./gradle test" in *nix to run test cases and check the test reports in "build/reports/tests"


2. ****DEPENDENCIES****
This program is built using Gradle, and depends on several external libraries that have been placed in the lib folder. Below are their details of usage and dependency.
2.1 Utilities library:
org.apache.commons:commons-lang3:3.5
2.2 Logging framework:
ch.qos.logback:logback-classic:1.1.11
     +--- ch.qos.logback:logback-core:1.1.11
     \--- org.slf4j:slf4j-api:1.7.22
2.3 Testing framework:
junit:junit:4.12
     \--- org.hamcrest:hamcrest-core:1.3


3. ****DESIGN CONSIDERATION****
3.1 Extensibility
This is a simple console version of a drawing program. However, the requirement mentions extensibility, which is the most important design consideration of this implementation. Especially, the design considers extensibility in below areas:
* Shape extensibility: easy to support more shapes
* Command extensibility: easy to support more commands
* Rendering extensibility: easy to support other renderer rather than just console

The io.drawing.shape package is designed to support shape extensibility. Below is its class diagram
**************************Class Diagram (io.drawing.shape)*****************************************
                                                |---------HorizontalLine
                                                |
                                |----Line<|-----| 
                                |               |
 Canvas------------->Shape <|---|               |----------VerticalLine
        |               |       |
        |               |	|----Rectangle
        |               |
        |		V				 
        |----------->Point
******************************************************************************************************

The io.drawing.command package is designed to support command extensibility. Below is its class diagram
**************************Class Diagram (io.drawing.command)*****************************************
 CommandContext  
        |       |---Help                |----CreateCanvas				
        |       |                       |				
        |       |---Quit                |----DrawLine
        v       |                       |              
 Command<|------|---RenderingCommand<|--|----DrawRectangle
                                        |
                                        |----BucketFill
******************************************************************************************************			

The io.drawing.renderer package is design to support rendering extensibility. Below is its class diagram
**************************Class Diagram (io.drawing.command)*****************************************
 Renderer<|-----StreamRender
******************************************************************************************************

Also, In addition to above major extensibility support, a few configuration is also used to customized various aspects of this program, such as the canvas's maximum size, background color and foreground color.

3.2 Robustness
The provided requirement mentions little on the program's robustness and exceptional cases. For example, what to do if a given point is outside the canvas? and what to do if the given lower right point of a rectangle is same with the given upper left point? This implementation puts much attention to the program robustness, and carefully validates user input to avoid runtime errors.

3.3 Performance
The requirement requires to provide a bucket fill operation on canvas. Considering there is no limit on the canvas size, a default flood fill algorithm that uses recursive implementation might cause stack overflow. This program implements a optimised forest fire algorithm to achieve bucket fill.

3.4 Test Driven Development
The development of this program is driven by test, to achieve higher productivity in a limit time period. At the end of the development, more test cases are added to improve its test coverage.
