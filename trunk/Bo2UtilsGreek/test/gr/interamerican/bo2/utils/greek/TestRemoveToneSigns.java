package gr.interamerican.bo2.utils.greek;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for RemoveToneSigns.
 */
public class TestRemoveToneSigns {
	
	/**
	 * test containsNonGreekLetters
	 */
	@SuppressWarnings("nls")
	@Test
	public void testRemoveToneSigns() {
		RemoveToneSigns instance = RemoveToneSigns.getInstance();
		
		String[] inputs = 
			{"���� ���� ����� ���", "�����", "�", "Like ���,���,���,���" };
		String[] expecteds = 
			{"���� ���� ����� ���", "�����", "�", "Like ���,���,���,���" };
		
		for (int i = 0; i < inputs.length; i++) {
			System.out.println(inputs[i]);
			String actual = instance.removeToneSigns(inputs[i]);
			assertEquals(expecteds[i],actual);
		}
	}
	
	/**
	 * test containsNonGreekLetters
	 */	
	@Test
	public void testContainsNonGreekLetters() {
		RemoveToneSigns instance = RemoveToneSigns.getInstance();		
		Assert.assertEquals('�', instance.removeToneSign('�'));
	}

}
