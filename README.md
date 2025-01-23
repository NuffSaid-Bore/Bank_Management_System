# Bank Management System

[Link Preview](https://bank-management-system-v0hi.onrender.com)

## Introduction

The **Spring Bank Management System** is a web-based application that allows users to manage their bank accounts efficiently. Users can create accounts, manage multiple account types, perform transfers between accounts, and make withdrawals. The system uses modern web technologies to provide a seamless, secure, and responsive user experience.

## Technologies Used

- **HTML**: For structuring the frontend user interface.
- **CSS**: For styling the web pages and creating a responsive, user-friendly design.
- **JavaScript**: For DOM manipulation, enabling dynamic and interactive elements on the webpage (e.g., form validation, updating balances in real time, showing transaction histories).
- **Java**: The core programming language used to implement the backend functionality of the system.
- **SQL**: For managing the database and storing user data, transactions, and account information.
- **Spring Boot**: A framework for building and deploying the backend application, enabling REST APIs, database integration, and overall application structure.
- **JWT (JSON Web Tokens)**: For **token-based authentication** to securely manage user logins and API access.
- **WebSockets**: For **real-time updates**, ensuring that users can see their account balances and transaction histories updated immediately.

## Features

1. **Account Creation**  
   Users can create multiple accounts, but only one account per type (e.g., Checking, Savings).
   
3. **Multiple Account Types**  
   Users can maintain different account types, but cannot create duplicate accounts of the same type (e.g., two Checking accounts).

4. **Account Management**  
   Users can view their accounts, check balances, and access transaction history.

5. **Transfers Between Accounts**  
   Users can transfer funds from one account to another within their profile. The system ensures that there are sufficient funds for transfers.

6. **Withdrawals**  
   Users can withdraw funds from their accounts, as long as the available balance is sufficient.

7. **Transaction History**  
   Users can view a history of all transactions made for each of their accounts.

8. **Add Descriptions for Transactions**  
   Users can add a description (e.g., "Rent payment", "Savings deposit", "Transfer to personal savings") when making a transfer or withdrawal. This helps users track the purpose or reason for each transaction.

9. **Token-based Authentication**  
   The system uses **JWT (JSON Web Tokens)** to authenticate users securely. This allows users to log in once, receive a token, and use it to make authenticated requests to the backend without having to re-enter credentials.

10. **Real-time Updates**  
   The system implements **WebSockets** to enable real-time updates. When a user makes a transfer, withdrawal, or receives an update, the page automatically reflects the change (e.g., updated account balance) without requiring a page reload.


## System Requirements

- **Java 8 or higher**
- **Spring Boot** for backend services
- **MySQL** or any SQL-based database for storing account and transaction data
- **HTML5**, **CSS3**, and **JavaScript** for frontend development
- **JWT** for token-based authentication
- **WebSockets** for real-time updates
- **Browser**: Any modern web browser (e.g., Google Chrome, Firefox, Edge)

## How to Use the System

### 1. **Creating an Account**

Users can create a new account by filling out a form with their personal details (e.g., name, account type, initial deposit). The system will automatically save the details to the database.

Example:  
- Navigate to the Dashboad page.
- Enter your name, select an account type (Checking or Savings).
- Click "Create Account" to finalize the process.

### 2. **Viewing Account Details**

Once logged in, users can view their account details, including the type of account, current balance, and recent transactions. This page will display a list of accounts associated with the user in different cards styles.

Example:  
- Go to the Dashboard page to view your accounts and balances.

### 3. **Logging In and Authentication**

Upon registration, users can log in using their credentials (username and password). After successful registration, a **JWT token** will be issued, which the user can use to make authenticated API requests.
 

### 4. **Transferring Funds Between Accounts**

Users can transfer funds between their own accounts. The system verifies that the source account has sufficient funds before completing the transfer. In addition, users can add a **description** for the transfer to provide context (e.g., "Payment for rent").

Example:  
- On the "Transfer" page, select your source and destination accounts.
- Enter the amount you wish to transfer.
- Add a description (optional): "Payment for rent".
- Confirm the transfer to complete the action.

### 5. **Withdrawing Funds**

Users can make withdrawals from their accounts, subject to available balance. The system ensures that the withdrawal does not exceed the account balance. Users can also add a description for the withdrawal (e.g., "ATM withdrawal", "Shopping expenses").

Example:  
- Navigate to the "Withdraw" page.
- Select the account and specify the withdrawal amount.
- Add a description (optional): "ATM withdrawal".
- Confirm the withdrawal to update the account balance.

### 6. **Viewing Transaction History**

Each account tracks all deposits, withdrawals, and transfers made. Users can view their transaction history for each account, which includes the description of each transaction.

Example:  
- On the "Transaction History" page, view all related transactions, including descriptions like "Transfer to Savings" or "Rent payment".

### 7. **Real-time Updates**

Whenever a user performs a transfer, withdrawal, or account balance update, the system will automatically refresh the page to show the updated balance and transaction history, without needing to reload the page.

Example:  
- After making a transfer, the new balance will be shown instantly without requiring the user to manually refresh the page.

## Backend Architecture

- **Spring Boot** is used to implement REST APIs to handle operations such as creating accounts, making transfers, and processing withdrawals.
- **Spring Security** is integrated to manage **JWT token-based authentication**, ensuring secure user access to the system.
- **WebSockets** are used to provide real-time updates to the frontend, ensuring changes to balances or transactions are reflected immediately.
- **Spring Data JPA** is used to interact with the SQL database.
- **Security**: Basic authentication is implemented with **JWT tokens** for secure API access and user data protection.

## Frontend Architecture

- **HTML** is used to structure the web pages, displaying forms for account creation, account management, transfers, and withdrawals.
- **CSS** is used to style the pages, ensuring a responsive and user-friendly design that works across desktop and mobile devices.
- **JavaScript** is used for DOM manipulation, making the web pages dynamic. Key features include:
  - **Form validation**: Ensuring that users enter valid data before submitting forms (e.g., checking if the account type already exists, verifying amounts for transfers and withdrawals).
  - **Real-time balance updates**: Using **WebSockets**, account balances are updated instantly without refreshing the page.
  - **Displaying transactions**: Using JavaScript to dynamically display transaction histories with their descriptions after completing operations like deposits, transfers, or withdrawals.

## Example Flow of Operations

1. **User Registration**: A user enters their details and selects an account type. The system saves this information to the database.
2. **Account Management**: The user logs in, views their accounts, and selects an account to view details.
3. **Making a Transfer**: The user selects two accounts (source and destination), enters the transfer amount, adds an optional description (e.g., "Payment for groceries"), and the system verifies that the transfer is possible before completing it.
4. **Withdrawal**: The user selects an account, enters the amount to withdraw, adds an optional description (e.g., "ATM withdrawal"), and JavaScript ensures the withdrawal amount does not exceed the account balance before submitting the request.
5. **Transaction History**: JavaScript is used to dynamically update the transaction history on the user interface, displaying new entries with their descriptions (e.g., "Transfer to Savings" or "Rent payment") as transactions occur.
6. **Real-time Updates**: Once a user performs any transaction, the system will instantly update the balance and transaction history displayed on the page via **WebSockets**.

## Database Schema

- **User Table**: Stores user information such as name, email, password, etc.
- **Account Table**: Stores account details, including user ID, account type, and balance.
- **Transaction Table**: Tracks all transactions (withdrawals, deposits, transfers) associated with the accounts, including a **description** for each transaction.

## Limitations

- **No external transactions**: The system only supports internal transfers between the user’s own accounts. External transfers to other banks are not supported.
- **Real-time updates only within user’s accounts**: Updates are real-time for user accounts but not for transactions involving external services or banks.

## Future Enhancements

1. **Advanced Security Features**: Implement multi-factor authentication (MFA) to further secure user logins.
2. **Account Types**: Add additional account types, such as Joint Accounts and Investment Accounts.
3. **Interest Calculation**: Introduce automatic interest calculations for Savings accounts.
4. **External Transfers**: Implement functionality to allow transfers between users' accounts at different banks.
5. **Mobile App**: Develop a mobile application for managing bank accounts on-the-go.
