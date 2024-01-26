/**
 * The UserImagePathFinder class extends the ImagePathFinder class and sets the image path to a
 * specific directory for user-prepared data.
 */
public class UserImagePathFinder extends ImagePathFinder{
    private final static String USER_IMAGE_PATH = "userPreparedData/img";

    public UserImagePathFinder() {
        super(USER_IMAGE_PATH);
    }
}
