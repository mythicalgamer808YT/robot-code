public class SwerveCommand extends CommandBase {
    
    private final SwerveSubsystem swerveSubsystem;
    private final DoubleSupplier leftX; // left of joystick
    private final DoubleSupplier leftY; // X = Strafe   Y = Forward
    private final DoubleSupplier rightX;
    private final boolean fieldOriented;

    public SwerveCommand(SwerveSubsystem swerveSubsystem,
    DoubleSupplier leftX,
    DoubleSupplier leftY,
    DoubleSupplier rightX,
    boolear fieldOriented) {

        this.swerveDriveSubsystem = swerveDriveSubsystem;
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
        this.fieldOriented = fieldOriented;
        addRequirements(swerveDriveSubsystem);
    }

    @Override
    public void execute() {
        //joystick inputs
        double xInput = leftX.getAsDouble();
        double yInput = leftY.getAsDouble();
        double rotation = rightX.getAsDouble();

        //translation vector cuh
        double forward = -yInput; // joystick invert
        double strafe = xInput;

        //Yippee the robot can move
        swerveSubsystem.drive(forward, strafe, rotation, fieldOriented);

    }
}
