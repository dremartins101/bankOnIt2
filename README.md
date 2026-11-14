# bankOnit2

```mermaid
classDiagram

class CheckingAccount{
    + CheckingAccount()
    + CheckingACcount(balance)
    + main(): void
    + menu() : String
    + start() : String
    + getBalanceString() : String
    + setBalance(balance) : void 
    + checkBalance() : void
    - getDouble() : double 
    + makeDeposit() : void
    + makeWithdrawal() : void
}

class HasMenu{
    +menu() : String
    + start() : void
}

class SavingAccount{
    - interestRate: double
    + main(")
    + calcInterest: void
    + setInterestRate: void
    + getInterestRate : void
}

class Customer{
    - checking: CheckingACcount
    - savings: SavingAccount
    + main(): void
    + Customer()
    + start() : void
    + menu() : String
    + changePIN(): void
    + getReport(): String
}

class User {
    - userName : String
    - PIN: String

    + login: boolean
    + login(userName, PIN): boolean
    + setUserName(userName): void
    + getUserName(): String
    + setPIN(PIN): void
    + getPIN(): String
    + getReport(): (abstract) String
}

class Bank {
-admin: Admin
- customers: ArrayList

+ Bank()
+main():void
+loadSampleCustomers(): void
+loadCustomers(): void
+saveCustomers(): void
+fullCustomerReport(): void
+addUser(): void
+ applyInterest(): void
+ +loginAsCustomer(): void
+ +menu(): void
+ +start(): void
+ +startAdmin(): void
}

class Admin{
+Admin()
+menu(): String
+getReport(): String
}

SavingAccount --|> CheckingAccount : Extends
CheckingAccount --|> HasMenu : Implements
User --|> HasMenu : Implements
Customer --|> User : Extends

Customer --|> Bank
Admin --|> Bank
```

## Bank

Bank.start:
    display bank menu
    if user chooses admin login:
        ask for admin user and pin
        if login successful:
            start admin menu
        else if user chooses customer login:
            call loginascustomer
        else if user chooses exit:
            exit
Bank.loginAsCustomer():
    ask for customer user and pin
    for each customer
        if user and pin match
            start customer menu
            stop searching
    if no matching customer found
        display login error
Bank.addUser()
    ask for new customer name and pin
    create new customer object
    add customer to customer list
    display confirmation
Bank.applyInterest()
    for each customer in customer list
        calculate interest on savings account
        update savings balance
Bank.loadCustomers()
    try to open customers.dat
        read saved customer list
        store it in the customers arraylist
    if file does not exist
        load sample customers
Bank.saveCustomers()
    open customers.dat for writing
    write customers arraylist to file
    close file
Customer.start()
    while customer has not exited
        display menu
            allow customer to check balance, deposit money, withdraw, change pin
            exit when requested
