package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Launcher {
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    private DcMotorEx launcherMotor;
    private Follower follower;
    //We use these as reference points to make the formula for auto-aim
    private double motorPower1, motorPower2;
    private Pose pos1, pos2;

    private double P,I,D,F;
    //PIDF stuff
    PIDFCoefficients pidfCoefficients;

    public Launcher(HardwareMap hardwareMap, Telemetry telemetry, Follower follower){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.follower = follower;
        launcherMotor = hardwareMap.get(DcMotorEx.class,"launcher");
        pidfCoefficients = new PIDFCoefficients(0,0,0,0);
        launcherMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);
    }

    public double neededVelocity() {
        return 1000.0; //temporary
    }

    public void setDirection(DcMotorSimple.Direction direction){
        launcherMotor.setDirection(direction);
    }

    public void setPIDFCoefficients(PIDFCoefficients pidfCoefficients){
        this.pidfCoefficients = pidfCoefficients;
    }
    public void updatePIDFCoefficients(){
        setPIDFCoefficients(pidfCoefficients);
    }

    public DcMotorEx getMotor(){ return launcherMotor; }

    public PIDFCoefficients getPidfCoefficients(){ return pidfCoefficients; }

    public void startLauncher(){
        launcherMotor.setVelocity(neededVelocity());
    }
    public void stopLauncher(){ launcherMotor.setPower(0);}
    public void startLauncher(double velocity){
        launcherMotor.setVelocity(velocity);
    }
}