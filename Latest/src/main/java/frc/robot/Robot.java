// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//Import/ Packages ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


package frc.robot;
import javax.lang.model.util.ElementScanner14;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Subroutines.Autonomous;  We do not have Autonomous code yet
import frc.robot.Subroutines.Driving;
import frc.robot.Subroutines.Shooter;
import frc.robot.Subroutines.Elevator;
import frc.robot.Subroutines.AlgaeArm;
import frc.robot.Subroutines.AlgaeWheel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Timer;


//Initialization ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


public class Robot extends TimedRobot {
  public static double kDefaultSpeed = 1.0;

  XboxController DriverController = new XboxController(0); //Assigns a new Xbox controller to port 0
  XboxController ArmController = new XboxController(1); //Assigns a new Xbox controller to port 0

  Driving DriveSub = new Driving(); //Allows us to call the Driving Subroutines in the Driving.Java class
  Shooter ShooterSub = new Shooter(); //Allows us to call the Shooter Subroutines in the Shooter.Java class
  Timer TimerSub = new Timer(); //Allows for timers
  Elevator ElevatorSub = new Elevator(); // Allows us to call the Elevator subroutine
  AlgaeArm AlgaeArmSub = new AlgaeArm();
  AlgaeWheel AlgaeWheelSub = new AlgaeWheel();

  @Override
  public void robotInit() { //This code runs when the robot powers on
    DriveSub.Initial(); //inverts the rear right motor and other setup for the driving controls
    ShooterSub.Initial();
    ElevatorSub.Initial();
    AlgaeArmSub.Initial();
    AlgaeWheelSub.Initial();
    SmartDashboard.putString("Auto Command", "RobotInit");
  }
  @Override
  public void robotPeriodic(){

  }


 //Auton  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


