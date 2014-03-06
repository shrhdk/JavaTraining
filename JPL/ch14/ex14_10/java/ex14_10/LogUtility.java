package ex14_10;

public class LogUtility {

    public static void logDebug() {
        System.out.println(getStackTraceInfo());
    }

    public static void logDebug(String message) {
        System.out.println(getStackTraceInfo() + " " + message);
    }

    /**
     * スタックトレースから呼び出し元の基本情報を取得。
     *
     * @return <<className#methodName:lineNumber>>
     */
    private static String getStackTraceInfo() {
        // 現在のスタックトレースを取得。
        // 0:VM 1:スレッド 2:getStackTraceInfo() 3:logDebug() 4:呼び出し元
        StackTraceElement element = Thread.currentThread().getStackTrace()[3];

        String fullName = element.getClassName();
        String className = fullName.substring(fullName.lastIndexOf(".") + 1);
        String methodName = element.getMethodName();
        int lineNumber = element.getLineNumber();

        return "<<" + Thread.currentThread().getName()  + ":" + className + "#" + methodName + ":" + lineNumber + ">> ";
    }
}
