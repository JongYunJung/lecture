
import java.util.*;


public class ShortestPathBFSList {	
	static Map<Integer, List<Node>> adjList = new HashMap<Integer, List<Node>>();	// ���� ����Ʈ
	static int[] D = new int[100];						// �Ÿ� ����
	static int[] P = new int[100];						// �ִ� ��� Ʈ��
	
	static int V, E;
	private static class Node{
		int v, w;
		Node(int a, int b){ v= a; w = b;}
	}
	
	// D[], P[] �迭 ��� �ϱ�
	public static void printResult()
	{
		for(int i = 1; i <= V; i++)
		{
			System.out.printf("%d ", D[i]);
		}
		System.out.printf("\n");
		for(int i = 1; i <= V; i++)
		{
			System.out.printf("%d ", P[i]);
		}
		System.out.printf("\n");
	}
	
	// BFS + ���� ����Ʈ
	public static void BFS(int v)
	{
		for(int i = 1; i <= V; i++)
			D[i] = 0xffffff;
		D[v] = 0; P[v] = v;
		
		Queue<Integer> Q = new LinkedList<Integer>();		
		Q.add(v);		
		while(!Q.isEmpty())
		{
			v = Q.remove();
			
			List<Node> list = adjList.get(v);
			for(Node node: list)
			{
				if(D[node.v] > D[v] + node.w)
				{
					D[node.v] = D[v] + node.w;
					P[node.v] = v;
					Q.add(node.v);					
				}	
			}
		}
	}
	
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		int from, to, weight;
		for(int i = 0; i < E; i++)
		{
			from = sc.nextInt();
			to = sc.nextInt();				
			weight = sc.nextInt();
			
			List<Node> list = adjList.get(from);
			if(list == null)
			{
				list = new LinkedList<Node>();
				list.add(new Node(to, weight));
				adjList.put(from, list);
			}else list.add(new Node(to, weight));
			
			list = adjList.get(to);
			if(list == null)
			{
				list = new LinkedList<Node>();
				list.add(new Node(from, weight));
				adjList.put(to, list);
			}else list.add(new Node(from, weight));
		}			
		sc.close();
		
		System.out.println("�ִ� ��� / BFS - ��������Ʈ");
		System.out.println("----------------");
		BFS(1); printResult();
		System.out.println("----------------");
		
	}
}
