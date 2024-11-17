public class SwerveSubsystem extends SubsystemBase {
    
    private final SwerveModule frontLeft = new SwerveModule(FRONT_LEFT_DRIVE_MOTOR_ID, FRONT_LEFT_ANGLE_MOTOR_ID, FRONT_LEFT_CANCODER_ID, CAN_BUS_NAME);
    private final SwerveModule frontRight = new SwerveModule(FRONT_RIGHT_DRIVE_MOTOR_ID, FRONT_RIGHT_ANGLE_MOTOR_ID, FRONT_RIGHT_CANCODER_ID, CAN_BUS_NAME);
    private final SwerveModule backLeft = new SwerveModule(BACK_RIGHT_DRIVE_MOTOR_ID, BACK_RIGHT_ANGLE_MOTOR_ID, BACK_RIGHT_CANCODER_ID, CAN_BUS_NAME);
    private final SwerveModule backRight = new SwerveModule(BACK_LEFT_DRIVE_MOTOR_ID, BACK_LEFT_ANGLE_MOTOR_ID, BACK_LEFT_CANCODER_ID, CAN_BUS_NAME);

    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        new edu.wpi.first.math.geometry.Translation2d(TRACK_WIDTH / 2, WHEELBASE / 2),
        new edu.wpi.first.math.geometry.Translation2d(TRACK_WIDTH / 2, -WHEELBASE / 2),
        new edu.wpi.first.math.geometry.Translation2d(-TRACK_WIDTH / 2, WHEELBASE / 2),
        new edu.wpi.first.math.geometry.Translation2d(-TRACK_WIDTH / 2, -WHEELBASE / 2)
    )

    //filed orentated sensor thingy
    private final Pigeon2 gyro = new Pigeon2(PIGEON_ID);

    public SwerveSubsystem() {
        resetGyro(); // new function obvious what it does, to declutter previous code
    }

    public void drive(double forward, double strafe, double rotation, boolean fieldRelative) {

        Rotation2d robotHeading = getHeading();

        ChassisSpeeds chassisSpeeds = fieldOriented
            ? ChassisSpeeds.fromFieldRelativeSpeeds(forward, strafe, rotation, robotHeading)
            : new ChassisSpeeds(forward, strafe, rotation);

        //convert the stuff to states
        SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);

        //Normalization of da vector sooo it doesn't break going diagonal because of the limit imposed in SwerveMudule.java
        SwerveDriveKinematives.desaturateWheelSpeeds(states, MAX_SPEED_METERS_PER_SECOND);

        frontLeft.setDesiredState(states[0]);
        frontRight.setDesiredState(states[1]);
        backRight.setDesiredState(states[2]);
        backLeft.setDesiredState(states[3]);
    }

    public void resetGyro() {
        gyro.setYaw(0);
        //I found the other command in the old code unnessary because I'm sigma
    }
    
    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(gyro.getYaw());
    }

    //removed unnessary functions and redundant stuff too
    //also didn't used Pose because I'm sigma and don't need no object doin my math
}
