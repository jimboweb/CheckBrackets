import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

// [tk] needs two stacks:
// unmatched_opening_brackets
// & unmatched_closing_brackets
// I'll do this later

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
	static boolean debug = false;
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        String output = "";

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        Stack<Bracket> unmatched_closing_brackets = new Stack<Bracket>();
        char lastChar;
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);
            Bracket nextBracket;
            if (next == '(' || next == '[' || next == '{') {
            	nextBracket = new Bracket(next,position);
                opening_brackets_stack.push(nextBracket);

                
                if(debug){
                	System.out.printf("adding %c with position %d to stack%n", next, position);
                }

            
            }

            if (next == ')' || next == ']' || next == '}') {
            	nextBracket = new Bracket(next,position);
            	
            	if(debug){
	            	System.out.printf("Closing bracket %c at %d%n", 
	            			nextBracket.type, nextBracket.position);
            	}
            	
            	if(!opening_brackets_stack.empty()){
            		
            		if(debug){
            			System.out.println("Opening brackets stack isn't empty");
            		}
            		
            		
            		lastChar = opening_brackets_stack.peek().type;
            	} else {
            		lastChar = '\0';
            	}
                if(!opening_brackets_stack.empty() && 
                		opening_brackets_stack.peek().Match(nextBracket.type)){

                	
                	if(debug){
                    	System.out.printf("character %c with position %d matches character %c at position %d in stack%n",
                    			 next, position, opening_brackets_stack.peek().type,
                    			 opening_brackets_stack.peek().position);
                    }

                	
                	opening_brackets_stack.pop();
                } else {
                	//int addPosition = opening_brackets_stack.pop().position + 1;
        			//output = output + addPosition + " ";
                	
                	if(debug){
                		System.out.printf("Adding bracket %c at position %d to opening brackets and unmatched closing brackets%n",
                				next, position);
                	}
        			opening_brackets_stack.push(new Bracket(next,position));
        			unmatched_closing_brackets.push(new Bracket(next, position));
                }
            }
        } 
        
        Stack<Integer> output_stack = new Stack<Integer>();
        while(!opening_brackets_stack.isEmpty()){
        	output_stack.push(opening_brackets_stack.pop().position);
        }
        while(!unmatched_closing_brackets.isEmpty()){
        	output_stack.push(unmatched_closing_brackets.pop().position);
        }
        	
        if(debug){
        	System.out.printf("output_stack:");
	        while(!output_stack.isEmpty()){
	        	System.out.printf("%d ", output_stack.pop() + 1);
	        }
        	
        } else{
	        if(!output_stack.isEmpty()){
	        	System.out.printf("%d", output_stack.pop() + 1);
	        } 
	        else {
	        	output = "Success";
	        }
        }
        // Printing answer, write your code here
        System.out.println(output);
    }
}
