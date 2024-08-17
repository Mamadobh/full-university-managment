package com.global.university.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private int number;
    private int size;
    private long totalElments;
    private int totalPages;
    private boolean first;
    private boolean last;
    @JsonProperty("record_from")
    private int recordFrom;
    @JsonProperty("record_to")
    private int recordTo;


    public void record(int number, int size) {
        if (totalElments == 0) {
            recordFrom = 0;
            recordTo = 0;
        } else {
            recordFrom = (size * (number-1))+1;
            recordTo = ((size*number) > totalElments) ? (int) totalElments : number * size;
        }
    }
}
