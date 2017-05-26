import java.util.*;

public class ShortestPathDijkstra {
	static Map<Integer, List<Vertex>> adjList = new HashMap<Integer, List<Vertex>>();	// ���� ����Ʈ
	static int[] D = new int[100];						// �Ÿ� ����
	static int[] P = new int[100];						// �ִ� ��� Ʈ��
	
	static int V, E;

	
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
	
	// �켱 ���� ť�� ����Ǵ� ����
	private static class Vertex implements Comparable<Vertex>{
		int v;	// ����
		int d;	// ����ġ �Ǵ� �Ÿ�
		Vertex(int a, int b)
		{
			v = a; d = b; 
		}
		public String toString()
		{
			return v +" " + d + "\n";			
		}		
		public int compareTo(Vertex arg) {			 
			return d - arg.d;
		}
	}
	// ���ͽ�Ʈ�� + �켱���� ť
	public static void dijkstra(int v)
	{
		for(int i = 1; i <= V; i++)
			D[i] = 0xffffff;
		D[v] = 0; P[v] = v;
		
		PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>();		
		Q.add(new Vertex(v, D[v]));
		
		while(!Q.isEmpty())
		{
			Vertex from = Q.poll();
			if(from.d > D[from.v]) continue;
			
			List<Vertex> list = adjList.get(from.v);
			
			for(Vertex to: list)
			{
				if(D[to.v] > D[from.v] + to.d)
				{
					D[to.v] = D[from.v] + to.d;
					P[to.v] = from.v;
					Q.add(new Vertex(to.v, D[to.v]));
					
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
			
			List<Vertex> list = adjList.get(from);
			if(list == null)
			{
				list = new LinkedList<Vertex>();
				list.add(new Vertex(to, weight));
				adjList.put(from, list);
			}else list.add(new Vertex(to, weight));
			
			list = adjList.get(to);
			if(list == null)
			{
				list = new LinkedList<Vertex>();
				list.add(new Vertex(from, weight));
				adjList.put(to, list);
			}else list.add(new Vertex(from, weight));
		}			
		sc.close();
		
		System.out.println("�ִ� ��� / ���ͽ�Ʈ��");
		System.out.println("----------------");
		dijkstra(1); printResult();
		System.out.println("----------------");
	}
}
