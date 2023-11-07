package se.lexicon.todoapi.domain.dto;

import lombok.*;
import se.lexicon.todoapi.domain.entity.Person;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TaskDTOView {

    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
    private Person person;

}