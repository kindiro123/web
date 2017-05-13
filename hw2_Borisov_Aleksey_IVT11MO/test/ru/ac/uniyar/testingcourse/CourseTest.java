package ru.ac.uniyar.testingcourse;

import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class CourseTest {
    
    public CourseTest() {
    }
    
    @Test
    /**
     * Проверяет переход из состояния неполного курса в состояние неполного курса
     */
    public void CourseNotFullToCourseNotFull() {
        int maxStudents = 2;
        LinkedList<Integer> expectedList = new LinkedList<>();
        expectedList.add(1);
        Course course = new Course(maxStudents); 
        
        course.enroll(1);
        assertThat(course.getEnrollmentList()).isEqualTo(expectedList);
        assertThat(course.getEnrollmentList().size() < maxStudents).isTrue();
        course.unenroll(1);
    }
    
    @Test
     /**
     * Проверяет переход из состояния неполного курса в состояние полного курса
     */
    public void CourseNotFullToCourseFull() {
        int maxStudents = 1;
        LinkedList<Integer> expectedList = new LinkedList<>();
        expectedList.add(1);
        
        Course course = new Course(maxStudents); 
        
        course.enroll(1);
        assertThat(course.getEnrollmentList().size() == maxStudents).isTrue();
        assertThat(course.getEnrollmentList()).isEqualTo(expectedList);
    }
    
    @Test
     /**
     * Проверяет переход из состояния полного курса в состояние полного курса
     */
    public void CourseFullToCourseFull() {
        int maxStudents = 1;
        LinkedList<Integer> expectedList = new LinkedList<>();
        expectedList.add(1);
        Course course = new Course(maxStudents); 
        course.enroll(1);
        assertThat(course.getEnrollmentList()).isEqualTo(expectedList);
        course.unenroll(1);
        assertThat(course.getEnrollmentList()).doesNotContain(1);
    }
    
    @Test
     /**
     * Проверяет переход из состояния полного курса в состояние неполного курса
     */
    public void CourseFullToCourseNotFull() {
        int maxStudents = 1;
        LinkedList<Integer> expectedList = new LinkedList<>();
        Course course = new Course(maxStudents); 
        course.enroll(1);
        
        course.unenroll(1);
        assertThat(course.getEnrollmentList()).isEqualTo(expectedList);
    }
    
    @Test
     /**
     * Проверяет переход из состояния полного курса в состояние со списком ожидания
     */
    public void CourseFullToWaitngListExists() {
        int maxStudents = 1;
        LinkedList<Integer> expectedWaitingList = new LinkedList<>();
        expectedWaitingList.add(2);
        Course course = new Course(maxStudents); 
        course.enroll(1);
        
        course.enroll(2);
        assertThat(course.getEnrollmentList()).doesNotContain(2);
        assertThat(course.getWaitingList()).isEqualTo(expectedWaitingList);
    }
    
    @Test
    /**
     * Проверяет переход из состояния со списком ожидания в состояние со списком ожидания
     */
    public void WaitngListExistsToWaitingListExists() {
        int maxStudents = 1;
        Course course = new Course(maxStudents);
        LinkedList<Integer> expectedList = new LinkedList<>();
        LinkedList<Integer> expectedWaitingList = new LinkedList<>();
        expectedList.add(2);
        expectedWaitingList.add(3);
        course.enroll(1);
        course.enroll(2);
        
        course.enroll(3);
        course.unenroll(1);
       
        assertThat(getNumStudents(course) > maxStudents).isTrue();
        assertThat(course.getEnrollmentList()).isEqualTo(expectedList);
        assertThat(course.getWaitingList()).isEqualTo(expectedWaitingList);
    }
    
    @Test
     /**
     * Проверяет переход из состояния со списком ожидания в состояние полного курса
     */
    public void WaitngListExistsToCourseFull() {
        int maxStudents = 1;
        LinkedList<Integer> expectedList = new LinkedList<>();
        LinkedList<Integer> expectedWaitingList = new LinkedList<>();
        expectedList.add(2);
        Course course = new Course(maxStudents); 
        course.enroll(1);
        course.enroll(2);
        
        course.unenroll(1);
        assertThat(course.getEnrollmentList().size() == maxStudents).isTrue();
        assertThat(course.getEnrollmentList()).isEqualTo(expectedList);
        assertThat(course.getWaitingList()).isEqualTo(expectedWaitingList);
    }
    

    @Test
    /**
     * Проверяет, что каждый студент может быть записан 1 раз 
     */
    public void enrollDuplicates() {
        int maxStudents = 2;
        LinkedList<Integer> expectedList = new LinkedList<>();
        expectedList.add(1);
        Course course = new Course(maxStudents); 
        course.enroll(1);
        course.enroll(1);
        course.enroll(1);
       
        assertThat(course.getEnrollmentList()).isEqualTo(expectedList);
        assertThat(course.getWaitingList()).doesNotContain(1);
    }
    
    @Test
    /**
     * Проверяет удаление несуществующих элементов
     */
    public void unenrollNotExisting() {
        int maxStudents = 2;
        LinkedList<Integer> expectedList = new LinkedList<>();
        expectedList.add(1);
        Course course = new Course(maxStudents); 
        course.enroll(1);

        course.unenroll(2);
        assertThat(course.getEnrollmentList()).isEqualTo(expectedList); 
    }
    
     @Test
    /**
     * Проверяет запись студента на курс с максимальным количеством студентов = 0
     */
    public void enrollInEmpty(){
        int maxStudents = 0;
        LinkedList<Integer> expectedWaitingList = new LinkedList<>();
        expectedWaitingList.add(1);
        Course course = new Course(maxStudents); 
        course.enroll(1);
        
        assertThat(course.getEnrollmentList()).doesNotContain(1);
        assertThat(course.getWaitingList()).isEqualTo(expectedWaitingList); 
    }
    
    /**
     * @param course 
     * @return количество студентов в списках
     */
    public int getNumStudents(Course course){
        return course.getEnrollmentList().size() + course.getWaitingList().size();
    }
    
    /**
     * Проверяет, есть ли студент в списках
     * @param course
     * @param studId id студента, который нужно проверить
     * @return true если есть, false, если нет
     */
    public boolean isStudentInLists(Course course, int studId){
        return course.getEnrollmentList().contains(studId) || course.getWaitingList().contains(studId);
    }
   
}
