import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class simpLex{
	private static String[] keywords= {"and", "bool", "const", "do", "else", "false", "if", "int", "main", "not", "or", "real", "return", "string", "then", "true", "var", "void", "while"};

	public static void main(String[] args) throws FileNotFoundException {


		String filename = new String(args[0]);
		String input_string = getToken(filename);
		System.out.println(input_string);
		char[] charArray = input_string.toCharArray();

		tokenGeneratorAnalyser(charArray);
		System.out.println("");
	}



	private static void tokenGeneratorAnalyser(char[] charArray) {
		for (int counter = 0; counter < charArray.length; counter++) {
			boolean error = false;
			//			System.out.println(counter);
			String dummy = new String();
//			if (charArray[counter] == ' ') {
//				counter = counter +1;
//			}
			if (isAlpha(charArray[counter])) {
				do {
					dummy += charArray[counter];
					counter = counter +1;

					if (counter >= charArray.length) {
						break;
					}
				} while (isAlphaDigitUnderscore(charArray[counter]));
				counter = counter -1;
				isKeyword(counter, dummy);
			}

			else if(charArray[counter] == '"'){

				// still left
				//				System.out.println("in a string!!");
				//				dummy += charArray[counter];
				counter = counter +1;
				while (charArray[counter] != '"') {
					dummy += charArray[counter];
					counter++;
					if (counter >= charArray.length) {
						//						System.out.println("Error!");
						error = true;
						break;
					}
				}
				if (error != true){
					System.out.println(dummy    + "\t\t.\t.\t.\t.\t.\t.\t.\t String Literal");
				}
				else{
					System.out.print(dummy    + "\t\t.\t.\t.\t.\t.\t.\t.\t error!\n");
				}

			}


			else if (isDigit(charArray[counter])) {
				//				System.out.println("a number!");
				//				dummy += charArray[counter];
				while(isDigit(charArray[counter])){
					dummy += charArray[counter];
					if (++counter >= charArray.length) {
						counter = counter -1;
						break;
					}
				}
				if (isNumberCheck(dummy) == true){
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t number");
				}
				else {
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t error");
				}
				//

			}

			else if (charArray[counter] == '/') {
				dummy = "/";
				int dummy_counter = counter +1;
				int depth = 1;
				if (dummy_counter < charArray.length){
					if (charArray[dummy_counter] == '*') {
						dummy += "*";
						counter = dummy_counter +1;
						if (counter < charArray.length) {
							while (counter < charArray.length) {
								dummy += charArray[counter];
								if (charArray[counter] == '*' && charArray[counter-1] =='/') {
									depth +=1;
								}

								if (charArray[counter] == '/' && charArray[counter-1] =='*') {
//									System.out.println("out of comment! level:" + depth);
									depth = depth -1;
									if (depth < 1){
										System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t /* comment");
										break;										
									}
								}
								counter = counter +1;
								//							System.out.println(depth);
							}
							if (depth != 0) {
								System.out.println("\t\t.\t.\t.\t.\t.\t.\t.\t  error! comment not closed");
							}
						}
						else {
							System.out.println("\t\t.\t.\t.\t.\t.\t.\t.\t error!, comment not closed");
						}
					}
					else if (charArray[dummy_counter] == '/') {
//						dummy += "/";
//						System.out.println("print in comment //");
						counter = dummy_counter;
						while (charArray[counter] != '\n') {
							dummy += charArray[counter];
							counter = counter + 1;
							if (counter >= charArray.length) {
								counter = counter -1;
								break;
							}
						}
						System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t //comment");
					}
					else{
						System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Division operator");
					}
				}
				else{
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Division operator");
				}
			}



			else {
				//				System.out.println(counter);
				dummy = "";
				dummy += charArray[counter];
				//				System.out.println(dummy  + "     not a id or key");


				switch (charArray[counter]) {
				case '(':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Left parenthesis");
					break;
				case ')':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Right parenthesis");
					break;
//				case ':':
//					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Colon");
//					break;
				case ';':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Semi-Colon");
					break;					
				case '{':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Left Curly Braces");
					break;
				case '}':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Right Curly Braces");
					break;
				case '+':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t addition operator");
					break;
				case '-':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Subtraction operator");
					break;
				case '*':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Multiplication operator");
					break;
//				case '/':
//					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Division operator");
//					break;
				case '%':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Modulus operator");
					break;
				case ',':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t comma operator");
					break;
				case '.':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t dot operator");
					break;
				case '?':
					System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t question mark operator");
					break;

				case '!':
//					System.out.println("counter at !" + counter);
					if ((counter+1) < charArray.length){
//						counter += 1;
//						System.out.println("counter later"+ counter);
						if (charArray[counter +1] == '=') {
							dummy += charArray[counter + 1];
							System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t not equal to operator");
						}
						else{
							System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t bang operator");
						}
					}
					else{
						System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t bang operator");
					}
					break;	
				case '>':
//					System.out.println("counter at !" + counter);
					if ((counter+1) < charArray.length){
//						counter += 1;
//						System.out.println("counter later"+ counter);
						if (charArray[counter +1] == '=') {
							dummy += charArray[counter + 1];
							System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t greater than equal to operator");
						}
						else{
							System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t greater than operator");
						}
					}
					else{
						System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t greater than operator");
					}
					break;	
				case '<':
//					System.out.println("counter at !" + counter);
					if ((counter+1) < charArray.length){
//						counter += 1;
//						System.out.println("counter later"+ counter);
						if (charArray[counter +1] == '=') {
							dummy += charArray[counter + 1];
							System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t less than equal to operator");
						}
						else{
							System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t less than operator");
						}
					}
					else{
						System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t less than operator");
					}
					break;				
				case ':':
//					System.out.println("counter at !" + counter);
					if ((counter+1) < charArray.length){
//						counter += 1;
//						System.out.println("counter later"+ counter);
						if (charArray[counter +1] == '=') {
							dummy += charArray[counter + 1];
							System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t colon equal to operator");
						}
						else{
							System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t colon");
						}
					}
					else{
						System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t colon");
					}
					break;	
				
				
				
				default:
//					System.out.println("Some other character.");
					break;
				}
			}

		}
	}



	private static boolean isNumberCheck(String dummy) {
		char[] numberArray = dummy.toCharArray();
		if (numberArray[0] == '.' || numberArray[numberArray.length-1] =='.'){
			//			System.out.println("error!");
			return false;
		}
		else {
			int dot_count = 0;
			for (int i = 0; i < numberArray.length; i++) {
				if ( numberArray[0] == '.'){
					dot_count =dot_count +1;
				}
			}
			if (dot_count >1){
				return false;
			}
		}
		return true;
	}



	private static boolean isKeyword(int counter, String dummy) {
		for(String s: keywords){
			if(s.equalsIgnoreCase(dummy)){
				System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t Keywords ");
				return true;
			}
		}
		System.out.println(dummy + "\t\t.\t.\t.\t.\t.\t.\t.\t identifier ");
		return false;
	}



	private static String getToken(String name) throws FileNotFoundException {
		String dummy = new String();
		Scanner s = null;
		try {
			s = new Scanner(new BufferedReader(new FileReader(name)));
			while (s.hasNextLine()) {
				dummy = dummy + '\n' + s.nextLine();
				//                System.out.println(s.next());
			}
			return dummy;
			//            s.useDelimiter(" ");
		} finally {
			if (s != null) {
				s.close();
			}
		}
		//        System.out.println(dummy);
	}

	private static boolean isAlpha(char char_sub) {
		String name = Character.toString(char_sub);
		return name.matches("[a-zA-Z]+");
	}

	private static boolean isAlphaDigitUnderscore(char char_sub) {
		String name = Character.toString(char_sub);
		return name.matches("[a-zA-Z0-9_]+");
	}


	private static boolean isDigit(char char_sub) {
		String name = Character.toString(char_sub);
		return name.matches("[0-9.]+");
	}
}