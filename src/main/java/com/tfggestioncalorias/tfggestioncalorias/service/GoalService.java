package com.tfggestioncalorias.tfggestioncalorias.service;

import com.tfggestioncalorias.tfggestioncalorias.entity.Genre;
import com.tfggestioncalorias.tfggestioncalorias.entity.Goal;
import com.tfggestioncalorias.tfggestioncalorias.entity.PhisicalActivity;
import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class GoalService {

    //Nos basamos en la fórmula de Harris-Benedict
    public Integer calculateGoalCalories(UserApp user) {
        Integer age = user.getAge();
        Integer height = user.getHeight();
        BigDecimal weight = user.getWeight();

        //tasa metabólica = calorias de mantenimiento entre hombres o mujeres
        double tmb;

        if(user.getGenre() == Genre.HOMBRE){
            tmb = 88.362 + (13.397 * weight.doubleValue()) + (4.799 * height) - (5.677 * age);
        }else {
            tmb = 447.593 + (9.247 * weight.doubleValue()) + (3.098 * height) - (4.330 * age);
        }

        //calculamos el valor de la actividad del usuario
        double activityFactor = 0;

        if(user.getPhisical_activity() == PhisicalActivity.SEDENTARIO){
            activityFactor = 1.2;
        } else if (user.getPhisical_activity() == PhisicalActivity.LIGERO) {
            activityFactor = 1.375;
        }else if (user.getPhisical_activity() == PhisicalActivity.MODERADO) {
            activityFactor = 1.55;
        } else if (user.getPhisical_activity() == PhisicalActivity.ACTIVO) {
            activityFactor = 1.725;
        }else{
            activityFactor = 1.9;
        }

        //obtenemos las calorías y dependiendo del objetivo sumamos o restamos 500 calorías (lo recomendado para aumentar o perder kg)
        double adjustedCalories = tmb * activityFactor;

        if(user.getGoal() == Goal.PERDER){
            adjustedCalories -= 500;
        } else if (user.getGoal() == Goal.AUMENTAR) {
            adjustedCalories += 500;
        } else {
            adjustedCalories += 0;
        }

        return (int) Math.round(adjustedCalories);
    }

    //será el 30% de las calorías en total
    public BigDecimal calculateGoalProtein(Integer goalCalories) {
        double proteinCalories = goalCalories * 0.30;
        double gramsProtein = proteinCalories / 4;
        return BigDecimal.valueOf(gramsProtein).setScale(2, RoundingMode.HALF_UP);
    }

    //será el 40% de las calorías
    public BigDecimal calculateGoalCarbohydrates(Integer goalCalories) {
        double carbsCalories = goalCalories * 0.40;
        double gramsCarbs = carbsCalories / 4;
        return BigDecimal.valueOf(gramsCarbs).setScale(2, RoundingMode.HALF_UP);
    }

    //será el otro 30% de las calorías
    public BigDecimal calculateGoalFats(Integer goalCalories) {
        double fatsCalories = goalCalories * 0.30;
        double gramsFats = fatsCalories / 9;
        return BigDecimal.valueOf(gramsFats).setScale(2, RoundingMode.HALF_UP);
    }
}
