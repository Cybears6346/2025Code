
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

public class AlgaeWheel {
    SparkFlex m_algaewheel = new SparkFlex(10, MotorType.kBrushless); //states the existance of a sparkflex with a CANid of 10
    SparkFlex[] CANIDs= {m_algaewheel};
    RelativeEncoder e_Encoder = m_algaewheel.getEncoder();
    DigitalInput LS_Elevator = new DigitalInput(2);

    public void Initial(){
        SparkConfigure.AlgaeWheelInit(m_algaewheel);
    }
    
    public void SetSpeed (double speed){
        m_algaewheel.set(speed);
    }

    public double getEncoder(){
        //Note: Up negative encoder, Down positive encoder
        return e_Encoder.getPosition();
    }
}
