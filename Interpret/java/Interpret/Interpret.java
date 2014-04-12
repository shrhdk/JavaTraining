package interpret;

public class Interpret {

    public static void main(String[] args) {
        interpret.ClassViewer classViewer = new interpret.ClassViewer(java.awt.Frame.class);
        classViewer.setVisible(true);
    }
}
