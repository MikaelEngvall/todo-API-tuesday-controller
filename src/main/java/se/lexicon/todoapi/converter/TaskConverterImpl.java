package se.lexicon.todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.dto.TaskDTOView;
import se.lexicon.todoapi.domain.entity.Person;
import se.lexicon.todoapi.domain.entity.Task;
import se.lexicon.todoapi.repository.PersonRepository;

import java.time.LocalDate;

@Component
public class TaskConverterImpl implements TaskConverter {

    private final PersonRepository personRepository;

    @Autowired
    public TaskConverterImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public TaskDTOView toTaskDTOView(Task entity) {
        return TaskDTOView.builder()
                .title(entity.getTitle())
                .description(entity.getDescription())
                .deadline(entity.getDeadline())
                .done(entity.isDone())
                .person(entity.getPerson())
                .build();
    }

    @Override
    public Task toTaskEntity(TaskDTOView dtoView) {
        Person person = personRepository.findById(dtoView.getPerson().getId())
                .orElseThrow(() -> new IllegalArgumentException("No person found with id: " + dtoView.getPerson().getId()));
        Task.TaskBuilder taskBuilder = Task.builder()
                .title(dtoView.getTitle())
                .description(dtoView.getDescription())
                .deadline(dtoView.getDeadline())
                .done(dtoView.isDone())
                .person(person);
//        taskBuilder.person(person);

//        if (dtoView.getPerson() != null) {
//            Person person = personRepository.findById(dtoView.getPerson().getId())
//                    .orElseThrow(() -> new IllegalArgumentException("No person found with id: " + dtoView.getPerson().getId()));
//            taskBuilder.person(person);
//        }

        return taskBuilder.build();
    }
}
