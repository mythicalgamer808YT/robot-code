public class SwerveModule {
    
    private final TalonFX mAngleMotor;
    private final TalonFX mDriveMotor;
    private final CANcoder angleEncoder;

    private final SimpleMotorFeedforward driveFeedForward;

    public SwerveModule(int driveMotorID int angleMotorID int canCoderID, String canBus) {

        angleEncoder = new CANcoder(canCoderID, canBus);
        angleEncoder.getConfigurator().apply(Robot.ctreConfigs.swerveCANcoderConfid);
        
        mDriveMotor = new TalonFX(driveMotorID, canBus);
        mDriveMotor.getConfigurator().apply(Robot.ctreConfigs.swerveDriveFXConfig);
        mDriveMotor.getConfigurator().setPosition(0.0);

        mAngleMotor = new TalonFX(angleMotorID, canBus);
        mAngleMotor.getConfigurator().apply(Robot.ctreConfigs.swerveAngleFXConfig);
        resetToAbsolute();

        driveFeedForward = new SimpleMotorFeedforward(DRIVE_kS, DRIVE_kV, DRIVE_kA);

    }

    public void setDesiredState(SwerveModuleState desiredState) {

        SwerveModuleState optimizedState = SwerveModuleState.optimize(desiredState, getCurrentAngle());

        //Conversions from meters per secont to TalonFX ticks per 100ms
        double targetSpeed = Math.min(optimizedState.speedMetersPerSecond, MAX_SPEED_METERS_PER_SECOND);
        double driveOutput = driveFeedForward.calculate(targetSpeed);
        
        //set it cuh
        mDriveMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.Velocity, velocityToTicks(targetSpeed),
        com.ctre.phoenix.motorcontrol.DemandType.arbitraryFeedForward, driveOutput / 12.0);

        //steer Motor set target angle using the TalonFX control
        double targetAngle = optimizedState.angle.getDegrees();
        mAngleMotor.set(com.ctre.phoenix.motorcontrol.ControlMode.Position, angleToTicks(targetAngle));

    }

    // -editor note --- was getPosition before but changed it becacuse it didn't make sense lol
    public double getCurrentAngle() {
        return angleEncoder.getAbsolutePosition();
    }

    //CONVERSION FUNCTIONS// -editor note --- got rid of math.Conversions because it is really only used here
    private double velocityToTicks(double velMPS) {
        double wheelRotPS = velMPS / wheelCircumfrance;
        double motorRotPS = wheelRotPS * driveGearRatio;
        return motorRotationsPerSecond * 2048 / 10; // Convert to TalonFX ticks/100ms sigma
    }

    private double angleToTicks(double angleDegrees) {
        return angleDegrees / 360.0 * 2048 * driveGearRatio; // Convert Degrees to ticks
    }

    public void resetToAbsolute() {

        double absolutePosition = Rotation2d.fromRotations(angleEncoder.getAbsolutePosition().getValue()).getRotations() - angleOffset.getRotations();
        mAngleMotor.setPosition(absolutePosition);

    }

    //extra getter methods just in case
    public TalonFX getDriveMotor() {
        return mDriveMotor;
    }

    public TalonFX getAngleMotor() {
        return mAngleMotor;
    }

    public CANcoder getObjectCANcoder()
    {
        return angleEncoder;
    }

}
