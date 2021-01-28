//Programming Assignment 1
//CS231
//Garrett Holtz

#include <stdio.h>
#include <string.h>

int add(int, int); //Delcare the add function
int subtract(int, int); //Delcare the subtract function
int divide(int, int); //Declare the divide function
int multiply(int, int); //Declare the multiply function

int main()
{
	char op[5]; //Operator variable, will store the chosen operator
	int Fval; //First value, will store the first chosen value
	int Lval; //Last value, will store the last chosen value
	
	printf("Welcome to the very complicated calculator. For all input don't dont use quotation marks please.\n");
	printf("When entering your operator please use '/' for division, '*' for multiplication, '-' for subtraction, and '+' for addition\n");
	printf("Whenever you wish to stop, enter 'q'\n");
	
	//While the operator variable doesn't read q for quit keep repeating
	while(strcmp(op, "q"))
	{
		printf("Enter an Operator: "); //Obtain the operator
		scanf("%s", op); //Assign the operator to op
		
		//printf("Operator: %s\n", op); //Tester
		
		//If the user entered q to quit then inform that the next values mean nothing then Prog1 will stop
		if (!strcmp(op, "q"))
			printf("Please enter the next 2 values to exit, they do not matter\n");
		
		printf("Enter the first value: "); //Obtain the first value
		scanf("%d", &Fval); //Assigne the first value to Fval
		
		//printf("First Value: %d\n", Fval); //Tester
	
		printf("Enter the second value: "); //Obtain the last value
		scanf("%d", &Lval); //Assign the last value to Lval
	
		//printf("Last Value: %d\n", Lval); //Tester
		
		//Check if the user is attempting to divide by 0
		if (!strcmp(op, "/") && Lval == 0)
		{
			printf("You can't divide by 0\n");
			printf("Please re-enter your operator and values without attemting to divide by 0\n");
		}
		//If not trying to divide by 0, start the calculations.
		else
		{
			if(!strcmp(op, "+")) //Add the values 
				add(Fval, Lval);
			else if(!strcmp(op, "-")) //Subtract the values
				subtract(Fval, Lval);
			else if(!strcmp(op, "*")) //Multiply the values
				multiply(Fval, Lval);
			else if(!strcmp(op, "/")) //Divide the values
				divide(Fval, Lval);
		}
	}
	return 0;
}

//Add the two inputted values and print the answer
int add(int Fval, int Lval)
{
	printf("%d + %d = %d\n", Fval, Lval, Fval + Lval);
	return 0;
}

//Subtract the two inputted values and print the answer
int subtract(int Fval, int Lval)
{
	printf("%d - %d = %d\n", Fval, Lval, Fval - Lval);
	return 0;
}

//Multiply the two inputted values and print the answer
int multiply(int Fval, int Lval)
{
	printf("%d * %d = %d\n", Fval, Lval, Fval * Lval);
	return 0;
}

//Divide the two inputted values and print the answer
//Rounded down
int divide(int Fval, int Lval)
{
	printf("%d / %d = %d\n", Fval, Lval, Fval / Lval);
	return 0;
}
