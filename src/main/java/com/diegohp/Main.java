package com.diegohp;

import com.diegohp.config.AppConfig;
import com.diegohp.dto.trainee.CreateTraineeDto;
import com.diegohp.dto.trainee.UpdateTraineeDto;
import com.diegohp.dto.trainer.CreateTrainerDto;
import com.diegohp.dto.trainer.UpdateTrainerDto;

import com.diegohp.dto.user.CreateUserDto;
import com.diegohp.dto.user.UpdateUserDto;

import com.diegohp.service.TraineeService;
import com.diegohp.service.TrainerService;

import com.diegohp.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;


public class Main {
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {
        TrainerService trainerService = context.getBean(TrainerService.class);
        TraineeService traineeService = context.getBean(TraineeService.class);
        UserService userService = context.getBean(UserService.class);

        // 1. Create Trainer profile
        String trainerPsw = trainerService.create(
                new CreateTrainerDto(new CreateUserDto("Daniel", "Huerta"), 9L));
        // 2. Create Trainee profile
        String traineePsw = traineeService.create(
                new CreateTraineeDto(new CreateUserDto("David", "Madrigal"),
                        new Date(2000, 11, 4), "Insurgentes 29, CDMX")
        );

        //3. Trainee username and password matching.
        if (userService.login("Daniel.Huerta", trainerPsw)) {
            // 5. Select Trainer profile by username.
            trainerService.getByUsername("Daniel.Huerta");
            // 8. Trainer password change
            userService.changePassword("Daniel.Huerta", trainerPsw, "new trainer pswd");
            // 9. Update trainer profile.
            trainerService.update("Daniel.Huerta", new UpdateTrainerDto(
                    new UpdateUserDto(null, null, null, null), 5L
            ));
            // 12. Activate/De-activate trainer
            trainerService.toggleActive("Daniel.Huerta", false);
            trainerService.toggleActive("Daniel.Huerta", true);
        }
        //4. Trainer username and password matching.
        if (userService.login("David.Madrigal", traineePsw)) {
            // 6. Select Trainee profile by username.
            traineeService.getByUsername("David.Madrigal");
            // 7. Trainee password change
            userService.changePassword("David.Madrigal", traineePsw, "new trainee pswd");
            // 10. Update trainee profile
            traineeService.update("David.Madrigal", new UpdateTraineeDto(
                    new UpdateUserDto(null, "Buendia", null, null), null, "New address trainee"
            ));
            //11. Activate/De-activate trainee
            traineeService.toggleActive("David.Buendia", false);
            traineeService.toggleActive("David.Buendia", true);
        }
    }
}
