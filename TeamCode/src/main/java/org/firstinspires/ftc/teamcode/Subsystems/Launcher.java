package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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
    }

    public double neededVelocity(Follower follower) {
        return 4000.0; //temporary
    }

    public void startLauncher(){
        launcherMotor.setVelocity(neededVelocity(follower));
    }
}