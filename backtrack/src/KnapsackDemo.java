


public class KnapsackDemo {
	static int W = 10;
	static int N = 4;
	static int max = 0;
	static int[] w = new int[]{6, 3, 4, 2};
	static int[] v = new int[]{30, 14, 16, 9};
	static boolean[] bits = new boolean[N];
	public static void print_items()
	{
		for(int i = 0; i < N; i++)
		{
			if(bits[i])
				System.out.print("����" + i + ", ");
				//System.out.print("[����" + i + "(" + w[i] + "," + v[i] + ")] ");
		}
		System.out.println();
	}
	// ��ȯ �� ����.
	public static void knapsack(int k, int weight, int value)
	{
		if( weight > W) return;
		
		if(k == N)
		{
			if(value > max) 
			{
				max = value; //print_items();
			}
			return;
		}
		bits[k] = true; knapsack(k + 1,  weight + w[k], value + v[k]);
		bits[k] = false; knapsack(k + 1,  weight, value);
	}
	
	// ��ȯ ���� ������ ���·� ����
	public static int knapsack(int k, int weight)
	{
		if(k == N || weight == 0) return 0;
		
		int case1 = 0, case2 = 0;

		if( weight >= w[k]) 
			case1 = knapsack(k + 1,  weight - w[k]) + v[k];
		case2 = knapsack(k + 1,  weight);
		
		return case1 > case2? case1: case2;
	}
	public static void main(String[] args) {
		knapsack(0, 0, 0);
		System.out.println("�ִ� ��ġ = " + max);
		
		System.out.println("�ִ� ��ġ = " + knapsack(0, W));
	}

}
