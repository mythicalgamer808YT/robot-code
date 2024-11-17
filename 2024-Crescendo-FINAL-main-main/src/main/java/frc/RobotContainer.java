public class RobotContainer {

    //SUBSYSTEMS//
    private final SwerveSubsystem swerveSubsystem = new SwerveSubSystem;

    //DRIVER CONTROLLER//
    //2:12 - change the driver port to constant
    CommandXboxController driverController2 = new CommandXboxController(2);

    //COMMANDS//

    public RobotContainer() {
        //Butt
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        //Da default swerve drive command
        swerveSubsystem.setDefaultCommand(new SwerveCommand(
            swerveSubsystem,
            driverController2::getLeftX, // strafe
            driverController2::getLeftY, // forward
            driverController2::getRightX, // ratotion
            true
            //I got black I got white what u waant
        ));
    }

}
