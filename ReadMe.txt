To be able to run this code please download java JDK and Junit to run test. refer link below:
https://www.tutorialspoint.com/junit/junit_environment_setup.htm
To run the Coin-Operated Soda Machine code:
	1. Open Command Prompt;
	2. run command: javac COSM.java
	3. run command: java COSM
	4. Try as follow
To run the unit test file
	1. Open Command Prompt
	2. run command: javac -cp junit-4.13.2.jar;. test.java
	3. run command: java -cp junit-4.13.2.jar;hamcrest-core-1.3.jar;. org.junit.runner.JUnitCore test
	4. check command prompt
SourceCode include 2 file COSM.java is main code, test is the unit test for the main code (still I think it not really complete yet). This is how this application work:
When start the program, app will request user to insert money, it'll only accept the value which is exactly the samme with description on the screen.
If user insert right, we will go to next step select products. This machine only provide 3 products only. Input the number in front of the product name to choose.
Next, if the balance insert before is enough, it'll ask for user confirmation and decrease the balance. If user cancel will receive a refund.
After purchase it'll ask would the user like to continue. User can continue to buy more product or cancel to get refund.
Also it is having a promotion for every 3 consecutive purchases of the same product, user will have chance to receive a product for free. The limited budget for the program is 50.000 a day. Try your luck.