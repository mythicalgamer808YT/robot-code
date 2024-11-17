public class Constants {
    
    //prevent instantiation because that wouldn't make sence *cue smack on face sound* duh poopy head :p
    private Constants() {}

    private static final class SwerveConstants() {

        //EDITOR NOTE 11/15/24 ---- actually set theses values to something right :/

        public static final double WHEEL_CIRCUMFERENCE = 999;
        public static final double DRIVE_GEAR_RATIO = 999;
        public static final double MAX_SPEED_METERS_PER_SECOND = 999;

        // FeedForward Constants
        public static final double DRIVE_kS = 999;
        public static final double DRIVE_kV = 999;
        public static final double DRIVE_kA = 999;

        //Gyro
        public static final int PIGEON_ID = 999;

    }

    public static final class ModuleConstants {

        public static final int FRONT_LEFT_DRIVE_MOTOR_ID = 999;
        public static final int FRONT_LEFT_ANGLE_MOTOR_ID = 999;
        public static final int FRONT_LEFT_CANCODER_ID = 999;

        public static final int FRONT_RIGHT_DRIVE_MOTOR_ID = 999;
        public static final int FRONT_RIGHT_ANGLE_MOTOR_ID = 999;
        public static final int FRONT_RIGHT_CANCODER_ID = 999;

        public static final int BACK_RIGHT_DRIVE_MOTOR_ID = 999;
        public static final int BACK_RIGHT_ANGLE_MOTOR_ID = 999;
        public static final int BACK_RIGHT_CANCODER_ID = 999;

        public static final int BACK_LEFT_DRIVE_MOTOR_ID = 999;
        public static final int BACK_LEFT_ANGLE_MOTOR_ID = 999;
        public static final int BACK_LEFT_CANCODER_ID = 999;

        public static final String CAN_BUS_NAME = "sigma ohio gyatt"

    }

    public static final class RobotDimensions {

        public static final double TRACK_WIDTH = 999; // meters
        public static final double WHEELBASE = 999; // meters

    }

}
