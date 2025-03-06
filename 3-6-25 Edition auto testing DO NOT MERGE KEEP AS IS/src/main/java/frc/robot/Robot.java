// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import javax.lang.model.util.ElementScanner14;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Subroutines.Arm;
import frc.robot.Subroutines.Autonomous;
import frc.robot.Subroutines.Camera;
import frc.robot.Subroutines.Driving;
// import frc.robot.Subroutines.Pickup;
import frc.robot.Subroutines.
Shooter;
// import frc.robot.Subroutines.Winch;
import frc.robot.Subroutines.Elevator;
import frc.robot.Subroutines.AlgaeArm;
import frc.robot.Subroutines.AlgaeWheel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Timer;
// Terry is a funny guy (this statement is false)
public class Robot extends TimedRobot {
  public static double kDefaultSpeed = 1.0;

  XboxController DriverController = new XboxController(0); //Assigns a new Xbox controller to port 0
  XboxController ArmController = new XboxController(1); //Assigns a new Xbox controller to port 0

  Camera CamSub = new Camera(); //Allows us to call the Camera Subroutines in the Camera.Java class
  Driving DriveSub = new Driving(); //Allows us to call the Driving Subroutines in the Driving.Java class
  // Arm ArmSub = new Arm(); //Allows us to call the Arm Subroutines in the Arm.Java class
  // Pickup PickupSub = new Pickup(); //Allows us to call the Pickup Subroutines in the Pickup.Java class
  Shooter ShooterSub = new Shooter(); //Allows us to call the Shooter Subroutines in the Shooter.Java class
  Autonomous AutoSub = new Autonomous(); //Allows us to call the Autonomous Subroutines in the Autonomous.Java class
  // Winch WinchSub = new Winch(); //Allows us to call the Autonomous Subroutines in the Autonomous.Java class
  Timer TimerSub = new Timer(); //Allows for timers
  Elevator ElevatorSub = new Elevator(); // Allows us to call the Elevator subroutine
  AlgaeArm AlgaeArmSub = new AlgaeArm();
  AlgaeWheel AlgaeWheelSub = new AlgaeWheel();
  Autonomous AutonomousSub = new Autonomous();


  String A_Selected;
  boolean AutoFinished = false;
  private static final String A_Stationary = "Stationary Score";
  private static final String A_Red_Source = "Red Source Score";
  private static final String A_Red_Center = "Center Score";
  private static final String A_Red_Amp = "Red Amp Score";
  private static final String A_Blue_Source = "Blue Source Score";
  private static final String A_Blue_Center = "Fast Center Score";
  private static final String A_Blue_Amp = "Blue Amp Score";
  private static final String A_Nothing = "Nothing";
  String[] AutoNames= {A_Stationary, A_Red_Source, A_Red_Center, A_Red_Amp, A_Blue_Source, A_Blue_Center, A_Blue_Amp, A_Nothing, ""};
  SendableChooser<String> Auto_Selector = new SendableChooser<>();

  @Override
  public void robotInit() { //This code runs when the robot powers on
    CamSub.Initial();//starts the camera streams from the roboRio
    // AutoSub.Initial(Auto_Selector, AutoNames);
    DriveSub.Initial(); //inverts the rear right motor and other setup for the driving controls
    // ArmSub.Initial(); //inverts the left arm motor and other setup for the arm controls
    ShooterSub.Initial();
    // PickupSub.Initial();
    // WinchSub.Initial();
   ElevatorSub.Initial();
    AlgaeArmSub.Initial();
    AlgaeWheelSub.Initial();
    SmartDashboard.putString("Auto Command", "RobotInit");
    
    
  }
  @Override
  public void robotPeriodic(){
    SmartDashboard.putNumber("Wheel Encoder",DriveSub.getEncoder());
  }

  double startTime;
  @Override
  public void autonomousInit(){
    // //This code starts the autonomous program. 
    // //At the beginning of every autonomous, the winch and arm will move to zero on their limit switches.
    TimerSub.reset();
    TimerSub.start();

    // SmartDashboard.putNumber("Timer", TimerSub.get());
    // AutoSub.doNothingAuto(DriveSub);
    // AutoSub.leaveStartingPositionAuto(DriveSub);
    AutoSub.scoreL2Auto(DriveSub, ElevatorSub);
    // AutoFinished = true;
    // stopAll();
    // getSelected();
    // SmartDashboard.putString("Auto Command", "zeroWinch");
    // // WinchSub.zeroWinch(TimerSub);
    // SmartDashboard.putString("Auto Command", "zeroArm");
    // // ArmSub.zeroArm(TimerSub);
    // RunAuto();
    // AutoFinished = true;
    // startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic(){
    SmartDashboard.putNumber("Timer", TimerSub.get());
    // double time = Timer.getFPGATimestamp();
    // if (time - startTime > 0 && time - startTime < 1)
    // {
    //   DriveSub.ManualDrive(1,0, true);
    // }

    
  }

