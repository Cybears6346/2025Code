// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subroutines;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
    // Fresh beginnings
    double l2PositionBottomed = 6.875; // Calculation 31.875 - 26
    double l3PositionBottomed = 21.625;
    double l4PositionBottomed = 46;



    double startingToReefCenter = 88 - 30 - 2.9 + 0.9; // 'double' cause im unsure of whether calculation conflicts can occur, - 2.9 for front bumper + 0.9 for 2 inches behind starting line

    // The below applies to all starting positions
    public void Initial(SendableChooser<String> Auto_Selector, String[] AutoNames){
        SmartDashboard.putData("Auto choices", Auto_Selector);
        Auto_Selector.setDefaultOption(AutoNames[0], AutoNames[0]);
        Auto_Selector.addOption(AutoNames[1], AutoNames[1]);
        Auto_Selector.addOption(AutoNames[2], AutoNames[2]);
        Auto_Selector.addOption(AutoNames[3], AutoNames[3]);
        Auto_Selector.addOption(AutoNames[4], AutoNames[4]);
        Auto_Selector.addOption(AutoNames[5], AutoNames[5]);
        Auto_Selector.addOption(AutoNames[6], AutoNames[6]);
        Auto_Selector.addOption(AutoNames[7], AutoNames[7]);
        
    }

    public void doNothingAuto(Driving DriveSub)
    {
        System.out.println("Doing nothing, applies to all starting positions");
    }

    //The below applies to [Team] center starting position
    public void leaveStartingPositionAuto(Driving DriveSub)
    {
        while (DriveSub.getEncoderToInches() < startingToReefCenter) // Assumes 2 inches of movement is necessary to leave starting position/area
        {
            DriveSub.AutoDrive(-1, 0);
            System.out.println("Linear displacement: " + DriveSub.getEncoderToInches());
        }
    }

    public void scoreL1Auto(Driving DriveSub, Shooter ShooterSub)
    {
        while (DriveSub.getEncoderToInches() < startingToReefCenter) // Assumes 2 inches of movement is necessary to leave starting position/area
        {
            DriveSub.AutoDrive(-0.5, 0);
            System.out.println("Linear displacement: " + DriveSub.getEncoderToInches());
        }
        while (ShooterSub.getEncoderToInches() < 0.5)
        {
            ShooterSub.SetSpeed(-0.5);
        }
    }

    // public void scoreL2Auto(Driving DriveSub, Elevator ElevatorSub)
    // {
    //     // while (DriveSub.getEncoderToInches() < startingToReefCenter)
    //     // {
    //     //     DriveSub.AutoDrive(1, 0);
    //     // }
    //     while (-ElevatorSub.getEncoderToInches() > l2PositionBottomed) // 'l2PositionBottomed' begated because negative speed leads to negative encoder readings
    //     {
    //         ElevatorSub.SetSpeed(-0.01);
            
    //         System.out.println(-ElevatorSub.getEncoderToInches());
    //     }
    //     ElevatorSub.SetSpeed(0);
    // }
    
    public void scoreL2Auto(Driving DriveSub, Elevator ElevatorSub) {
    double targetHeight = 6.875; // inches
    double gearDiameter = 1.45; // inches
    double gearRatio = 27; // 1:27 gear ratio

    // Convert inches to encoder rotations
    double wheelCircumference = gearDiameter * Math.PI;
    double targetRotations = (targetHeight / wheelCircumference) * gearRatio;

    double initialPosition = ElevatorSub.getEncoderToInches2();
    double targetPosition = initialPosition + targetRotations;

    while (ElevatorSub.getEncoderToInches2() < targetPosition) {
        ElevatorSub.SetSpeed(-0.01); // Negative speed moves up
        System.out.println("Encoder: " + ElevatorSub.getEncoderToInches2());
    }

    ElevatorSub.SetSpeed(0); // Stop motor when target reached
}

    public void removeAlgae(Driving DriveSub, AlgaeArm AlgaeArmSub, AlgaeWheel AlgaeWheelSub, Elevator ElevatorSub)
    {
        while (DriveSub.getEncoderToInches() < startingToReefCenter)
        {
            DriveSub.AutoDrive(1, 0); // For linear travel
        }
        while (ElevatorSub.getEncoderToInches() < l2PositionBottomed)
        {
            ElevatorSub.SetSpeed(1);
        }
        // while ( )
        // {
            
        // }
    }


}
