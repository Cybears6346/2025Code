package frc.robot.Subroutines;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkRelativeEncoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class Shooter {
    SparkMax m_shooterLeft = new SparkMax(7, MotorType.kBrushless); //states the existance of a SparkMax with a CANid of 5
    SparkMax m_shooterRight = new SparkMax(8, MotorType.kBrushless); //states the existance of a SparkMax with a CANid of 6
    SparkMax[] CANIDs= {m_shooterLeft, m_shooterRight};
    RelativeEncoder e_Encoder = m_shooterLeft.getEncoder();

    public void Initial(){
        SparkConfigure.ShooterInit(m_shooterLeft, m_shooterRight);
    }
    
    public void SetSpeed (double speed){
        m_shooterLeft.set(-speed);
        m_shooterRight.set(-speed);
    }

    public double getEncoder(){
        //Note: Up negative encoder, Down positive encoder
        return e_Encoder.getPosition();
    }
}