  public void AutoRun()
  {
    // Include switch cases for each starting postion, purpose of auton selection in shuffleboard (ex. red, center)
  }
 
  @Override
  public void teleopInit(){
    AutoFinished = false;
    SmartDashboard.putString("Auto Command", "AutoReset");
    stopAll();
  }

  @Override
  public void teleopPeriodic() {
    //Manual Drive Code: Controlled with Left Y and Right X thumb sticks on the Driver Controller
    DriveSub.ManualDrive(DriverController.getLeftY() * 0.7, DriverController.getRightX() * 0.7,true);
    
    //Winch Code: Controlled with the Right Y thumb stick on the Arm Controller
    // if(Math.abs(ArmController.getRightY())>0.25)
    // {
    //   WinchSub.SetSpeed(-ArmController.getRightY(), ArmController.getYButton());
    // } else
    // {
    //   WinchSub.SetSpeed(0.00, ArmController.getYButton());
    // }
    

    //Manual Arm Code: Controlled by Left Y on the Arm Controller
    //Minimum speed must always be 0.05 to prevent arm from dropping
    // if(Math.abs(ArmController.getLeftY())>0.05){ArmSub.SetSpeed(ArmController.getLeftY());}
    // else{ArmSub.SetSpeed(0.05);}
    
    //Manual Pickup Code: Controlled by Left and Right Triggers on the Arm Controller
    // PickupSub.SetSpeedTeleOp(ArmController.getLeftTriggerAxis() - ArmController.getRightTriggerAxis());

    //Manual Shooter Code: Controlled by B Button on the Arm Controller
    // ShooterSub.SetSpeed(1, ArmController.getBButton());
    
    // //Preset Shooter Code: Controlled by A Button on the Arm Controller
    // if(ArmController.getAButtonPressed()){
    //   AutoSub.Preset_Shoot(ShooterSub, PickupSub);
    // }
    
    //Assign Shoot & Amp arm positions: Controlled by the Start and Back button on the Arm Controller
    // ArmSub.setShootPosition(ArmController.getStartButtonPressed());
    // ArmSub.setAmpPosition(ArmController.getBackButtonPressed());

    // Elevator Code, controls level positioning
    // if (ArmController.getLeftTriggerAxis() > 0.25)
    // {
    //   ElevatorSub.StageUp(1);
    // } else if (ArmController.getRightTriggerAxis() > 0.25)
    // {
    //   ElevatorSub.StageDown(1);
    // }
    // if (ArmController.getLeftY() > 0.25)
    // {
    //   ElevatorSub.SetSpeed(kDefaultSpeed);
    // } else if(ArmController.getLeftY() < -0.25)
    // {
    //   ElevatorSub.SetSpeed(-kDefaultSpeed);
    // }

    ShooterSub.SetSpeed(0);
    if (ArmController.getLeftTriggerAxis() > 0.25)
    {
      ShooterSub.SetSpeed(ArmController.getLeftTriggerAxis());
    } else if(ArmController.getRightTriggerAxis() > 0.25)
    {
      ShooterSub.SetSpeed(-ArmController.getRightTriggerAxis());
    }

 
      ElevatorSub.SetSpeed(ArmController.getRightY());
      AlgaeArmSub.SetSpeed(ArmController.getLeftY());
      



    /*
    //Test Individual Motor Code: Controlled by Y +button on the Driver Controller
    if (DriverController.getYButton() ==  true){
      TestMotor(0); //Change TestMotor(CAN_ID) to desired motor Range: 1 to 13
    }
    */




    // // Algae Arm Control
    

 
    
  
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
      } else if (ArmController.getBButton())
    {
      AlgaeWheelSub.SetSpeed(-1);
    }

