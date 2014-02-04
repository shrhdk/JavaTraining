package ex13_02;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;

public class MatchingCounterTest {

	@Test
	public void count() {
		assertThat(MatchingCounter.count("bbbbbb", "aa"), is(0));
		assertThat(MatchingCounter.count("abbbba", "aa"), is(0));
		assertThat(MatchingCounter.count("ababab", "aa"), is(0));
		assertThat(MatchingCounter.count("baabbb", "aa"), is(1));
		assertThat(MatchingCounter.count("aabbbb", "aa"), is(1));
		assertThat(MatchingCounter.count("bbaabb", "aa"), is(1));
		assertThat(MatchingCounter.count("bbbbaa", "aa"), is(1));
		assertThat(MatchingCounter.count("aabbaa", "aa"), is(2));
		assertThat(MatchingCounter.count("aaaaaa", "aa"), is(3));
		assertThat(MatchingCounter.count("aaaaaaa", "aa"), is(3));
	}

}
