package org.firstinspires.ftc.teamcode.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import java.util.Timer;

@TeleOp
public class LauncherTesting1 extends LinearOpMode {
    PIDFCoefficients pidfCoefficients;
    DcMotorEx launcherMotor;
    int velocity;

    @Override
    public void runOpMode() throws InterruptedException {
        launcherMotor = hardwareMap.get(DcMotorEx.class,"launcher");
        velocity = 2000;
        waitForStart();
        launcherMotor.setPower(1);
        wait(1000);
        double maxTicksPerSecond = launcherMotor.getVelocity();
        double F = 32767/maxTicksPerSecond;
        pidfCoefficients = new PIDFCoefficients(0.1*F,0.005*F,0,F);
        launcherMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,pidfCoefficients);
        while(opModeIsActive()){
           telemetry.addData("Maximum ticks per second",maxTicksPerSecond);
           if(gamepad1.dpadDownWasPressed()) velocity-=10;
           if(gamepad1.dpadUpWasPressed()) velocity+=10;
           launcherMotor.setVelocity(velocity);
           telemetry.addData("Wanted velocity",velocity);
           telemetry.addData("Actual velocity",launcherMotor.getVelocity());
        }
    }
}