    // if (ArmController.getAButtonPressed())
    // {
    //   AlgaeWheelSub.SetSpeed(1);
    // } else if (ArmController.getBButtonPressed())
    // {
    //   AlgaeWheelSub.SetSpeed(-1);
    // }

    // trash code
    // if (ArmController.getLeftBumper())
    // {
    //   while (ArmController.getLeftBumper())
    //   {
    //     AlgaeWheelSub.SetSpeed(1);
    //   }
    // } else if (ArmController.getRightBumper())
    // {
    //   while (ArmController.getRightBumper())
    //   {
    //     AlgaeWheelSub.SetSpeed(-1);
    //   }
    // }

    // TRiggers
    
    // ShooterSub.SetSpeed(0);
    // if (ArmController.getLeftTriggerAxis() > 0.25)
    // {
    //   ShooterSub.SetSpeed(ArmController.getLeftTriggerAxis());
    // } else if (ArmController.getRightTriggerAxis() > 0.25)
    // {
    //   ShooterSub.SetSpeed(-ArmController.getRightTriggerAxis());
    // }
    
  

  }

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public void RunAuto(){
    if (AutoFinished == false){
        switch (A_Selected) {
          //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
          // case A_Stationary:
          // AutoSub.Auto_StationaryScore(DriveSub, ArmSub, PickupSub, ShooterSub, 0, TimerSub);
          
          // break;

          // case A_Red_Source:
          // AutoSub.Auto_Source(DriveSub, ArmSub, PickupSub, ShooterSub, "RED", TimerSub);
          // break;

          // case A_Red_Center:
          // AutoSub.Auto_Center(DriveSub, ArmSub, PickupSub, ShooterSub, "RED", TimerSub);
          // break;

          // case A_Red_Amp:
          // AutoSub.Auto_Amp(DriveSub, ArmSub, PickupSub, ShooterSub, "RED", TimerSub);
          // break;

          // case A_Blue_Source:
          // AutoSub.Auto_Source(DriveSub, ArmSub, PickupSub, ShooterSub, "BLUE", TimerSub);
          // break;

          // case A_Blue_Center:
          // AutoSub.Auto_Center_Fast(DriveSub, ArmSub, PickupSub, ShooterSub, "BLUE", TimerSub);
          // break;

          // case A_Blue_Amp:
          // AutoSub.Auto_Amp(DriveSub, ArmSub, PickupSub, ShooterSub, "BLUE", TimerSub);
          // break;

          case A_Nothing:
          SmartDashboard.putString("Auto Command", "Auto Finished");;

          default:
          break;
        }
    }
  }

  public void getSelected(){
    A_Selected = Auto_Selector.getSelected();
    SmartDashboard.putString("Auto Selected", A_Selected);
  }

  public void stopAll(){
    DriveSub.ManualDrive(0, 0, true);
    // ArmSub.SetSpeed(0.05);
    // PickupSub.SetSpeed(0);
    ShooterSub.SetSpeed(0);

  }

  public void TestMotor(int CAN_ID){
      if (DriverController.getYButton() == true){
        if(1 <= CAN_ID && CAN_ID <= 4){
          DriveSub.TestMotor(0.1, CAN_ID);
        }
        
        // if(5 <= CAN_ID && CAN_ID <= 6){
        // ArmSub.TestMotor(0.1, CAN_ID);
        // }

        // if(7 <= CAN_ID && CAN_ID <= 8){
        // WinchSub.TestMotor(0.1, CAN_ID);
        // }

        // if(CAN_ID == 9){
        // PickupSub.TestMotor(0.1, CAN_ID);
        // }

        // if(10 <= CAN_ID && CAN_ID <= 13){
        // ShooterSub.TestMotor(0.1, CAN_ID);
        // }
   
      }
      else{
        if(1 <= CAN_ID && CAN_ID <= 4){
          DriveSub.TestMotor(0, CAN_ID);
        }
        
        // if(5 <= CAN_ID && CAN_ID <= 6){
        // ArmSub.TestMotor(0, CAN_ID);
        // }

        // if(7 <= CAN_ID && CAN_ID <= 8){
        // WinchSub.TestMotor(0, CAN_ID);
        // }

        // if(CAN_ID == 9){
        // PickupSub.TestMotor(0, CAN_ID);
        // }

        if(10 <= CAN_ID && CAN_ID <= 13){
        // ShooterSub.TestMotor(0, CAN_ID);
        }
   
      }
    }
}