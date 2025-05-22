package org.iclass.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iclass.board.model.BookableEntity;

import java.util.Arrays;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookableDTO {
    private int id;
    private String group;
    private String title;
    private String notes;
    private int[] sessions;
    private int[] days;

    public BookableDTO (BookableEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.notes = entity.getNotes();
        this.group = entity.getGroupName();
        this.sessions = Arrays.stream(entity.getSessionNumbers().split(","))
                .mapToInt(Integer::parseInt) // 각 문자열을 정수로 변환
                .toArray(); // 정수 스트림을 배열로 변환

        this.days= Arrays.stream(entity.getAvailableDays().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public BookableEntity toEntity(){
        BookableEntity entity = BookableEntity.builder()
                .id(this.id)
                .title(this.title)
                .notes(this.notes)
                .groupName(this.group)
                .sessionNumbers(Arrays.stream(this.sessions)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(",")))
                .availableDays(Arrays.stream(this.days)     // 정수 배열을 IntStream으로 변환
                        .mapToObj(String::valueOf)          // 각 정수를 문자열로 변환
                        .collect(Collectors.joining(",")))   // ,로 구분하여 문자열로 결합)
                .build();
        return  entity;
    }



}
