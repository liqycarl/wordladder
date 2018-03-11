package se_hw;

import java.io.*;
import java.util.*;

public class Wordladder 
{

	public static void main(String[] args) 
	{
		List<String> wordList= new ArrayList<>();
		try 
		{
            String pathname = "src\\se_hw\\EnglishWords.txt";
            File filename = new File(pathname);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            while (line != null)
            { 
                line = br.readLine();
                wordList.add(line);
            }  
		}
		catch (Exception e) 
		{  
            e.printStackTrace();  
		}
		

		
		Scanner sc=new Scanner(System.in);
		System.out.println("Word#1(or Enter to quit):");
		String Word1=sc.nextLine();
		if(Word1.length()==0)
		{
			System.out.println("Have a nice day!");
			System.exit(0);
		}
		String beginWord=Word1;
		System.out.println("Word#2(or Enter to quit):");
		String Word2=sc.nextLine();
		if(Word2.length()==0)
		{
			System.out.println("Have a nice day!");
			System.exit(0);
		}
		String endWord=Word2;
		
		if(beginWord.length()!=endWord.length())
		{
			System.out.println("The two words must be the same length.");
			System.exit(0);
		}
		if(endWord.equals(beginWord))
		{
			System.out.println("The two words must be different.");
			System.exit(0);
		}
		
		
		
		
		Queue<Stack> ladder = new LinkedList<Stack>();
		Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Stack<String> temp = new Stack<String>();
        Stack<String> beginStack= new Stack<String>();
        beginStack.push(beginWord);
		ladder.add(beginStack);
        while(!ladder.isEmpty())
        {
        	while(!temp.empty())
        	{
        		temp.pop();
        	}
        	temp=ladder.peek();
        	ladder.poll();
        	
        	for(int i=0;i<temp.peek().length();i++)
        	{
        		char[] chars = temp.peek().toCharArray();
        		for(char c='a';c<='z';c++)
        		{
        			if (chars[i]==(char)c)
        			{
        				continue;
        			}
        			chars[i] = (char)c;
        			String newWord=new String(chars);
        			if(visited.contains(newWord))
        			{
        				continue;
        			}
        			if(newWord.equals(endWord))
        			{
        				System.out.println("A ladder from " + endWord + " back to "+ beginWord + ":");
        				System.out.print(endWord + " ");
        				while(!temp.empty())
        				{
        					System.out.print(temp.peek() + " ");
        					temp.pop();
        				}
        				System.exit(0);
        			}
        			if(wordSet.contains(newWord))
        			{
        				temp.push(newWord);
        				Stack<String> tt = (Stack<String>)temp.clone();
        				ladder.add(tt);
        				temp.pop();
        				visited.add(newWord);
        			}
        		}      	
            }
        }
        System.out.println("No word ladder found from "+ endWord+ " back to " + beginWord + ". ");
    	System.exit(0);
	}
}
