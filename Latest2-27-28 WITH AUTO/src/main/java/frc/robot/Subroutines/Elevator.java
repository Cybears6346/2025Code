
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

    public void Initial()
    {
        SparkConfigure.ElevatorInit(m_elevatorLeft, m_elevatorRight);
        // m_elevatorLeft.setInverted(true);
         SmartDashboard.putBoolean("Elevator Bottom LS", ElevatorBottomLS.get());
         SmartDashboard.putBoolean("Elevator Top LS", ElevatorTopLS.get());
    }
    
    public void SetSpeed (double speed){
// Conditional to control Limit Switches
        // if (ElevatorBottomLS.get()) {
        //     m_elevatorRight.set(-0.5);
        //     m_elevatorLeft.set(-0.5);
        //     System.out.println("Bottom Elevator LS triggered");
        //   }
        //   else if (ElevatorTopLS.get()) {
        //     m_elevatorRight.set(0.5);
        //     m_elevatorLeft.set(0.5);
        //     System.out.println("Top Elevator LS triggered");
        //   } else {
            m_elevatorLeft.set(speed);
            m_elevatorRight.set(speed);
        //   }
       
    }
}