package org.firstinspires.ftc.teamcode.Testing;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Subsystems.DoubleLauncher;
import org.firstinspires.ftc.teamcode.Subsystems.Launcher;

import java.util.Timer;

@TeleOp
public class LauncherTesting1 extends LinearOpMode {
    DoubleLauncher launcher;
    Follower follower;
    double velocity;
    boolean changing1;
    boolean changingF;
    double[] adders = {0.01,0.1,1,10,100};
    int adder = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        launcher = new DoubleLauncher(hardwareMap,telemetry,null);
        launcher.setDirection(DcMotorSimple.Direction.REVERSE,1);
        launcher.setDirection(DcMotorSimple.Direction.REVERSE,2);
        changing1 = true;
        changingF = true;
        waitForStart();
        while(opModeIsActive()){
           if(gamepad1.dpadDownWasPressed()) velocity-=adders[adder];
           if(gamepad1.dpadUpWasPressed()) velocity+=adders[adder];
           if(gamepad1.dpadLeftWasPressed()){
               if(changingF)
                   launcher.getPidfCoefficients(changing1?1:2).f-=adders[adder];
               else
                   launcher.getPidfCoefficients(changing1?1:2).p-=adders[adder];
               launcher.updatePIDFCoefficients();
           }
           if(gamepad1.dpadRightWasPressed()){
               if(changingF)
                   launcher.getPidfCoefficients(changing1?1:2).f+=adders[adder];
               else
                   launcher.getPidfCoefficients(changing1?1:2).p+=adders[adder];
               launcher.updatePIDFCoefficients();
           }
           if(gamepad1.triangleWasPressed())
               changing1=!changing1 ;
           if(gamepad1.circleWasPressed()){
               adder++;
               adder%=adders.length;
           }
           if(gamepad1.squareWasPressed())changingF=!changingF;
           launcher.startLauncher(velocity);
           telemetry.addData("Wanted velocity",velocity);
           telemetry.addData("Actual velocity 1",launcher.getMotor(1).getVelocity());
           telemetry.addData("Actual velocity 2",launcher.getMotor(2).getVelocity());
           telemetry.addData("F 1",launcher.getPidfCoefficients(1).f);
           telemetry.addData("F 2",launcher.getPidfCoefficients(2).f);
           telemetry.addData("P 1",launcher.getPidfCoefficients(1).p);
           telemetry.addData("P 2",launcher.getPidfCoefficients(2).p);
           telemetry.addData("Changing pidf value for motor",changingF?"F":"P");
           telemetry.addData("Adding by",adders[adder]);
           telemetry.addData("Tuning motor",changing1?1:2);
           telemetry.addLine("Press circle to change adder, square to change P/F, triangle to change motor");
           telemetry.update();
        }
    }
}
