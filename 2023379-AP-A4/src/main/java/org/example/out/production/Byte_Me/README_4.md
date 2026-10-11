# ASSIGNMENT - 4
# BYTE ME


3.1
Implemented GUI using java Swing to create
and interface for both customer and admin
t uses a JFrame as the main window and CardLayout to switch between different screens, such as the menu and pending orders. 
Data is displayed in JTable components

users can only view item and admin can view item and pending order 
of the customer  menu data and pending orders are loaded dynamically from files using input stream

3.2
used Java I/O streams to manage customers credentials
users log in their details are saved to a file called user_data.txt

LoginRegister: This methods checks if a customer's credentials already exist in the file

FindUser_Login: This method searches the file for a customer with the provided credentials

Update_details: Updates if the customer become vip 

3.3
I have implemented 4 tests
testAddingUnavailableItemCart():

checks if the system prevents customers from adding out-of-stock items to their cart.

testForUpdateItem():

ensures that the system updates the quantity of an item correctly in the cart.

testAddItem_CheckAmount():

test verifies that the correct total price is calculated when adding an item to the cart.

testForNegativeQuantities():

test checks if the system prevents negative quantities in the cart.
