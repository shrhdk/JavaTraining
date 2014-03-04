package ex13_01;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class MatchingCounterTest {

	@Test
	public void count() {
		assertThat(MatchingCounter.count("bbbbbb", 'h'), is(0));
		assertThat(MatchingCounter.count("abbbba", 'i'), is(0));
		assertThat(MatchingCounter.count("ababab", 'j'), is(0));
		assertThat(MatchingCounter.count("baabbb", 'a'), is(2));
		assertThat(MatchingCounter.count("aabbbb", 'b'), is(4));
		assertThat(MatchingCounter.count("bbaabb", 'a'), is(2));
		assertThat(MatchingCounter.count("bbbbaa", 'b'), is(4));
		assertThat(MatchingCounter.count("aabbaa", 'a'), is(4));
		assertThat(MatchingCounter.count("aaaaaa", 'b'), is(0));
		assertThat(MatchingCounter.count("aaaaaaa", 'a'), is(7));
	}

}
