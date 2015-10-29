For our project, we decided to implement a banking system. We plan to support Savings, Checking and several types of loans within the application. We will have two types of User Interfaces currently planned: one is an ATM and the other is a web based banking interface. The user side of the application will invoke RMI to call methods on the Server.

All the user data (names, account numbers, etc) will be stored in a MySQL database. This database will be accessible by the Server side of our program. There will be multiple clients connecting simultaneously to the server over a network connection. The server will parse and analyze user input, query the database, and return output back to the user.

Advanced functionality at the ATM will consist of the ability of users with various loans to make payments to those loans, monitor the existing balance on the loan, and withdraw cash from existing credit lines provided through said loans. From the web UI the user will be able to view account and loan transaction histories and transfer funds. Authentication at the ATM will consist of an account or card number and PIN, and also at the web page (username and password).

We have chosen to use a MySQL database because it is free, and can handle synchronicity by itself (very important for any database, but especially for a bank).

The bulk of the 'Business' portion of our application will be built using Java and RMI to facilitate remote access. The web interface will use Java Server Pages and probably PHP.