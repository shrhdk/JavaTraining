package ex16_04;

import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ClassAnnotationsTest {

	@Test
	public void test() {
		ClassAnnotations.main(new String[] { "ex16_04.Annotated" });
	}

}

@Annotation1
@Annotation2
class Annotated {
}

@Retention(RetentionPolicy.RUNTIME)
@interface Annotation1 {
}

@interface Annotation2 {
}