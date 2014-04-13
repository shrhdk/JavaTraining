package interpret;

public class Interpret {

    public static void main(String[] args) {
        ClassViewer classViewer = new ClassViewer(java.awt.Frame.class, null);
        classViewer.setVisible(true);
    }
}
