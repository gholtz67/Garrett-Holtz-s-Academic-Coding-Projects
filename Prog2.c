/*
  Programming Assignment 2
  CS231
  Garrett Holtz
*/

/*
  NOTE: Slight issue with outputing, sometimes will have random
		letters and symbols replacing actual letters in output file.
		Run ./a.out fileIn fileOut again (maybe multiple times)
		for a proper output if this occurs.
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

void PigLatiner(char * input, char * output);
int readFile(FILE * fp);

/*
  main(int argc, char * arv[])
  To delcare all the file pointers, run function PigLatiner, 
  and output the list to the given output file.
  argv[] is the input and output files,
  argv[1] = input, argv[2] = output
*/
int main(int argc, char * argv[])
{
	FILE * file_input; //File pointer for input
	FILE * file_output; //File pointer for output
	
	file_input = fopen(argv[1], "r"); //Opens the file to read
	
	int numWords = readFile(file_input);
	
	char inputWords[numWords][23];  //Creates an array of strings equal to the number of words in the document
	char outputWords[numWords][23]; //Creates an array of strings equal to the number of words in the document
									//Holds strings of length up to 20 characters
	
	int pos; //For less declaration redundancy in for loops
	
	//Loop to put all the words found into an array of strings
	for(pos = 0; pos < numWords; pos++)
		fscanf(file_input, "%s", inputWords[pos]);
	
	fclose(file_input); //Closes input file
	
	//Loop to run PigLatiner on all words and put them into outputWords
	for(pos = 0; pos < numWords; pos++)
		PigLatiner(inputWords[pos], outputWords[pos]);
	
	file_output = fopen(argv[2], "w"); //Opens the file to write
	
	//Loop to print to output file
	for(pos = 0; pos < numWords; pos++)
		fprintf(file_output, "%s\n", outputWords[pos]);
	
	fclose(file_output); //Closes output file
	
	return 0;
}

/*
  PigLatiner(char * input, char * output)
  Takes two string arrays and transforms the word according to Pig Latin
  If string starts with a vowel then append 'eh' to end of string
  If string starts without a vowel then move first letter to end and append 'eh'
  If string doesn't start with uppercase then keep output lowercase
*/
void PigLatiner(char * input, char * output)
{
	int pos; //For less declaration redundancy in for loops
	int len = strlen(input);
	
	//Put current word into output, since both are char pointers
	//have to work with individual letters
	for(pos = 0; pos < len; pos++)
		output[pos] = input[pos];
	
	char check = toupper(input[0]); //Gets the first char and forces uppercase
	int checkUpper = 0; //Holder variable when uppercase is checked
	
	//Checks if the first letter is a vowel, forced to uppercase beforehand
	if(check == 'A' || check == 'E' || check == 'I' || check == 'O' || check == 'U')
		strcat(output, "eh"); //Concatenate 'eh' to the end
	else
	{
		if(isupper(output[0]))
			checkUpper = 1;
		output[strlen(output)] = tolower(output[0]); //Sets the last letter as 
										  			 //lowercase first letter
		strcat(output, "eh"); //Concatenate 'eh' to the end
		len = strlen(output);
		
		//Moves all the letters in output to the left by 1
		for(pos = 0; pos < len; pos++)
			output[pos] = output[pos + 1];
			
		//If first letter was uppercase, make the new first letter uppercase	
		if(checkUpper)
			output[0] = toupper(output[0]);
	}
}

/*
  readFile(FILE * fp)
  Reads in the file and returns the number 
  of words found in the text file.
*/
int readFile(FILE * fp)
{
	int holder; //Used to check if loop should continue
	char string[22]; //Used for fscanf to properly read
	int numWords = -1; //Holder for number of words, starts at -1 since
					   //my loop overcounts the number of words by 1
	
	//Goes through all the words found and will count how many there are.
	//Finds the end of the list when holder = -1
	while(holder != -1)
	{
		holder = fscanf(fp, "%s", string);
		numWords++;
	}
	//printf("There are %d words.\n", numWords);
	rewind(fp); //Rewind back to the start of the file to properly read in the words
	
	return numWords;
}
