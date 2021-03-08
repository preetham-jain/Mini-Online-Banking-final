#Mini Online-Banking System


# Introduction
	The goal of this project is to design a simple banking application that provides some basic functionalities of banking such as creating accounts, withdraw from
  accounts, deposit to accounts, transfer to other accounts along with login and registration. We have used spring boot framework for backend services and vue.js
  for frontend.

# Problem Statement

User can add transaction (withdrawal or deposit)
Display period wise transaction details / bank statement
Add users for payment within the same bank.
Transfer money to any user already added within the same bank.

# Requirements

## Hardware Requirements:

* MacBook Pro (Retina, 15-inch, Mid 2015)
* Processor 2.2 GHz Quad-Core Intel Core i7

## Software Requirements:

## Frontend:
* Npm version: '7.6.0'
* Node version: '15.10.0'
* v8 version: '8.6.395.17-node.25'
* uv version: '1.41.0',
* zlib version: '1.2.11'

## Backend:
* Spring Boot version: 2.8 with Java 8.0 version
* PostreSQL version: 13.2


# Technology Used
* HTML5
* CSS
* VUE JS
* Spring Boot
* PostgreSQL for database

# Features

* Registration page for registering new users into the Bank 
* Login page for users to access their accounts
* Welcome page is the landing page where the user can create account
* Different pages for all the functionalities like deposit, withdrawal, transfer, account details and transaction details.

# Database schema
Tables
![alt schema](https://github.com/preetham-jain/Mini-Online-Banking-final/blob/main/Screenshot%202021-03-08%20at%2012.11.22%20PM.png)

## Accounts 
* UserId(Foreign key)
* Id(Primary key)
* Account No
* Account Balance

## Transaction
* UserId(Foreign key)
* Id(Primary key)
* Date
* Type{withdrawal,deposit,transfer)
* Status
* Recipient Account No
* Amount

## User
* User Id(Primary key)
* First Name
* Last Name
* Email
* Password
* Address
* Pin

# Api     
* Register microservice-

   Api endpoint-/register
   
         1. Create user-Post api
         
              Api endpoint-/createuser
              
         2. Login-Post api
         
             Api endpoint-/login
             
* Update microservice

    Api endpoint-/update
    
         1. GetAccountDetails-Get api
         
             Api endpoint-/GetAccountDetails
              
         2. Withdraw-Put api
         
             Api endpoint-/withdraw
             
         3. deposit-Put api
         
             Api endpoint-/deposit
             
         4. transfer-Put api
         
             Api endpoint-/transfer
              
         5. getPastTransaction-Get api
         
             Api endpoint-/getPastTransaction
         
         6. getTransactionType-Post api
         
             Api endpoint-/getTransactionType
            
         7. CreateAccount-Post api
          
            Api endpoint-/CreateAccount
      
# Steps to run the project

* Clone the repository
* For frontend in vs code terminal use commands: 
  * npm install - for downloading all dependencies
  * npm run serve - for starting the server
* For backend in IntelliJ IDEA use following commands:
  * mvn clean install - for installing maven dependencies for all the services
  * configure the database in application properties
  * build and run
  ## Important
  * run the transaction service and login service in two different ports
  * re route the api gateway calls to those services using the ports
  


      
          

      









