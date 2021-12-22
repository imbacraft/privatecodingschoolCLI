/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import model.Course;
import model.Trainer;

/**
 *
 * @author Dell
 */
public class TrainerPerCourse {
    private Trainer trainer;
    private Course course;

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "TrainerPerCourse{" + "trainer=" + trainer + ", course=" + course + '}';
    }

    public TrainerPerCourse(Trainer trainer, Course course) {
        this.trainer = trainer;
        this.course = course;
    }

    public TrainerPerCourse() {
    }
    
}
