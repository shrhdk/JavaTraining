package ex16_05;

import static org.junit.Assert.*;

import org.junit.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.AnnotatedElement;

public class ClassAnnotationsTest {

    @Test
    public void test() {
        ClassAnnotations.main(new String[]{"ex16_05.Annotated"});
    }

}

@Annotation1
@Annotation2
@Annotation3
class Annotated {

    @Annotation1
    public Annotated() {

    }

    @Annotation1
    @Annotation2
    public int annotatedField;

    @Annotation1
    @Annotation3
    public void annotatedMethod() {
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Annotation1 {
}

@Retention(RetentionPolicy.RUNTIME)
@interface Annotation2 {
}

@interface Annotation3 {
}
