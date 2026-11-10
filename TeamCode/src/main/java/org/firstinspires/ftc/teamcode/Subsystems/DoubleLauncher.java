package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DoubleLauncher {
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    private DcMotorEx launcherMotor1,launcherMotor2;
    private Follower follower;
    //We use these as reference points to make the formula for auto-aim
    private double motorPower1, motorPower2;
    private Pose pos1, pos2;
    //PIDF stuff
    PIDFCoefficients pidfCoefficients1,pidfCoefficients2;

    public DoubleLauncher(HardwareMap hardwareMap, Telemetry telemetry, Follower follower){
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
        this.follower = follower;
        launcherMotor1 = hardwareMap.get(DcMotorEx.class,"launcher1");
        launcherMotor2 = hardwareMap.get(DcMotorEx.class, "launcher2");
        pidfCoefficients1 = new PIDFCoefficients(0,0,0,10.5);
        pidfCoefficients2 = new PIDFCoefficients(0,0,0,10.5);
        updatePIDFCoefficients();
    }

    public double neededVelocity() {
        return 1000.0; //temporary
    }

    public void setDirection(DcMotorSimple.Direction direction, int motor){
        if(motor == 1)
            launcherMotor1.setDirection(direction);
        else if(motor == 2)
            launcherMotor2.setDirection(direction);
    }

    public void setPIDFCoefficients(PIDFCoefficients pidfCoefficients1,PIDFCoefficients pidfCoefficients2){
        this.pidfCoefficients1 = pidfCoefficients1;
        this.pidfCoefficients2 = pidfCoefficients2;
        launcherMotor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients1);
        launcherMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients2);
    }
    public void updatePIDFCoefficients(){
        setPIDFCoefficients(pidfCoefficients1,pidfCoefficients2);
    }

    public DcMotorEx getMotor(int motor){ if(motor == 1) return launcherMotor1; else if(motor == 2) return launcherMotor2; return null; }

    public PIDFCoefficients getPidfCoefficients(int motor){ return motor == 1 ? pidfCoefficients1 : pidfCoefficients2; }

    public void startLauncher(){
        launcherMotor1.setVelocity(neededVelocity());
        launcherMotor2.setVelocity(neededVelocity());
    }
    public void stopLauncher(){ launcherMotor1.setPower(0); launcherMotor2.setPower(0);}
    public void startLauncher(double velocity){
        launcherMotor1.setVelocity(velocity);
        launcherMotor2.setVelocity(velocity);
    }
}