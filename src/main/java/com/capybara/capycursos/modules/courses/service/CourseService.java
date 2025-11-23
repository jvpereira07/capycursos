package com.capybara.capycursos.modules.courses.service;

import java.util.List;
import java.util.UUID;

import com.capybara.capycursos.modules.courses.model.Module;
import org.springframework.stereotype.Service;

import com.capybara.capycursos.modules.courses.model.Course;
import com.capybara.capycursos.modules.courses.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository courseRepository;


    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;

    }
    public Course saveCourse(Course course){
        return courseRepository.saveAndFlush(course);

    }
    public Course getCourseByCode(String code){
        return courseRepository.findByCode(code).orElseThrow(
                () -> new RuntimeException("Curso não encontrado")
        );
    }
    public Course getCourse(UUID id){
        return courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Curso não encontrado")
        );
    }
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }
    public Course UpdateCourse(UUID id, Course course){
        Course courseEntity = getCourse(id);
        Course courseUpdated = Course.builder()
                .id(id)
                .code(course.getCode() != null ? course.getCode() : courseEntity.getCode())
                .name(course.getName() != null ? course.getName() : courseEntity.getName())
                .description(course.getDescription() != null ? course.getDescription() : courseEntity.getDescription())
                .build();
        if (course.getModules() != null) {
            courseEntity.getModules().clear();

            for (Module module : course.getModules()) {
                module.setId(null);
                module.setCourse(courseEntity);
                courseEntity.getModules().add(module);
            }
        }
        return courseRepository.saveAndFlush(courseUpdated);
    }
    public Course UpdateByCode(String code, Course course) {
        Course courseEntity = getCourseByCode(code);
        Course courseUpdated = Course.builder()
                .id(course.getId() != null ? course.getId() : courseEntity.getId())
                .code(course.getCode() != null ? course.getCode() : courseEntity.getCode())
                .name(course.getName() != null ? course.getName() : courseEntity.getName())
                .description(course.getDescription() != null ? course.getDescription() : courseEntity.getDescription())
                .modules(course.getModules() != null ? course.getModules() : courseEntity.getModules()).build();
        return courseRepository.saveAndFlush(courseUpdated);
        }
        public void DeleteCourse(UUID id){
            courseRepository.deleteById(id);
        }


    }
