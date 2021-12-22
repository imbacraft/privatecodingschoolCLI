/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoint;

import dto.TrainerPerCourse;
import java.util.List;
import model.Trainer;

/**
 *
 * @author Dell
 */
public interface TrainerDaoInt {

    public List<Trainer> getTrainerList();

    public void printTrainerList();

    public List<TrainerPerCourse> getTrainerPerCourseList();

    public void printTrainerPerCourseList();

    public Trainer createTrainer();

    public void insertTrainer(Trainer tr);

    public int getNextTrainerId();

    public void assignTrainerToCourse(TrainerPerCourse tpc);

    public TrainerPerCourse askUserToAssignTrainer();

    public boolean checkForDuplicateEntries(TrainerPerCourse tpc);

}
