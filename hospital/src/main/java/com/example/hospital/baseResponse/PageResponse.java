package com.example.hospital.baseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageResponse<T> {

    int recordCount;
    T response;

}
