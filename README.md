# File Hiding using Java and MySQL

## Project Overview

In this Java project tutorial, I will show you how to create a file hider using Java and MySQL. With this project, you can learn how to implement important security features such as email authentication, login, and encryption.

## Description

This project is used to hide files and also unhide them. The user can enter the file path they want to hide/unhide. Before that, signup and login features are included. While signing up and logging in, the user enters a Gmail ID and receives an OTP in email which needs to be entered for successful login/signup.

The main steps are:

Signup

Login

Enter file path to hide/unhide

Exit


## Technologies Used

Java: Programming language used for development.

JDBC: Java Database Connectivity for connecting and executing queries on the MySQL database.

MySQL: Database used for storing user information and file paths.


## Features


Signup: Users can create an account using their Gmail ID. An OTP is sent to the provided email for verification.

Login: Users can log in to the system using their Gmail ID and OTP.

Hide File: Users can hide a file by providing its file path.

Unhide File: Users can unhide a previously hidden file by providing its file path.

Email Authentication: OTP is sent to the user's Gmail for authentication during signup and login.

Encryption: Files are encrypted when hidden and decrypted when unhidden.
