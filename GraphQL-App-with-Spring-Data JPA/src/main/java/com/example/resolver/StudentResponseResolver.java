package com.example.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.entity.Subject;
import com.example.enums.SubjectNamefilter;
import com.example.response.StudentResponse;
import com.example.response.SubjectResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse> {
    public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse ,
                                                     SubjectNamefilter subjectNamefilter){
        List<SubjectResponse> learningSubjects = new ArrayList<>();
        if (studentResponse.getStudent().getLearningSubjects() != null) {
			learningSubjects = new ArrayList<SubjectResponse>();
			for (Subject subject: studentResponse.getStudent().getLearningSubjects()) {
                if(subjectNamefilter.name().equalsIgnoreCase("All")){
                    learningSubjects.add(new SubjectResponse(subject));
                } else if(subjectNamefilter.name().equalsIgnoreCase(subject.getSubjectName())){
                    learningSubjects.add(new SubjectResponse(subject));
                }
			}
		}
        return learningSubjects;
    }

    public String getFullName(StudentResponse studentResponse){
        return studentResponse.getFirstName() + " " + studentResponse.getLastName();
    }
}
