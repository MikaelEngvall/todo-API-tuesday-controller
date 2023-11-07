package se.lexicon.todoapi.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.todoapi.domain.dto.PersonDTOView;
import se.lexicon.todoapi.domain.entity.Person;


@Component
public class PersonConverterImpl implements PersonConverter {

    @Autowired
    private TaskConverter taskConverter;

    @Override
    public PersonDTOView toPersonDTOView(Person entity) {
        return PersonDTOView.builder()
                .name(entity.getName())
                .tasks(entity.getTasks().stream()
                        .map(task -> taskConverter.toTaskDTOView(task))
                        .toList())
                .build();
    }

    @Override
    public Person toPersonEntity(PersonDTOView dtoView) {
        return Person.builder()
                .name(dtoView.getName())
                .tasks(dtoView.getTasks().stream()
                        .map(taskDTOView -> taskConverter.toTaskEntity(taskDTOView))
                        .toList())
                .build();
    }
}
