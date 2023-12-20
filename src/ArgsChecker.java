public class ArgsChecker {

    public static void Check(String[] args) {
        if(args.length == 0) {
            throw new IllegalArgumentException("Image path is required");
        }

        checkPath(args[0]);
    }

    private static void checkPath(String path) {
        CheckPath checkPath = new CheckPath();
        if (checkPath.isValidPath(path) == false) {
            throw new IllegalArgumentException("InvalidPath");
        }
    }

}