  private double startTime; //Following 0 to Autonomous
  @Override
  public void autonomousInit(){
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic(){
    double time = Timer.getFPGATimestamp(); //This gets the time since the robot turned on, not how long since automode started EVERY AUTO NEEDS THIS
// Each Auto number is gona be a different method in the autonomous file later.  The conditionals are just time keepers


   // AUTO #1; EVERYTHING BELOW THIS COMMENT IS A BASIC AUTO METHOD.  DRIVE 5 SECONDS, ELEVATOR 2 Seconds, SHOOT 2 Seconds

    // Drive Auto
    // if (time - startTime < 5){
    //   DriveSub.ManualDrive(-0.6, 0,true);
    // } else {
    //   DriveSub.ManualDrive(-0, 0,true);
    // }

    // if (time - startTime > 6 && time- startTime < 8){
    //   ElevatorSub.SetSpeed(-0.6); //MUST BE NEGATIVE, POSITIVE WILL CAUSE IT TO GO DOWN
    // } else {
    //   ElevatorSub.SetSpeed(0); //Must be 0 so that it can stop
    // }

    // if (time - startTime > 8 && time - startTime < 10){
    //   ShooterSub.SetSpeed(0.6);
    // } else {
    //   ShooterSub.SetSpeed(0);
    // }

//AUTO # 2 THIS WILL RUN ELEVATOR 2 SECONDS, BRING UP ALGAE ARM 0.5 SECONDS, AND SPIN ALGAE WHEELS 3 SECONDS
  // if (time - startTime > 0 && time- startTime < 2){//this is for L3 AUton
  //   ElevatorSub.SetSpeed(-0.6); //MUST BE NEGATIVE, POSITIVE WILL CAUSE IT TO GO DOWN
  // } else {
  //   ElevatorSub.SetSpeed(0); //Must be 0 so that it can stop
  // }
 
  // if (time - startTime > 2 && time - startTime < 2.5){
  //   AlgaeArmSub.SetSpeed(-0.6);
  // } else {
  //   AlgaeArmSub.SetSpeed(0);
  // }

  // if (time - startTime > 3 && time - startTime < 6){
  //   AlgaeWheelSub.SetSpeed(0.6);
  // } else {
  //   AlgaeWheelSub.SetSpeed(0);
  // }
  
//Auto #3 L3 AUTON THIS IS JUST ELEVATOR MOVING UP. ALSO THIS IS SPECIFICALLY FOR 1:60 Gear Ratio with 1 elevator motor moving
  // if (time - startTime > 0 && time- startTime < 4){//this is for L3 AUton
  //   ElevatorSub.SetSpeed(-0.6); //MUST BE NEGATIVE, POSITIVE WILL CAUSE IT TO GO DOWN
  // } else {
  //   ElevatorSub.SetSpeed(0); //Must be 0 so that it can stop
  // }
  // if (time - startTime > 4.5 && time - startTime < 6){
  //   ShooterSub.SetSpeed(0.6);
  // } else {
  //   ShooterSub.SetSpeed(0);
  // }

  //Auto #4 L2 AUTON
  if (time - startTime > 0 && time- startTime < 1.2){//this is for L2 AUton
    ElevatorSub.SetSpeed(-0.6); //MUST BE NEGATIVE, POSITIVE WILL CAUSE IT TO GO DOWN
  } else {
    ElevatorSub.SetSpeed(0); //Must be 0 so that it can stop
  }
  if (time - startTime > 1.5 && time - startTime < 3){
    ShooterSub.SetSpeed(0.6);
  } else {
    ShooterSub.SetSpeed(0);
  }

//Auto #5 L4 Auton
if (time - startTime > 0 && time- startTime < 8){//this is for L3 AUton
  ElevatorSub.SetSpeed(-0.6); //MUST BE NEGATIVE, POSITIVE WILL CAUSE IT TO GO DOWN
} else {
  ElevatorSub.SetSpeed(0); //Must be 0 so that it can stop
}
// if (time - startTime > 4.5 && time - startTime < 6){
//   ShooterSub.SetSpeed(0.6);
// } else {
//   ShooterSub.SetSpeed(0);
// }


}
 
  @Override
  public void teleopInit(){
    SmartDashboard.putString("Auto Command", "AutoReset");
    stopAll();
  }

  @Override
  public void teleopPeriodic() {
    //Manual Drive Code: Controlled with Left Y and Right X thumb sticks on the Driver Controller
    DriveSub.ManualDrive(DriverController.getLeftY(), DriverController.getRightX(),true);


//SetSpeed for Subsystems ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  


// Coral shooter 
    ShooterSub.SetSpeed(0);
    if (ArmController.getLeftTriggerAxis() > 0.25)
    {
      ShooterSub.SetSpeed(ArmController.getLeftTriggerAxis());
    } else if(ArmController.getRightTriggerAxis() > 0.25)
    {
      ShooterSub.SetSpeed(-ArmController.getRightTriggerAxis());
    }

//Elevator Movement
    ElevatorSub.SetSpeed(ArmController.getLeftY());
     
// Algae Arm Control
    AlgaeArmSub.SetSpeed(ArmController.getRightY());
    // Code to Account for Algae Arm drift 

    // if (ArmController.getRightY() > 0.35)
    // {
    //   AlgaeArmSub.SetSpeed(0.15);
    // } else if (ArmController.getRightY() < -0.35)
    // {
    //   AlgaeArmSub.SetSpeed(-0.15);
    // }
  
//Algae Wheels Control
    AlgaeWheelSub.SetSpeed(0);
      if (ArmController.getAButton())
      {
        AlgaeWheelSub.SetSpeed(1);
      }
    else if (ArmController.getBButton())
    {
      AlgaeWheelSub.SetSpeed(-1);
    }
  }


//Smartdashboard, Driver Station, Shuffleboard configurations ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


  public void RunAuto(){
   
  }

  public void getSelected(){
    
  }

  public void stopAll(){
    DriveSub.ManualDrive(0, 0, true);
    // ArmSub.SetSpeed(0.05);
    // PickupSub.SetSpeed(0);
    // ShooterSub.SetSpeed(0, true);

  }

  public void TestMotor(int CAN_ID){
    if (DriverController.getYButton() == true){
      if(1 <= CAN_ID && CAN_ID <= 4){
        DriveSub.TestMotor(0.1, CAN_ID);
      }
    } else{
      if(1 <= CAN_ID && CAN_ID <= 4){
        DriveSub.TestMotor(0, CAN_ID);
      }
    } 
  }
}
