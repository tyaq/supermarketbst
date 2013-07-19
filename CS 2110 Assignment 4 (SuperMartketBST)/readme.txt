Ishtyaq Habib and Omer Syed:
Assignment 4 Problem 4 SuperMarketBST

This program takes a number of customers in a supermarket,
then places them in cashier queue. And stores them in a binary search tree. The customers have an AI which allows them
to leave their current line when another line is shorter.
The cashiers have random speeds of serving customers.
Main method is in super market class

Features:	 All actors in the supermarket have their own AI. 
			Double-ly Linked list BST
			BST can be displayed either horizontally or vertically
			(Horizontally by default, but can be commented out in toString Method in BSTree Class)
			Displays height of tree
			Tree balances

ReadMe:This program is poorly documented. Also, last minute testing proved that 
This program tends to not complete on Macs if the number of customers are greater
than 30, we believe this is because OSX has a check in place preventing large numbers of threads.
On PC this check does not trigger so even 1000 customers works, however the speed
of serving suffers because of the large number of threads and synchronized methods
being used between them. Also if the customers are not served terminate the program and check task manager
for extra running java processes.