
package frc.robot.Subroutines;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkRelativeEncoder;
import edu.wpi.first.wpilibj.Timer;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class Elevator {
    SparkFlex m_elevatorLeft = new SparkFlex(5, MotorType.kBrushless); //states the existance of a SparkMax with a CANid of 5
    SparkFlex m_elevatorRight = new SparkFlex(6, MotorType.kBrushless); //states the existance of a SparkMax with a CANid of 6
    DigitalInput ElevatorBottomLS = new DigitalInput(0);
    DigitalInput ElevatorTopLS = new DigitalInput(1);

    // SparkFlex[] CANIDs= {m_elevatorLeft, m_shooterRight};

    // MotorControllerGroup m_winch = new MotorControllerGroup(m_winchLeft, m_winchRight);

    // RelativeEncoder e_Encoder = m_elevatorLeft.getEncoder(); -- Commented to avoid driver station spam


    public void Initial()
    {
        SparkConfigure.ElevatorInit(m_elevatorLeft, m_elevatorRight);
        // m_elevatorLeft.setInverted(true);
         SmartDashboard.putBoolean("Elevator Bottom LS", ElevatorBottomLS.get());
         SmartDashboard.putBoolean("Elevator Top LS", ElevatorTopLS.get());
    }
    
    public void SetSpeed (double speed){

        if (ElevatorBottomLS.get()) {
            m_elevatorRight.set(-0.5);
            m_elevatorLeft.set(-0.5);
            System.out.println("Bottom Elevator LS triggered");
          }
          else if (ElevatorTopLS.get()) {
            m_elevatorRight.set(0.5);
            m_elevatorLeft.set(0.5);
            System.out.println("Top Elevator LS triggered");
          } else {
            m_elevatorLeft.set(speed);
            m_elevatorRight.set(speed);
          }
       

    }
}

    // public void StageUp(double speed)
    // {
    //     if (StagingCount != 4)
    //     {
    //         while (getEncoder() < levels[StagingCount])
    //         {
    //             m_elevatorLeft.set(-speed * speedPercentage); // Due to positive encoder readings signaling down
    //         }
            
    //         StagingCount++;
    //     } else
    //     {
    //         System.out.println("Maximum level reached");
    //     }
    // }

    // public void StageDown(double speed)
    // {
    //     if (StagingCount != 0)
    //     {
    //         while (getEncoder() > levels[StagingCount])
    //         {
    //             m_elevatorLeft.set(speed * speedPercentage); // Due to negative encoder readings signaling up
    //         }
    //         StagingCount--;
    //     } else
    //     {
    //         System.out.println("Minimum level reached");
    //     }
    // }
    

    // public void CalibrateUpward(double speed)
    // {
    //     while (getEncoder() < 100) // MUST BE CHANGED IN FUTURE TO REFLECT TRUE CONVERSION FACTOR
    //     {
    //         m_elevatorLeft.set(-speed * speedPercentage);
    //     }
    //     StagingCount = 4;
    // }

    // public void CalibrateDownward(double speed)
    // {
    //     while (getEncoder() > 0)
    //     {
    //         m_elevatorLeft.set(speed * speedPercentage);
    //     }
    //     StagingCount = 0;
    // }

    // public void LevelAlign() // In case we want free control with joystick
    // {
    //     if ()
    //     {

    //     }
    // }

    // public void TestMotor (double speed, int CAN_ID){
    //     //Used to test arm motors individually. Call on this function and supply speed and CAN_ID 5-6        
    //     if (CAN_ID <= 6 || CAN_ID >= 9){
    //         String S = Integer.toString(CAN_ID);
    //         System.out.println("CAN_ID #" + S + " is not assigned to an winch motor");
    //     }
    //     else{
    //         CANIDs[(CAN_ID-7)].set(speed);
    //     }
    // }

    // public void zeroWinch(Timer TimerSub){
    //     while(LS_Winch.get() == true && TimerSub.get() < 15){
    //         SetSpeed(-0.1, false);
    //         if (LS_Winch.get() == false)
    //         {
    //             enc_Winch_Zeroed = getEncoder();
    //             enc_Winch_Top = enc_Winch_Zeroed - 58.5;
    //             //SmartDashboard.putNumber("Winch Top", enc_Winch_Top);
    //             SetSpeed(0, false);
    //         }
    //     }
    // }

//     public double getEncoder(){
//         //Note: Up negative encoder, Down positive encoder
//         return e_Encoder.getPosition();
//     }
// }
