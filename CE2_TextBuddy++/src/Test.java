import static org.junit.Assert.*;


public class Test {

	@org.junit.Test
	public void test() {
		
		TextBuddy.clear();
		
		//test adding
		int initialSize = TextBuddy.getBufferSize();
		TextBuddy.add("hello world");
		assertEquals(initialSize+1, TextBuddy.getBufferSize());
		
		//testing clearall
		TextBuddy.clear();
		assertEquals(0, TextBuddy.getBufferSize());
		
		//test deleting
		TextBuddy.add("Quick");
		TextBuddy.add("brown");
		TextBuddy.add("fox");
		
		initialSize = TextBuddy.getBufferSize();
		TextBuddy.delete("2");
		assertEquals(initialSize-1, TextBuddy.getBufferSize());
	}
		
}
