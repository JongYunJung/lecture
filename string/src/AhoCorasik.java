// Aho-Corasik �����ϱ�
import java.util.*;

public class AhoCorasik {

	static final int MAXS = 500; // �ִ� ���¼� = ���ϵ��� ������ ��
	static final int MAXC = 26;  // ���������� �ִ� ũ��

	static int[] out = new int[MAXS];	// ��� �Լ�
	static int[] f = new int[MAXS]; 	// ����(failure) �Լ�
	static int[][] g = new int[MAXS][MAXC + 1]; // goto �Լ�(Ʈ����): g[���¹�ȣ][����]

	static int buildMatchingMachine(String[] arr, int k) // ���¼� ��ȯ
	{
	    int states = 1;

	    // goto �Լ� ����, k: ���� ��
	    for (int i = 0; i < k; ++i)
	    {
	        String word = arr[i];
	        int currentState = 0;

	        for (int j = 0; j < word.length(); ++j)
	        {
	            int ch = word.charAt(j) - 'a';

	            if (g[currentState][ch] == -1)
	                g[currentState][ch] = states++;

	            currentState = g[currentState][ch];
	        }
	        out[currentState] |= (1 << i);    
	    }
	    
	    for (int ch = 0; ch < MAXC; ++ch)
	        if (g[0][ch] == -1)
	            g[0][ch] = 0;


	    Queue<Integer> Q = new LinkedList<Integer>();      
	    for (int ch = 0; ch < MAXC; ++ch)  
	    {
	        if (g[0][ch] != 0)
	        {
	            f[g[0][ch]] = 0;
	            Q.add(g[0][ch]);
	        }
	    }
	    // Now queue has states 1 and 3
	    while (!Q.isEmpty())
	    {
	        int state = Q.remove();

	        for (int ch = 0; ch < MAXC; ++ch)
	        {
	        
	            if (g[state][ch] != -1)
	            {
	                int failure = f[state]; // Find failure state of removed state

	                while (g[failure][ch] == -1)
	                      failure = f[failure];

	                failure = g[failure][ch];
	                f[g[state][ch]] = failure;

	                // ��� �Լ��� ��� �߰�
	                out[g[state][ch]] |= out[failure];

	                Q.add(g[state][ch]);
	            }
	        }
	    }
	    return states;
	}
	static int findNextState(int currentState, char nextInput)
	{
	    int answer = currentState;
	    int ch = nextInput - 'a';
	    
	    while (g[answer][ch] == -1) // �����Լ� �� ����
	        answer = f[answer];

	    return g[answer][ch];
	}
	
	static void searchWords(String[] arr, int k, String text)
	{

	    buildMatchingMachine(arr, k);
	    int currentState = 0;       // �ʱ� ����

	    for (int i = 0; i < text.length(); ++i)
	    {
	        currentState = findNextState(currentState, text.charAt(i));
	        
	        if (out[currentState] == 0)  // ��Ī ���� ��, ���� ���ڷ�
	             continue;

	        for (int j = 0; j < k; ++j)
	        {
	            if ((out[currentState] & (1 << j)) != 0)
	            {
	            	System.out.println("���ڿ� " + arr[j]);
	                System.out.printf(" %d ���� %d ���� ����\n" , i - arr[j].length() + 1, i);
	            }
	        }
	    }
	}

	public static void main(String[] args)
	{
	    String[] arr = new String[]{"he", "she", "hers", "his"};
	    String text = "ahishers";
	    
	    System.out.println("���ϵ�> ");
	    int len = 0;
	    for(String str: arr)
	    {
	    	System.out.print(str + " ");
	    	len += str.length();
	    }
	    System.out.println("\n�ؽ�Ʈ> " + text + "\n------------------");
	    for(int i = 0; i <= len; i++)
	    	for(int j = 0; j < MAXC; j++)
	    		g[i][j] = -1;
	    
	    searchWords(arr, arr.length, text);
	    
	}

}
